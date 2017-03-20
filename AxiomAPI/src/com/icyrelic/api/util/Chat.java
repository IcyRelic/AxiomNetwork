package com.icyrelic.api.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * @author IcyRelic
 */
public class Chat {

    /**
     *
     * @param message message to broadcase
     * @param players players to ignore
     */
    public static void sendGlobal(String message, Player... players){
        Bukkit.getOnlinePlayers().forEach(player -> {
                if(!Arrays.asList(players).contains(player)){
                    player.sendMessage(message);
                }
        });
    }
}
