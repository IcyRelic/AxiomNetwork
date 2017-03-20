package com.icyrelic.api.event;

import com.icyrelic.api.AxiomAPI;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * @author IcyRelic
 */
public abstract class AxiomEvent implements Listener {

    PluginManager pluginManager;

    public AxiomEvent(Plugin plugin){
        pluginManager = AxiomAPI.Instance.getServer().getPluginManager();
        AxiomAPI.EventManager.register(this, plugin);
    }
}
