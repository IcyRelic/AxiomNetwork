package com.icyrelic.api.event.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.AxiomModule;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.language.Language;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.PluginEnableEvent;

/**
 * @author IcyRelic
 */
public class ModuleEnableEvent extends AxiomEvent {

    public ModuleEnableEvent() {
        super(AxiomAPI.Instance);
    }

    @EventHandler
    public void onPluginEnable(PluginEnableEvent event){
        boolean axiomModule = event.getPlugin() instanceof AxiomModule;
        if(!axiomModule) {
            AxiomAPI.Instance.sendConsoleMessage(Language.API_PREFIX.getMessage() + ChatColor.RED + " Rogue Plugin Detected: " + event.getPlugin().getName());
        }

    }
}
