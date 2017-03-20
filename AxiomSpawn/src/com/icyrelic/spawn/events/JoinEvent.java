package com.icyrelic.spawn.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.language.Language;
import com.icyrelic.spawn.AxiomSpawn;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Set;

/**
 * @author IcyRelic
 */
public class JoinEvent extends AxiomEvent {

    public JoinEvent(){ super(AxiomSpawn.Instance); }

    @EventHandler (priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event){
        if(!AxiomAPI.PlayerManager.playerExists(event.getPlayer().getUniqueId())){
            if(!AxiomSpawn.Instance.getConfig().isSet("Spawn")) { noSpawn(event.getPlayer()); return; }
            Set<String> keys = AxiomSpawn.Instance.getConfig().getConfigurationSection("Spawn").getKeys(false);
            if(keys.isEmpty()) { noSpawn(event.getPlayer()); return; }
            event.getPlayer().teleport(AxiomSpawn.Instance.getSpawnLocation());
            event.getPlayer().sendMessage(Language.TELEPORTED_TO_SPAWN.getMessage(Language.SPAWN_PREFIX));
        }

    }

    private void noSpawn(Player player){
        player.sendMessage(Language.NO_SPAWN.getMessage(Language.SPAWN_PREFIX));
    }



}
