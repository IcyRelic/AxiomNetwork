package com.icyrelic.api.manager;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IcyRelic
 */
public class EventManager extends Manager {

    List<AxiomEvent> events = new ArrayList<>();

    public void register(AxiomEvent event, Plugin plugin){
        String className = event.getClass().getSimpleName();
        AxiomAPI.Instance.sendConsoleMessage(ChatColor.GREEN + "Registering Events: " + ChatColor.YELLOW + className + ChatColor.GRAY + " for " + ChatColor.YELLOW + plugin.getName());
        plugin.getServer().getPluginManager().registerEvents(event, plugin);
        events.add(event);
    }

    public List<AxiomEvent> getEvents() {
        return events;
    }

    public void unRegister(Listener listener){
        if (listener == null) return;
        HandlerList.unregisterAll(listener);
    }

    public void call(Event event){
        AxiomAPI.Instance.getServer().getPluginManager().callEvent(event);
    }

}
