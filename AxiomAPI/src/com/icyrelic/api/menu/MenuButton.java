package com.icyrelic.api.menu;

import com.icyrelic.api.player.AxiomPlayer;
import org.bukkit.inventory.ItemStack;

/**
 * @author IcyRelic
 */
public abstract class MenuButton {

    protected ItemStack item;
    protected int slot;

    public MenuButton(ItemStack item, int slot) {
        this.item = item;
        this.slot = slot;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getSlot() {
        return slot;
    }

    public abstract void execute(AxiomPlayer axiomPlayer);

}
