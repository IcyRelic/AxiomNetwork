package com.icyrelic.chat.manager;

import com.icyrelic.api.manager.Manager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author IcyRelic
 */
public class ChatManager extends Manager {

    private HashMap<UUID, String> mutedPlayers = new HashMap<>();

    public HashMap<UUID, String> getMutedPlayers(){
        return mutedPlayers;
    }

    public boolean isPlayerMuted(Player player){
        return mutedPlayers.containsKey(player.getUniqueId());
    }

    public void unmute(OfflinePlayer player){
        if(mutedPlayers.containsKey(player.getUniqueId()))
            mutedPlayers.remove(player.getUniqueId());
    }

    public void mute(OfflinePlayer player, String reason){
        if(mutedPlayers.containsKey(player.getUniqueId())){ mutedPlayers.remove(player.getUniqueId()); return; }
        mutedPlayers.put(player.getUniqueId(), reason);
    }
}
