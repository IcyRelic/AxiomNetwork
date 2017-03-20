package com.icyrelic.api;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author IcyRelic
 */
public abstract class AxiomModule extends JavaPlugin {

    public void onEnable(){
        enable();
    }

    public void onDisable() { disable(); }

    public void disable() {}

    public abstract void enable();

    public void sendConsoleMessage(String message){
        getServer().getConsoleSender().sendMessage(message);
    }
}
