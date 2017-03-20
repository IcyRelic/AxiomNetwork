package com.icyrelic.chat.events;

import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.language.Language;
import com.icyrelic.chat.AxiomChat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 * @author IcyRelic
 */
public class ChatEvent extends AxiomEvent {

    public ChatEvent(){
        super(AxiomChat.Instance);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if(AxiomChat.ChatManager.isPlayerMuted(event.getPlayer())){
            event.getPlayer().sendMessage(Language.PLAYER_MUTED.getMessage(Language.CHAT_PREFIX));
            event.setCancelled(true);
        }


        PermissionUser user = PermissionsEx.getUser(event.getPlayer());
        String prefix = user.getPrefix();
        String suffix = user.getSuffix();
        String uPrefix = user.getOwnPrefix();
        String uSuffix = user.getOwnSuffix();

        String format = AxiomChat.Instance.getConfig().getString("format")
                .replace("%prefix%", prefix+"")
                .replace("%suffix%", suffix+"")
                .replace("%ownprefix%", uPrefix+"")
                .replace("%ownsuffix%", uSuffix+"")
                .replace("%name%", event.getPlayer().getDisplayName())
                .replace("%message%", event.getMessage().replace("%", "%%"));
        event.setFormat(ChatColor.translateAlternateColorCodes('&', format));
    }

}
