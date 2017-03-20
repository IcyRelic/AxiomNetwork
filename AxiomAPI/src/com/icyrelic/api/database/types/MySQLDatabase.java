package com.icyrelic.api.database.types;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.database.Database;
import net.md_5.bungee.api.ChatColor;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author IcyRelic
 */
public class MySQLDatabase extends Database {

    public boolean connect(String host, String port, String db, String user, String password){
        String url = "jdbc:mysql://"+ host + ":" + port + "/" + db + "?autoReconnect=true";
        AxiomAPI.Instance.sendConsoleMessage(ChatColor.YELLOW + "Attempting to connect to " + url);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            AxiomAPI.Instance.sendConsoleMessage(ChatColor.RED + "Failed to connect to " + url);
            AxiomAPI.Instance.sendConsoleMessage(ChatColor.RED + e.getMessage());
            return false;
        }

        AxiomAPI.Instance.sendConsoleMessage(ChatColor.GREEN + "Successfully connected to " + url);
        return true;
    }
}
