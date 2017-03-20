package com.icyrelic.messages.events;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.util.Format;
import com.icyrelic.messages.AxiomMessages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author IcyRelic
 */
public class PlayerJoin extends AxiomEvent {

	public PlayerJoin() { super(AxiomMessages.Instance); }

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onPlayerJoin(PlayerJoinEvent event){
		event.setJoinMessage("");
		Player p = event.getPlayer();
		boolean hasPlayedBefore = AxiomAPI.PlayerManager.playerExists(p.getUniqueId());
		boolean vanished = AxiomMessages.Instance.vanished.containsKey(p.getUniqueId());
		boolean doNewJoinMsg = AxiomMessages.Instance.getConfig().getBoolean("JoinMessage.New");
		boolean doJoinMsg = AxiomMessages.Instance.getConfig().getBoolean("JoinMessage.Returning");
		boolean doMOTD = AxiomMessages.Instance.getConfig().getBoolean("JoinMessage.MOTD");
		int totalPlayers = AxiomMessages.Instance.getConfig().getInt("TotalPlayers");

		if(vanished) p.sendMessage(Language.SILENT_JOIN.getMessage());

		if(!hasPlayedBefore) {
			totalPlayers++;
			AxiomMessages.Instance.getConfig().set("TotalPlayers", totalPlayers);
			AxiomMessages.Instance.saveConfig();

			if(doNewJoinMsg)
				AxiomAPI.Instance.getServer().broadcastMessage(
						Format.replace(
								Language.MESSAGE_FIRST_JOIN.getMessage(),
								new String[]{"%player%", "%total%"},
								new Object[] {p.getName(), totalPlayers}
						)
				);
		} else if(doJoinMsg) {
			AxiomAPI.Instance.getServer().broadcastMessage(
					Format.replace(
							Language.MESSAGE_JOIN.getMessage(),
							new String[]{"%player%"},
							new Object[] {p.getName()}
					)
			);
		}

		if(doMOTD) {
			String motd = Format.replace(Language.MESSAGE_JOIN.getMessage(), new String[]{"%player%"}, new String[] {p.getName()});
			String [] split = motd.split("/n");

			int y = 0;
			while(y < split.length){
				p.sendMessage(split[y]);
				y++;
			}
		}
	}

}
