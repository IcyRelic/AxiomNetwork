package com.icyrelic.api;

import com.icyrelic.api.commands.cmd.MapCommand;
import com.icyrelic.api.event.events.*;
import com.icyrelic.api.manager.EventManager;
import com.icyrelic.api.manager.types.*;

/**
 * @author IcyRelic
 */
public class AxiomAPI extends AxiomModule {

    public static DatabaseManager DatabaseManager;
    public static CommandManager CommandManager;
    public static LanguageManager LanguageManager;
    public static PlayerManager PlayerManager;
    public static EventManager EventManager;
    public static ScoreboardManager ScoreboardManager;
    public static AxiomAPI Instance;
    public static String consoleUUID = "00000000-0000-0000-0000-000000000000";

    public void enable(){
        getDataFolder().mkdir();
        Instance = this;
        loadManagers();
        loadEvents();
        loadCommands();
        ScoreboardManager.setup();
        PlayerManager.loginAll();
    }

    public void disable(){
        PlayerManager.logoutAll();
    }

    private void loadEvents(){
        new CommandEvent();
        new ModuleEnableEvent();
        new JoinEvent();
        new QuitEvent();
        new InventoryClick();
        new MenuClick();
    }

    private void loadCommands(){
        new MapCommand();
    }

    private void loadManagers(){
        DatabaseManager = new DatabaseManager();
        CommandManager = new CommandManager();
        LanguageManager = new LanguageManager();
        PlayerManager = new PlayerManager();
        EventManager = new EventManager();
        ScoreboardManager = new ScoreboardManager();
    }
}
