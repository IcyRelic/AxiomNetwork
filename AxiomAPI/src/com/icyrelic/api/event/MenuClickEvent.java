package com.icyrelic.api.event;


import com.icyrelic.api.menu.AxiomMenu;
import com.icyrelic.api.menu.MenuButton;
import com.icyrelic.api.player.AxiomPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author IcyRelic
 */
public class MenuClickEvent extends Event {

    private static HandlerList handlers = new HandlerList();
    private AxiomPlayer axiomPlayer;
    private AxiomMenu menu;
    private MenuButton button;

    public MenuClickEvent(AxiomPlayer axiomPlayer, AxiomMenu menu, MenuButton button){
        this.axiomPlayer = axiomPlayer;
        this.menu = menu;
        this.button = button;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public AxiomPlayer getAxiomPlayer() {
        return axiomPlayer;
    }

    public AxiomMenu getMenu() {
        return menu;
    }

    public MenuButton getMenuButton() {
        return button;
    }
}
