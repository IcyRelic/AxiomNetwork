package com.icyrelic.mobcash;

import com.icyrelic.api.AxiomModule;
import com.icyrelic.mobcash.events.EntityEvent;

/**
 * @author IcyRelic
 */
public class AxiomMobCash extends AxiomModule{

    public static AxiomMobCash Instance;

    public void enable() {
        loadManagers();
        loadConfiguration();
        loadEvents();
    }

    private void loadEvents(){
        new EntityEvent();
    }

    private void loadManagers(){
        Instance = this;
    }

    private void loadConfiguration(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

}
