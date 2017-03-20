package com.icyrelic.messages.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.util.Format;
import com.icyrelic.messages.AxiomMessages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.MetadataValue;

public class PlayerQuit extends AxiomEvent {
	
	public PlayerQuit() { super(AxiomMessages.Instance); }


	@EventHandler(priority = EventPriority.LOWEST)
	private void onPlayerQuit(PlayerQuitEvent event){
		event.setQuitMessage("");
		Player p = event.getPlayer();
		boolean vanished = isVanished(p);
		boolean doQuitMessage = AxiomMessages.Instance.getConfig().getBoolean("QuitMessage");

		if(vanished) {
			if(AxiomMessages.Instance.vanished.containsKey(p.getUniqueId())){
				AxiomMessages.Instance.vanished.remove(p.getUniqueId());
			}
			AxiomMessages.Instance.vanished.put(p.getUniqueId(), true);
		}

		if(doQuitMessage && !vanished)
			AxiomAPI.Instance.getServer().broadcastMessage(Format.replace(Language.MESSAGE_QUIT.getMessage(), new String[]{"%player%"}, new String[] {p.getName()}));
	}

	private boolean isVanished(Player p) {
		for(MetadataValue value : p.getMetadata("vanished")) { 
			if (value.asBoolean()) { 
				return true;
			} 
		} return false; 
	}

}
