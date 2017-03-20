package com.icyrelic.shop;

import com.icyrelic.api.AxiomModule;
import com.icyrelic.shop.events.*;

/**
 * @author IcyRelic
 */
public class AxiomShop extends AxiomModule {

    public static AxiomShop Instance;

    public void enable() {
        loadConfiguration();
        loadManagers();
        loadEvents();
    }

    private void loadManagers(){
        Instance = this;
    }

    private void loadEvents(){
        new SignPlaceEvent();
        new SignBreakEvent();
        new InteractEvent();
        new ShopBuyEvent();
        new ShopSellEvent();
    }

    private void loadConfiguration(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
