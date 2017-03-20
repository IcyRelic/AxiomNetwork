package com.icyrelic.spawn;

import com.icyrelic.api.AxiomModule;
import com.icyrelic.spawn.commands.SetSpawnCommand;
import com.icyrelic.spawn.commands.SpawnCommand;
import com.icyrelic.spawn.events.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * @author IcyRelic
 */
public class AxiomSpawn extends AxiomModule {

    public static AxiomSpawn Instance;

    public void enable() {
        loadConfiguration();
        Instance = this;
        loadEvents();
        loadCommands();
    }

    private void loadEvents(){
        new JoinEvent();
    }

    private void loadCommands(){
        new SpawnCommand();
        new SetSpawnCommand();
    }

    public Location getSpawnLocation(){
        int x = Integer.parseInt(getConfig().getString("Spawn.X"));
        int y = Integer.parseInt(getConfig().getString("Spawn.Y"));
        int z = Integer.parseInt(getConfig().getString("Spawn.Z"));
        float yaw = Float.parseFloat(getConfig().getString("Spawn.Yaw"));
        float pitch = Float.parseFloat(getConfig().getString("Spawn.Pitch"));
        World world = Bukkit.getWorld(getConfig().getString("Spawn.World"));
        Location loc = new Location(world,x, y, z, yaw, pitch);
        loc.add(0.5, 0, 0.5);

        return loc;
    }

    public void setSpawnLocation(Location loc){
        getConfig().set("Spawn.X", loc.getBlockX());
        getConfig().set("Spawn.Y", loc.getBlockY());
        getConfig().set("Spawn.Z", loc.getBlockZ());
        getConfig().set("Spawn.Yaw", loc.getYaw());
        getConfig().set("Spawn.Pitch", loc.getPitch());
        getConfig().set("Spawn.World", loc.getWorld().getName());
        saveConfig();

    }

    private void loadConfiguration(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

}
