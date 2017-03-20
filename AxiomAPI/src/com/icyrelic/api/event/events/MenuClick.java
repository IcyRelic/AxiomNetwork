package com.icyrelic.api.event.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.event.MenuClickEvent;
import org.bukkit.event.EventHandler;

/**
 * @author IcyRelic
 */
public class MenuClick extends AxiomEvent {

    public MenuClick() {
        super(AxiomAPI.Instance);
    }

    @EventHandler
    private void onMenuClick(MenuClickEvent event) {
        event.getMenuButton().execute(event.getAxiomPlayer());
    }
}
