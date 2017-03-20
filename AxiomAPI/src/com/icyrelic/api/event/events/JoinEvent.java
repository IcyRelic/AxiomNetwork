package com.icyrelic.api.event.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.player.AxiomPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author IcyRelic
 */
public class JoinEvent extends AxiomEvent {

    public JoinEvent(){
        super(AxiomAPI.Instance);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        AxiomPlayer axiomPlayer = AxiomAPI.PlayerManager.loginPlayer(player);

        //scoreboard(axiomPlayer);
    }


}
