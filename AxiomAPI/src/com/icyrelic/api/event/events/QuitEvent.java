package com.icyrelic.api.event.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author IcyRelic
 */
public class QuitEvent extends AxiomEvent {

    public QuitEvent(){
        super(AxiomAPI.Instance);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        AxiomAPI.PlayerManager.logoutPlayer(event.getPlayer());
    }

}
