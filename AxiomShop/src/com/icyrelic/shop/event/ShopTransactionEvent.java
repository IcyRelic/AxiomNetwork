package com.icyrelic.shop.event;


import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.player.AxiomPlayer;
import com.icyrelic.shop.enums.Transaction;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

/**
 * @author IcyRelic
 */

public class ShopTransactionEvent extends Event implements Cancellable {

    private static HandlerList handlers = new HandlerList();
    private AxiomPlayer buyer;
    private AxiomPlayer seller;
    private Player player;
    private Material material;
    private byte data;
    private int amount;
    private double price;
    private boolean cancelled;
    private boolean isAdmin;
    private UUID owner;
    private Transaction type;
    private Block signBlock;

    public ShopTransactionEvent(Player player, UUID owner, Transaction type, boolean isAdmin, Block signBlock, Material material, byte data, int amount, double price) {
        this.player = player;
        this.material = material;
        this.data = data;
        this.amount = amount;
        this.price = price;
        this.isAdmin = isAdmin;
        this.owner = owner;
        this.type = type;
        this.signBlock = signBlock;
        this.buyer = AxiomAPI.PlayerManager.getAxiomPlayer(player.getUniqueId());
        this.seller = AxiomAPI.PlayerManager.getOfflineAxiomPlayer(owner);
    }



    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public Block getSignBlock() {
        return signBlock;
    }

    public Transaction getType() {
        return type;
    }

    public Material getMaterial() {
        return material;
    }

    public byte getData() {
        return data;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public UUID getOwner() {
        return owner;
    }

    public AxiomPlayer getBuyer() {
        return buyer;
    }

    public AxiomPlayer getSeller() {
        return seller;
    }

    public boolean isAdminShop() {
        return isAdmin;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
