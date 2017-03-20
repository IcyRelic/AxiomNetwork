package com.icyrelic.admin.managers;

import com.icyrelic.admin.AxiomAdmin;
import com.icyrelic.api.database.Database;
import com.icyrelic.api.database.types.MySQLDatabase;
import com.icyrelic.api.manager.Manager;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author IcyRelic
 */
public class AdminManager extends Manager {

    private Database database;

    public void setup() {
        setupDatabase();
    }

    public void shutdown() {
        this.database.shutdown();
    }

    private void setupDatabase(){
        String host = AxiomAdmin.Instance.getConfig().getString("Database.Hostname");
        String port = AxiomAdmin.Instance.getConfig().getString("Database.Port");
        String username = AxiomAdmin.Instance.getConfig().getString("Database.Username");
        String password = AxiomAdmin.Instance.getConfig().getString("Database.Password");
        String name = AxiomAdmin.Instance.getConfig().getString("Database.Name");

        this.database = new MySQLDatabase();

        if(!this.database.connect(host, port, name, username, password)) return;

        String bans = "CREATE TABLE IF NOT EXISTS Bans ( " +
                "ID INT NOT NULL AUTO_INCREMENT, " +
                "UUID varchar(255), " +
                "Type varchar(255), " +
                "OType varchar(255), " +
                "Admin varchar(255), " +
                "Reason varchar(255), " +
                "Timestamp varchar(255), " +
                "Unban varchar(255)," +
                "PRIMARY KEY ( ID )" +
                ")";

        String warnings = "CREATE TABLE IF NOT EXISTS Warnings ( " +
                "ID INT NOT NULL AUTO_INCREMENT, " +
                "UUID varchar(255), " +
                "Admin varchar(255), " +
                "Reason varchar(255), " +
                "Timestamp varchar(255), " +
                "PRIMARY KEY ( ID )" +
                ")";

        String kicks = "CREATE TABLE IF NOT EXISTS Kicks ( " +
                "ID INT NOT NULL AUTO_INCREMENT, " +
                "UUID varchar(255), " +
                "Admin varchar(255), " +
                "Reason varchar(255), " +
                "Timestamp varchar(255), " +
                "PRIMARY KEY ( ID )" +
                ")";

        String reports = "CREATE TABLE IF NOT EXISTS Reports ( " +
                "ID INT NOT NULL AUTO_INCREMENT, " +
                "UUID varchar(255), " +
                "Offender varchar(255), " +
                "Admin varchar(255), " +
                "Report varchar(255), " +
                "Timestamp varchar(255), " +
                "PRIMARY KEY ( ID )" +
                ")";

        this.database.executeUpdate(bans);
        this.database.executeUpdate(warnings);
        this.database.executeUpdate(kicks);
        this.database.executeUpdate(reports);
    }

    public boolean createBan(UUID banned, UUID admin, BanType type, String reason, long unban){
        if(isBanned(banned)) return false;
        String sql = "INSERT INTO `Bans`" +
                "(`ID`, `UUID`, `Type`, `OType`, `Admin`, `Reason`, `Timestamp`, `Unban`) " +
                "VALUES (null, '" + banned + "', '" + type.name() + "', '" + type.name() + "', '" + admin + "', '" + reason + "', '" + System.currentTimeMillis() + "', '" + unban + "')";

        this.database.executeUpdate(sql);
        return true;
    }

    public boolean unban(UUID banned){
        List<HashMap<String, Object>> query = database.executeQuery("SELECT * from Bans WHERE UUID = '" + banned + "' ORDER BY ID DESC");
        String sql = "UPDATE Bans SET Type = '" + BanType.UNBAN.name() + "' WHERE UUID = '" + banned + "'";
        BanType type = BanType.valueOf(query.get(0).get("Type").toString());
        if(type == BanType.PERM) return false;

        this.database.executeUpdate(sql);
        return true;
    }

    public boolean isBanned(UUID banned){
        List<HashMap<String, Object>> query = database.executeQuery("SELECT * from Bans WHERE UUID = '" + banned + "' ORDER BY ID DESC");
        if (query.isEmpty()) return false;
        BanType type = BanType.valueOf(query.get(0).get("Type").toString());

        switch (type){
            case BAN:
            case PERM:
                return true;
            case TEMP:
                long unban = Long.parseLong(query.get(0).get("Unban").toString());
                if(System.currentTimeMillis() >= unban) { unban(banned); return false; }
                return true;
            case UNBAN:
                return false;
        }

        return false;
    }

    public HashMap<String, Object> getBan(UUID banned){
        if(getNumBans(banned) < 1) return null;
        return database.executeQuery("SELECT * from Bans WHERE UUID = '" + banned + "' ORDER BY ID DESC").get(0);
    }

    public void createKick(UUID banned, UUID admin, String reason){
        String sql = "INSERT INTO `Kicks`" +
                "(`ID`, `UUID`, `Admin`, `Reason`, `Timestamp`) " +
                "VALUES (null, '" + banned + "', '" + admin + "', '" + reason + "', '" + System.currentTimeMillis() + "')";

        this.database.executeUpdate(sql);
    }

    public void createWarning(UUID banned, UUID admin, String reason){
        String sql = "INSERT INTO `Warnings`" +
                "(`ID`, `UUID`, `Admin`, `Reason`, `Timestamp`) " +
                "VALUES (null, '" + banned + "', '" + admin + "', '" + reason + "', '" + System.currentTimeMillis() + "')";

        this.database.executeUpdate(sql);
    }

    public void createReport(UUID banned, UUID offender, UUID admin, String reason){
        String sql = "INSERT INTO `Warnings`" +
                "(`ID`, `UUID`, `Offender`, `Admin`, `Reason`, `Timestamp`) " +
                "VALUES (null, '" + banned + "', '" + offender + "', '" + admin + "', '" + reason + "', '" + System.currentTimeMillis() + "')";

        this.database.executeUpdate(sql);
    }

    public int getNumBans(UUID uuid){
        String sql = "SELECT COUNT( ID ) FROM Bans WHERE UUID = '" + uuid + "'";
        return Integer.parseInt(database.executeQuery(sql).get(0).get("COUNT( ID )").toString());
    }

    public int getNumKicks(UUID uuid){
        String sql = "SELECT COUNT( ID ) FROM Kicks WHERE UUID = '" + uuid + "'";
        return Integer.parseInt(database.executeQuery(sql).get(0).get("COUNT( ID )").toString());
    }

    public int getNumWarnings(UUID uuid){
        String sql = "SELECT COUNT( ID ) FROM Warnings WHERE UUID = '" + uuid + "'";
        return Integer.parseInt(database.executeQuery(sql).get(0).get("COUNT( ID )").toString());
    }

    public int getNumReports(UUID uuid){
        String sql = "SELECT COUNT( ID ) FROM Reports WHERE UUID = '" + uuid + "'";
        return Integer.parseInt(database.executeQuery(sql).get(0).get("COUNT( ID )").toString());
    }
}
