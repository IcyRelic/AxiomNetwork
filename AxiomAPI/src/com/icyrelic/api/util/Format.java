package com.icyrelic.api.util;

import com.icyrelic.api.exceptions.ArrayMismatchException;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IcyRelic
 */
public class Format {

    /**
     *
     * @param str Message to color
     * @return
     */
    public static String colorString(String str){
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    /**
     *
     * @param raw Raw message
     * @param vars Items to replace
     * @param replacements Replacements for 'vars'
     * @return Raw message with the replaced vars
     * @throws ArrayMismatchException happens if vars.length != replacements.length
     */
    public static String replace(String raw, String[] vars, Object[] replacements) {

        if(vars.length != replacements.length) { return raw; }
        for(int i = 0; i < vars.length; i++){
            raw = raw.replace(vars[i], replacements[i].toString());
        }

        return raw;
    }

    public static String formatLocation(Location loc){
        StringBuilder sb = new StringBuilder();

        sb.append(loc.getWorld().getName());
        sb.append(",");
        sb.append(loc.getBlockX());
        sb.append(",");
        sb.append(loc.getBlockY());
        sb.append(",");
        sb.append(loc.getBlockZ());
        sb.append(",");
        sb.append(loc.getYaw());
        sb.append(",");
        sb.append(loc.getPitch());

        return sb.toString();
    }

    public static Location stringToLocation(String str){
        String world = str.split(",")[0];
        int x = Integer.parseInt(str.split(",")[1]);
        int y = Integer.parseInt(str.split(",")[2]);
        int z = Integer.parseInt(str.split(",")[3]);
        float yaw = Float.parseFloat(str.split(",")[4]);
        float pitch = Float.parseFloat(str.split(",")[5]);

        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public static ItemStack stringToItem(String str){
        String[] s = str.split(",");
        ItemStack item = new ItemStack(Material.valueOf(s[0].toUpperCase()), Integer.parseInt(s[2]), Short.parseShort(s[1]));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Format.colorString(s[3]));
        List<String> loreList = new ArrayList<>();
        String[] lore = s[4].split(";");

        for(String ls : lore){
            loreList.add(Format.colorString(ls));
        }

        meta.setLore(loreList);

        item.setItemMeta(meta);

        return item;
    }

    public static String itemToString(ItemStack item){
        StringBuilder sb = new StringBuilder();
        sb.append(item.getType().name());
        sb.append(",");
        sb.append(item.getData());
        sb.append(",");
        sb.append(item.getAmount());
        sb.append(",");
        sb.append(item.getItemMeta().getDisplayName());
        sb.append(",");
        for(String s : item.getItemMeta().getLore()){
            sb.append(s);
            sb.append(";");
        }

        return sb.toString().substring(0, sb.toString().length() - 1);
    }

}
