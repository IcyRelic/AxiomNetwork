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
public class ShopBuyEvent extends AxiomEvent {

    public ShopBuyEvent() {
        super(AxiomShop.Instance);
    }

    @EventHandler
    public void onBuy(ShopTransactionEvent event){
        if(event.getType() != Transaction.BUY) return;

        Player player = event.getPlayer();
        Block block = event.getSignBlock();
        Material material = event.getMaterial();
        byte data = event.getData();
        int amount = event.getAmount();
        double price = event.getPrice();
        boolean isAdmin = event.isAdminShop();
        boolean hasEnough = event.getBuyer().getBank().has(amount);

        if(!hasEnough) { player.sendMessage(Language.NOT_ENOUGH_MONEY.getMessage(Language.SHOP_PREFIX)); return; }

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

            Chest containingChest = null;

            for(Chest chest : chests){
                if(chest.getInventory().contains(item.getType(), amount)) containingChest = chest;
            }

            if(containingChest == null){
                buyer.getPlayer().sendMessage(Language.OUT_OF_STOCK.getMessage(Language.SHOP_PREFIX));
                if(offlinePlayer.isOnline())
                    ((Player) offlinePlayer).sendMessage(Language.SELLER_OUT_OF_STOCK.getMessage(Language.SHOP_PREFIX));
                return;
            }

            for(int i = 0; i != amount; i++){
                containingChest.getInventory().removeItem(item);
                containingChest.update(true);
            }

            seller.getBank().deposit(amount);

            if(offlinePlayer.isOnline()){
                ((Player) offlinePlayer).sendMessage(Format.replace(Language.SELLER_SOLD.getMessage(Language.SHOP_PREFIX),
                        new String[] {"%price%", "%amount%", "%item%", "%player%"}, new Object[] {price, amount, item.getType(), buyer.getPlayer().getName()}));

            }
        }

        buyer.getBank().charge(amount);

        buyer.getPlayer().sendMessage(Format.replace(Language.ITEM_BOUGHT.getMessage(Language.SHOP_PREFIX),
                new String[] {"%price%", "%amount%", "%item%"}, new Object[] {price, amount, item.getType()}));


        addItems(item, amount, buyer.getPlayer());
    }

    private void addItems(ItemStack item, int amount, Player player){

        Inventory playerInv = player.getInventory();
        int added = 0;
        while(added != amount){
            if(playerInv.firstEmpty() != -1){
                for(int i=0; i!=amount; i++){
                    playerInv.addItem(item);
                }
                added = amount;
            }else{
                for (int i=0; i<35; i++) {
                    if (playerInv.getItem(i).getAmount()+item.getAmount()<=64 && playerInv.getItem(i).getType().equals(item.getType())) {
                        playerInv.addItem(item);
                        break;
                    }
                    if (i == 34) {
                        player.getLocation().getWorld().dropItem(player.getLocation(), item);
                    }
                }
                added++;
            }


        }
    }
}
