package com.icyrelic.api.player.objects;

import org.bukkit.Location;

import java.util.HashMap;

/**
 * @author IcyRelic
 */
public class Locations {

    private HashMap<LocationType, HashMap<String, Location>> locations = new HashMap<>();

    public void addLocation(LocationType type, String name, Location location){
        if(locations.containsKey(type)) { locations.get(type).put(name, location); return; }
        HashMap<String, Location> loc = new HashMap<>();
        loc.put(name, location);

        locations.put(type, loc);
    }

    public HashMap<String, Location> getLocations(LocationType type){
        if(locations.containsKey(type)) return null;

        return locations.get(type);
    }

    public boolean hasAny(LocationType type){
        return locations.containsKey(type);
    }

    public void removeLocation(LocationType type, String name){
        if(!locations.containsKey(type)) return;
        if(!locations.get(type).containsKey(name)) return;

        locations.get(type).remove(name);
    }

    public Location getLocation(LocationType type, String name){
        if(!locations.containsKey(type)) return null;
        if(!locations.get(type).containsKey(name)) return null;

        return locations.get(type).get(name);
    }

    public boolean locationExists(LocationType type, String name){
        return locations.containsKey(type) && locations.get(type).containsKey(name);
    }

}
