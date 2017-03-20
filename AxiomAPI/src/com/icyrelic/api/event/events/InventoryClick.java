package com.icyrelic.api.event.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.event.MenuClickEvent;
import com.icyrelic.api.menu.AxiomMenu;
import com.icyrelic.api.menu.MenuButton;
import com.icyrelic.api.menu.MenuHolder;
import com.icyrelic.api.player.AxiomPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author IcyRelic
 */
public class InventoryClick extends AxiomEvent {

    public InventoryClick() {
        super(AxiomAPI.Instance);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onInventoryClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null) return;
        if(!(event.getClickedInventory().getHolder() instanceof MenuHolder)) return;

        AxiomPlayer axiomPlayer = AxiomAPI.PlayerManager.getAxiomPlayer(event.getWhoClicked().getUniqueId());
        MenuHolder holder = (MenuHolder) event.getClickedInventory().getHolder();
        AxiomMenu menu = holder.getMenu();
        MenuButton button = menu.getButton(event.getSlot());

        AxiomAPI.EventManager.call(new MenuClickEvent(axiomPlayer, menu, button));

        event.setCancelled(true);
    }
}
