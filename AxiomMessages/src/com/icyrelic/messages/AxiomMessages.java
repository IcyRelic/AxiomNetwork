package com.icyrelic.messages;

import com.icyrelic.api.AxiomModule;
import com.icyrelic.messages.commands.FakeCommand;
import com.icyrelic.messages.commands.MessageReloadCommand;
import com.icyrelic.messages.commands.SayCommand;
import com.icyrelic.messages.events.PlayerDeath;
import com.icyrelic.messages.events.PlayerJoin;
import com.icyrelic.messages.events.PlayerQuit;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class AxiomMessages extends AxiomModule {

	public static AxiomMessages Instance;
	public HashMap<UUID, Boolean> vanished = new HashMap<>();

	public void enable() {
		Instance = this;

		loadConfiguration();
		loadEvents();
		loadCommands();
		startAutoMessages();
	}

	/**
	 * Load all events
	 */
	private void loadEvents(){
		new PlayerJoin();
		new PlayerQuit();
		new PlayerDeath();
	}

	/**
	 * Load all commands
	 */
	private void loadCommands(){
		new MessageReloadCommand();
		new SayCommand();
		new FakeCommand();
	}

	/**
	 * Setup/Load config.yml
	 */
	private void loadConfiguration(){
	    getConfig().options().copyDefaults(true);
	    saveConfig();
	}

	/**
	 * Start AutoMessage runnable
	 */
	private void startAutoMessages(){
		
		if(getConfig().getBoolean("AutoMessages.Enabled")){
			Set<String> keys = getConfig().getConfigurationSection("AutoMessages.Messages").getKeys(false);
		    for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
		        String f = it.next();
		        
		        int x = getConfig().getInt("AutoMessages.Messages."+f+".interval");
				@SuppressWarnings("unused")
				BukkitTask task = new AutoMessages(this, f).runTaskTimer(this, 0, x);
		        
		        
		    }
			
		}
		
	}
}