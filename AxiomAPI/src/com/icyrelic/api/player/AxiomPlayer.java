package com.icyrelic.api.player;

import com.icyrelic.api.player.objects.LocationType;
import com.icyrelic.api.player.objects.Locations;
import com.icyrelic.api.util.Format;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author IcyRelic
 */
public class AxiomPlayer {

    private Player player;
    private OfflinePlayer offlinePlayer;
    private UUID uid;
    private PlayerFile file;
    private Locations locations;

    public AxiomPlayer(Player player){
        this.player = player;
        this.uid = player.getUniqueId();
        this.file = new PlayerFile(uid);
        this.file.createPlayerFile();
        load();
    }

    public AxiomPlayer(OfflinePlayer offlinePlayer){
        this.offlinePlayer = offlinePlayer;
        this.uid = offlinePlayer.getUniqueId();
        this.file = new PlayerFile(uid);
        this.file.createPlayerFile();
        load();
    }

    private void load(){
        this.locations = new Locations();

        file.getStringList("Locations").forEach(s -> {
            LocationType type = LocationType.valueOf(s.split(":")[0].toUpperCase());
            String name = s.split(":")[1];
            Location loc = Format.stringToLocation(s.split(":")[2]);

            this.locations.addLocation(type, name, loc);
        });

    }

    public void save(){
        List<String> locList = new ArrayList<>();

        Arrays.asList(LocationType.values()).forEach(type -> {

            if(this.locations.hasAny(type)){
                this.locations.getLocations(type).forEach((name, location) -> {
                    locList.add(type + ":" + name + ":" + Format.formatLocation(location));
                });
            }

        });
        file.set("Locations", locList);
    }

    public PlayerFile getPlayerFile(){
        return this.file;
    }

    public UUID getUUID() {
        return uid;
    }

    public Player getPlayer() {
        return player;
    }

    public OfflinePlayer getOfflinePlayer() {
        return offlinePlayer;
    }

    public boolean hasPermission(String permission){

        return player.hasPermission(permission);

    }

    public Locations getLocations() {
        return locations;
    }
}
