package com.icyrelic.api.menu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * @author IcyRelic
 */
public class MenuHolder implements InventoryHolder {

    private AxiomMenu menu;

    public MenuHolder(AxiomMenu menu) {
        this.menu = menu;
    }

    public Inventory getInventory() {
        return menu.inventory;
    }

    public AxiomMenu getMenu() {
        return menu;
    }
}
