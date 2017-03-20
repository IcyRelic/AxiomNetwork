package com.icyrelic.kits.kit;

import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author IcyRelic
 */
public class AxiomKit {

    private String name;
    private int cooldown;
    private List<ItemStack> items;

    public AxiomKit(String name, int cooldown, List<ItemStack> items){
        this.name = name;
        this.cooldown = cooldown;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public int getCooldown() {
        return cooldown;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public void setItems(List<ItemStack> items){
        this.items = items;
    }

    public void save(){

    }
}
