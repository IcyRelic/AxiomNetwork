package com.icyrelic.chat;

import com.icyrelic.api.AxiomModule;
import com.icyrelic.chat.commands.MuteCommand;
import com.icyrelic.chat.commands.UnMuteCommand;
import com.icyrelic.chat.events.ChatEvent;
import com.icyrelic.chat.manager.ChatManager;

/**
 * @author IcyRelic
 */
public class AxiomChat extends AxiomModule {

    public static AxiomChat Instance;
    public static ChatManager ChatManager;

    public void enable() {
        loadManagers();
        loadConfiguration();
        loadEvents();
        loadCommands();
    }

    private void loadEvents(){
        new ChatEvent();
    }

    private void loadCommands(){
        new MuteCommand();
        new UnMuteCommand();
    }

    private void loadManagers(){
        Instance = this;
        ChatManager = new ChatManager();
        ChatManager.setup();
    }

    private void loadConfiguration(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
