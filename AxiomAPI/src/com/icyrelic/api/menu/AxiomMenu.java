package com.icyrelic.api.menu;

import com.icyrelic.api.player.AxiomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IcyRelic
 */
public abstract class AxiomMenu {

    protected List<MenuButton> buttons;
    protected Inventory inventory;
    protected AxiomPlayer axiomPlayer;
    protected MenuHolder owner;
    protected AxiomMenu parent;
    protected String title;
    protected int size;

    public AxiomMenu(AxiomPlayer axiomPlayer, String title, int size, AxiomMenu parent) {
        this.buttons = new ArrayList<>();
        this.axiomPlayer = axiomPlayer;;
        this.title = title;
        this.size = size;
        this.owner = new MenuHolder(this);
        this.parent = parent;
        open();
    }

    private void create(){
        inventory = Bukkit.createInventory(owner, size, title);
        addButtons();
    }

    protected abstract void addButtons();

    protected void addButton(MenuButton button){
        this.buttons.add(button);
    }

    protected void removeButton(int slot){
        if(getButton(slot) == null) return;
        buttons.remove(getButton(slot));
        inventory.setItem(slot, null);
    }

    public MenuButton getButton(int slot){
        for(MenuButton button : buttons)
            if(button.getSlot() == slot) return button;
        return null;
    }

    protected void openParent(){
        if(parent == null) { axiomPlayer.getPlayer().closeInventory(); return; }
        parent.open();
    }

    protected void open(){
        create();
        axiomPlayer.getPlayer().openInventory(inventory);
    }
}
