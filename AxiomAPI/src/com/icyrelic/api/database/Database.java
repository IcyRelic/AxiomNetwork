package com.icyrelic.api.database;

import com.icyrelic.api.async.Call;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author IcyRelic
 */
public abstract class Database  {

    protected String name;
    protected static Connection con = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;
    protected ResultSetMetaData rsmd = null;

    public abstract boolean connect(String host, String port, String db, String user, String password);

    public boolean executeUpdate(String statement){
        return new Call<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    ps = con.prepareStatement(statement);
                    ps.executeUpdate();
                    return true;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return false;
                }


            }
        }.getBackAsync();

    }

    public List<HashMap<String, Object>> executeQuery(String statement){
        return new Call<List<HashMap<String, Object>>>() {
            @Override
            public List<HashMap<String, Object>> call() throws Exception {

                try {
                    ps = con.prepareStatement(statement);
                    rs = ps.executeQuery();
                    rsmd = rs.getMetaData();

                    List<HashMap<String, Object>> data = new ArrayList<>();
                    int cols = rsmd.getColumnCount();

                    while(rs.next()){
                        HashMap<String, Object> row = new HashMap<>();
                        for (int i = 1; i <= (cols); i++) {
                            String column_name = rsmd.getColumnName(i);
                            Object value = rs.getObject(i);
                            row.put(column_name, value);
                        }
                        data.add(row);
                    }
                    return data;

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                return null;

            }
        }.getBackAsync();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void shutdown(){
        try {
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}



