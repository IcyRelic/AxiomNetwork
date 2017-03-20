package com.icyrelic.messages.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * @author IcyRelic
 */
public class MessageUtil {

    static ChatColor bold = ChatColor.BOLD;
    static ChatColor italic = ChatColor.ITALIC;
    static ChatColor underline = ChatColor.UNDERLINE;
    static ChatColor magic = ChatColor.MAGIC;
    static ChatColor strike = ChatColor.STRIKETHROUGH;
    static ChatColor reset = ChatColor.RESET;

    public static String addColor(String msg){

        return msg
                .replaceAll("(&([a-f0-9]))", "\u00A7$2")
                .replace("&r", reset+"")
                .replace("&l", bold+"")
                .replace("&o", italic+"")
                .replace("&n", underline+"")
                .replace("&k", magic+"")
                .replace("&m", strike+"");

    }

    public static void broadcast(String msg) {
        Bukkit.getServer().broadcastMessage(msg);
    }

    public static String firstCap(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1, s.length()).toLowerCase();
    }
}
