package com.icyrelic.api.event.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

/**
 * @author IcyRelic
 */
public class CommandEvent extends AxiomEvent {

    public CommandEvent() {
        super(AxiomAPI.Instance);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommand(PlayerCommandPreprocessEvent e){
        if(AxiomAPI.CommandManager.process(e.getPlayer(), e.getMessage())) e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConsoleCommand(ServerCommandEvent e){
        if(AxiomAPI.CommandManager.process(e.getSender(), e.getCommand())) e.setCancelled(true);
    }

}
