package com.icyrelic.admin;

import com.icyrelic.admin.commands.*;
import com.icyrelic.admin.events.JoinEvent;
import com.icyrelic.admin.managers.AdminManager;
import com.icyrelic.api.AxiomModule;

/**
 * @author IcyRelic
 */
public class AxiomAdmin extends AxiomModule {

    public static AxiomAdmin Instance;
    public static AdminManager AdminManager;

    public void enable() {
        loadConfiguration();
        loadManagers();
        loadCommands();
        loadEvents();
    }

    private void loadManagers(){
        Instance = this;
        AdminManager = new AdminManager();
    }

    private void loadConfiguration(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void loadCommands(){
        new BanCommand();
        new KickCommand();
        new PermBanCommand();
        new TempBanCommand();
        new WarnCommand();
        new UnbanCommand();
        new CheckBanCommand();
    }

    private void loadEvents(){
        new JoinEvent();
    }
}
