package com.icyrelic.api.manager.types;

import com.icyrelic.api.manager.Manager;
import com.icyrelic.api.player.AxiomPlayer;
import com.icyrelic.api.player.PlayerFile;
import com.icyrelic.api.util.MojangAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author IcyRelic
 */
public class PlayerManager extends Manager {

    private List<AxiomPlayer> players = new ArrayList<>();

    public AxiomPlayer loginPlayer(Player player){
        AxiomPlayer axiomPlayer = new AxiomPlayer(player);

        players.add(axiomPlayer);
        return axiomPlayer;
    }

    public void logoutPlayer(Player player){
        AxiomPlayer axiomPlayer = getAxiomPlayer(player.getUniqueId());
        axiomPlayer.save();
        players.remove(axiomPlayer);
    }

    public void logoutPlayer(AxiomPlayer axiomPlayer){
        axiomPlayer.save();
    }

    public void loginAll(){
        Bukkit.getOnlinePlayers().forEach(this::loginPlayer);
    }

    public void logoutAll(){
        players.forEach(this::logoutPlayer);
        players.clear();
    }

    public AxiomPlayer getAxiomPlayer(UUID uid){
        Optional<AxiomPlayer> o = players.stream().filter(axiomPlayer -> axiomPlayer.getUUID().equals(uid)).findFirst();
        if(o.isPresent()) return o.get();

        return null;
    }

    public AxiomPlayer getOfflineAxiomPlayer(String name){
        if(!playerExists(getPlayerUUID(name))) return null;
        return new AxiomPlayer(Bukkit.getOfflinePlayer(getPlayerUUID(name)));
    }

    public AxiomPlayer getOfflineAxiomPlayer(UUID uid){
        if(!playerExists(uid)) return null;
        return new AxiomPlayer(Bukkit.getOfflinePlayer(uid));
    }

    public UUID getPlayerUUID(String name){
        try {
            return MojangAPI.getInformation(name).getId();
        } catch (Exception ex){
            return null;
        }
    }

    public OfflinePlayer getPlayerFromName(String name){
        return Bukkit.getOfflinePlayer(getPlayerUUID(name));
    }

    public boolean playerExists(UUID uid){
        return new PlayerFile(uid).getPlayerFile().exists();
    }

    public boolean playerExists(String name){
        return new PlayerFile(getPlayerUUID(name)).getPlayerFile().exists();
    }

}
