package com.icyrelic.shop.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.util.Format;
import com.icyrelic.shop.AxiomShop;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author IcyRelic
 */
public class SignPlaceEvent extends AxiomEvent {

    public SignPlaceEvent(){
        super(AxiomShop.Instance);
    }

    @EventHandler
    public void onSignPlace(SignChangeEvent event){
        Player player = event.getPlayer();
        String[] lines = event.getLines();

        if(!lines[0].equalsIgnoreCase("%shop%") && !lines[0].equalsIgnoreCase("adminshop")) return;
        if(lines[1].equals("") || lines[2].equals("") || lines[3].equals("")) { player.sendMessage(Language.INVALID_SHOP.getMessage(Language.SHOP_PREFIX)); return; }

        String[] matLine = lines[1].split(":");
        String[] priceLine = lines[3].split(":");

        if(!isMaterial(matLine[0])) { player.sendMessage(Language.INVALID_SHOP_MATERIAL.getMessage(Language.SHOP_PREFIX)); return; }
        if(matLine.length > 1 && !isInt(matLine[1])) { player.sendMessage(Language.INVALID_SHOP_DATA.getMessage(Language.SHOP_PREFIX)); return; }
        Arrays.asList(priceLine).forEach(value -> { if(!isInt(value)) { player.sendMessage(Language.INVALID_SHOP_PRICE.getMessage(Language.SHOP_PREFIX)); return; } });

        if(!isInt(lines[2])) { player.sendMessage(Language.INVALID_SHOP_AMOUNT.getMessage(Language.SHOP_PREFIX)); return; }

        boolean canCreateAdmin = AxiomAPI.PlayerManager.getAxiomPlayer(player.getUniqueId()).hasPermission("AxiomShop.Admin");
        boolean isAdmin = lines[0].equals("adminshop");
        String item = Format.colorString("&f"+lines[1].toLowerCase());
        String amount = Format.colorString("&f"+lines[2]);
        String price;

        if(priceLine.length == 1){ price = Format.colorString("&aB &f" + priceLine[0]); }
        else if(priceLine.length == 2){ price = Format.colorString("&aB &f" + priceLine[0] + " : " + priceLine[1] + " &aS"); }
        else { price = Format.colorString("&cERROR"); }

        event.setLine(0, Format.colorString("&f[&aShop&f]"));
        event.setLine(1, Format.colorString("&f" + item));
        event.setLine(2, Format.colorString("&f" + amount));
        event.setLine(3, Format.colorString("&f" + price));

        if(canCreateAdmin && isAdmin)
            event.setLine(0, Format.colorString("&f[&aAxiomShop&f]"));

        AxiomShop.Instance.getConfig().set(Format.formatLocation(event.getBlock().getLocation()), player.getUniqueId().toString());
        AxiomShop.Instance.saveConfig();

    }


    private boolean isInt(String str){ return Pattern.matches("^[0-9]{1,13}", str); }

    private boolean isMaterial(String str){return Material.valueOf(str.toUpperCase()) != null; }

}
