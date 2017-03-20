package com.icyrelic.mobcash.events;

import com.earth2me.essentials.api.Economy;
import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.player.AxiomPlayer;
import com.icyrelic.api.util.Format;
import com.icyrelic.mobcash.AxiomMobCash;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * @author IcyRelic
 */
public class EntityEvent extends AxiomEvent {

    public EntityEvent(){
        super(AxiomMobCash.Instance);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if(event.getEntity().getKiller() == null) return;
        Entity entity = event.getEntity();
        Player player = event.getEntity().getKiller();
        AxiomPlayer axiomPlayer = AxiomAPI.PlayerManager.getAxiomPlayer(player.getUniqueId());

        if(AxiomMobCash.Instance.getConfig().getString(entity.getType().toString()) == null) return;

        double money = AxiomMobCash.Instance.getConfig().getDouble(entity.getType().toString());
        String name = entity instanceof Player ? "Player" : entity.getName();
        player.sendMessage(Format.replace(Language.MONEY_EARNED.getMessage(Language.MOBCASH_PREFIX),
                new String[] {"%money%", "%mob%"},
                new Object[] {money, name}
        ));
        try {
            Economy.add(player.getName(), money);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
