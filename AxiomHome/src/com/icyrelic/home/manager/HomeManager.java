package com.icyrelic.home.manager;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.player.AxiomPlayer;
import com.icyrelic.api.player.objects.LocationType;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * @author IcyRelic
 */
public class HomeManager {

    public boolean hasMaxHomes(Player player){
        AxiomPlayer axiomPlayer = AxiomAPI.PlayerManager.getAxiomPlayer(player.getUniqueId());
        int max = 3;

        if(axiomPlayer.getPlayerFile().getStringList("Homes").size() >= max) return true;

        return false;

    }

    public void addHome(Player player, String name){
        AxiomPlayer axiomPlayer = AxiomAPI.PlayerManager.getAxiomPlayer(player.getUniqueId());
        axiomPlayer.getLocations().addLocation(LocationType.HOME, name, player.getLocation());
    }

    public Location getHome(Player player, String name){
        AxiomPlayer axiomPlayer = AxiomAPI.PlayerManager.getAxiomPlayer(player.getUniqueId());
        return axiomPlayer.getLocations().getLocation(LocationType.HOME, name);
    }

    public boolean hasHome(Player player, String name){
        AxiomPlayer axiomPlayer = AxiomAPI.PlayerManager.getAxiomPlayer(player.getUniqueId());
        return axiomPlayer.getLocations().locationExists(LocationType.HOME, name);
    }

    public int getHomeNumber(Player player){
        AxiomPlayer axiomPlayer = AxiomAPI.PlayerManager.getAxiomPlayer(player.getUniqueId());
        return axiomPlayer.getLocations().getLocations(LocationType.HOME).size();
    }

    public String getHomes(Player player){
        AxiomPlayer axiomPlayer = AxiomAPI.PlayerManager.getAxiomPlayer(player.getUniqueId());
        StringBuilder sb = new StringBuilder();

        HashMap<String, Location> homes = axiomPlayer.getLocations().getLocations(LocationType.HOME);
        homes.forEach((s, location) -> sb.append(s).append(", "));

        return sb.toString().substring(0, sb.toString().length()-2);
    }

}
