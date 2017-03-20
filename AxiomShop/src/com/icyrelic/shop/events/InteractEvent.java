package com.icyrelic.shop.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.util.Format;
import com.icyrelic.shop.AxiomShop;
import com.icyrelic.shop.enums.Transaction;
import com.icyrelic.shop.event.ShopTransactionEvent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

/**
 * @author IcyRelic
 */
public class InteractEvent extends AxiomEvent {

    public InteractEvent(){
        super(AxiomShop.Instance);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) return;

        Material material = event.getClickedBlock().getType();
        if(!material.equals(Material.SIGN) && !material.equals(Material.WALL_SIGN)) return;
        Sign sign = (Sign) event.getClickedBlock().getState();
        String[] lines = sign.getLines();

        if(!lines[0].equals(Format.colorString("&f[&aShop&f]")) && !lines[0].equals(Format.colorString("&f[&aAxiomShop&f]"))) return;

        boolean isAdmin = ChatColor.stripColor(lines[0]).equals(Format.colorString("[AxiomShop]"));

        String[] matLine = lines[1].split(":");
        String[] priceLine = ChatColor.stripColor(lines[3]).replace("B ", "").replace(" S", "").split(" : ");
        Material itemMat = parseMaterial(matLine[0]);
        byte data = 0;
        if(matLine.length > 1) data = Byte.parseByte(matLine[1]);
        int amount = Integer.parseInt(ChatColor.stripColor(lines[2]));
        double buyfor = Double.parseDouble(priceLine[0]);
        double sellfor = 0;

        if(priceLine.length == 2) sellfor = Double.parseDouble(priceLine[1]);

        boolean buy = event.getAction() == Action.RIGHT_CLICK_BLOCK;
        boolean sell = event.getAction() == Action.LEFT_CLICK_BLOCK;
        UUID uid = UUID.fromString(AxiomShop.Instance.getConfig().getString(Format.formatLocation(event.getClickedBlock().getLocation())));

        if(uid.equals(event.getPlayer().getUniqueId()) && !isAdmin) return;



        if(buy  && buyfor > 0) AxiomAPI.EventManager.call(new ShopTransactionEvent(event.getPlayer(), uid, Transaction.BUY, isAdmin, event.getClickedBlock(), itemMat, data, amount, buyfor));
        if(sell  && sellfor > 0) AxiomAPI.EventManager.call(new ShopTransactionEvent(event.getPlayer(), uid, Transaction.SELL, isAdmin, event.getClickedBlock(), itemMat, data, amount, sellfor));

    }

    private Material parseMaterial(String str){
        return Material.valueOf(ChatColor.stripColor(str.toUpperCase()));
    }

}
