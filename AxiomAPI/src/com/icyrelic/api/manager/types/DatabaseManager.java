package com.icyrelic.api.manager.types;

import com.icyrelic.api.database.Database;
import com.icyrelic.api.manager.Manager;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author IcyRelic
 */
public class DatabaseManager extends Manager {

    private static HashMap<String, List<Database>> databases = new HashMap<>();

    public boolean createDatabase(Plugin plugin, String name, Database database){
        boolean exists = getDatabase(plugin, name) != null;
        if(exists) return false;
        database.setName(name);

        if(!databases.containsKey(plugin.getName()))
            databases.put(plugin.getName(), new ArrayList<>());

        databases.get(plugin.getName()).add(database);

        return true;
    }

    public void shutdownAll(Plugin plugin){
        List<Database> databaseList = databases.get(plugin.getName());
        databaseList.forEach(Database::shutdown);
        databases.remove(plugin.getName());
    }

    public Database getDatabase(Plugin plugin, String name){
        if(!databases.containsKey(plugin.getName())) return null;
        List<Database> databaseList = databases.get(plugin.getName());
        Optional<Database> database = databaseList.stream().filter(db -> db.getName().equals(name)).findFirst();

        return database.isPresent() ? database.get() : null;
    }
}
