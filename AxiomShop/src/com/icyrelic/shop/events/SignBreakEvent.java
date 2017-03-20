package com.icyrelic.shop.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.util.Format;
import com.icyrelic.shop.AxiomShop;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

/**
 * @author IcyRelic
 */
public class SignBreakEvent extends AxiomEvent {

    public SignBreakEvent(){
        super(AxiomShop.Instance);
    }

    @EventHandler
    public void onSignBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        if(!block.getType().equals(Material.SIGN) && !block.getType().equals(Material.WALL_SIGN)) return;
        if(AxiomShop.Instance.getConfig().getString(Format.formatLocation(block.getLocation())) == null) return;

        String owner = AxiomShop.Instance.getConfig().getString(Format.formatLocation(block.getLocation()));
        UUID uid = event.getPlayer().getUniqueId();
        if(!owner.equals(uid.toString()) && !AxiomAPI.PlayerManager.getAxiomPlayer(uid).hasPermission("AxiomShop.Admin")) {
            event.getPlayer().sendMessage(Language.SHOP_DELETETION_FAILED.getMessage(Language.SHOP_PREFIX));
            return;
        }


        AxiomShop.Instance.getConfig().set(Format.formatLocation(event.getBlock().getLocation()), null);
        AxiomShop.Instance.saveConfig();
        event.getPlayer().sendMessage(Language.SHOP_DELETED.getMessage(Language.SHOP_PREFIX));
    }
}
