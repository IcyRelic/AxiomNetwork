package com.icyrelic.home;

import com.icyrelic.api.AxiomModule;
import com.icyrelic.home.commands.HomeCommand;
import com.icyrelic.home.commands.HomeListCommand;
import com.icyrelic.home.commands.SetHomeCommand;
import com.icyrelic.home.manager.HomeManager;

/**
 * @author IcyRelic
 */
public class AxiomHome extends AxiomModule {

    public static AxiomHome Instance;
    public static HomeManager HomeManager;

    public void enable() {
        loadManagers();
        loadCommands();
    }

    private void loadManagers(){
        Instance = this;
        HomeManager = new HomeManager();
    }

    private void loadCommands(){
        new SetHomeCommand();
        new HomeCommand();
        new HomeListCommand();
    }
}
