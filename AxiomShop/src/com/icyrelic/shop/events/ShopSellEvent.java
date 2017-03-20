package com.icyrelic.shop.events;

import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.player.AxiomPlayer;
import com.icyrelic.api.util.Format;
import com.icyrelic.shop.AxiomShop;
import com.icyrelic.shop.enums.Transaction;
import com.icyrelic.shop.event.ShopTransactionEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author IcyRelic
 */
public class ShopSellEvent extends AxiomEvent {

    public ShopSellEvent() {
        super(AxiomShop.Instance);
    }

    @EventHandler
    public void onSell(ShopTransactionEvent event){
        if(event.getType() != Transaction.SELL) return;

        Player player = event.getPlayer();
        Block block = event.getSignBlock();
        Material material = event.getMaterial();
        byte data = event.getData();
        int amount = event.getAmount();
        double price = event.getPrice();
        boolean isAdmin = event.isAdminShop();
        boolean hasEnough = event.getSeller().getBank().has(amount);

        if(!hasEnough && !isAdmin) { player.sendMessage(Language.NOT_ENOUGH_MONEY.getMessage(Language.SHOP_PREFIX)); return; }
        if(!player.getInventory().contains(material, amount)) { player.sendMessage(Language.NOT_ENOUGH_TO_SELL.getMessage(Language.SHOP_PREFIX)); return; }

        process(event.getBuyer(), event.getSeller(), block, material, data, amount, price, isAdmin);
    }

    private void process(AxiomPlayer buyer, AxiomPlayer seller, Block block, Material material, byte data, int amount, double price, boolean isAdmin){
        ItemStack item = new ItemStack(material, 1, (short) 0, data);
        List<Chest> chests = new ArrayList<>();

        Arrays.asList(BlockFace.values()).forEach(blockFace -> {
            if(block.getRelative(blockFace).getType().equals(Material.CHEST))
                chests.add((Chest) block.getRelative(blockFace).getState());
        });

        if(!isAdmin){
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(seller.getUUID());
            if(chests.isEmpty()) {
                buyer.getPlayer().sendMessage(Language.CHEST_NOT_FOUND.getMessage(Language.SHOP_PREFIX));
                if(offlinePlayer.isOnline())
                    ((Player) offlinePlayer).sendMessage(Language.SELLER_CHEST_NOT_FOUND.getMessage(Language.SHOP_PREFIX));
                return;
            }

            Chest sellersChest = null;

            if(!chests.isEmpty()) sellersChest = chests.get(0);

            if(sellersChest == null){
                buyer.getPlayer().sendMessage(Language.CHEST_NOT_FOUND.getMessage(Language.SHOP_PREFIX));
                if(offlinePlayer.isOnline())
                    ((Player) offlinePlayer).sendMessage(Language.SELLER_CHEST_NOT_FOUND.getMessage(Language.SHOP_PREFIX));
                return;
            }

            if(!canFit(sellersChest.getInventory(), item.getType(), amount)) {
                buyer.getPlayer().sendMessage(Language.CHEST_FULL.getMessage(Language.SHOP_PREFIX));
                if(offlinePlayer.isOnline())
                    ((Player) offlinePlayer).sendMessage(Language.SELLER_CHEST_FULL.getMessage(Language.SHOP_PREFIX));
                return;
            }

            for(int i = 0; i != amount; i++){
                sellersChest.getInventory().addItem(item);
                sellersChest.update(true);
            }

            seller.getBank().charge(amount);
            if(offlinePlayer.isOnline()){
                ((Player) offlinePlayer).sendMessage(Format.replace(Language.SELLER_BOUGHT.getMessage(Language.SHOP_PREFIX),
                        new String[] {"%price%", "%amount%", "%item%", "%player%"}, new Object[] {price, amount, item.getType(), buyer.getPlayer().getName()}));
            }
            if(offlinePlayer.isOnline());
        }



        for(int i = 0; i != amount; i++){
            buyer.getPlayer().getInventory().removeItem(item);
            buyer.getPlayer().updateInventory();
        }

        buyer.getPlayer().sendMessage(Format.replace(Language.ITEM_SOLD.getMessage(Language.SHOP_PREFIX),
                new String[] {"%price%", "%amount%", "%item%"}, new Object[] {price, amount, item.getType()}));

        buyer.getBank().deposit(amount);
    }

    private boolean canFit(Inventory inv, Material type, int amount){

        int fit = 0;
        if(inv.firstEmpty() != -1){
            for(int i=0; i!=amount; i++){
                fit++;
            }
            fit = amount;
        }else{
            for (int i=0; i<inv.getSize(); i++) {
                ItemStack slot = inv.getItem(i);
                if(slot.getType() != type) continue;
                for (int j = slot.getAmount(); j < slot.getMaxStackSize(); j++) fit++;
            }
        }
        return fit >= amount;
    }
}
