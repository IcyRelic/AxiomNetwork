package com.icyrelic.admin.events;

import com.icyrelic.admin.AxiomAdmin;
import com.icyrelic.admin.managers.BanType;
import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.event.AxiomEvent;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.util.Format;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author IcyRelic
 */
public class JoinEvent extends AxiomEvent {

    public JoinEvent() {
        super(AxiomAdmin.Instance);
    }

    @EventHandler
    public void onPlayerJoin(AsyncPlayerPreLoginEvent event){
        HashMap<String, Object> ban = AxiomAdmin.AdminManager.getBan(event.getUniqueId());

        if(ban == null) return;

        Language lang = Language.BANNED_JOIN;

        BanType type = BanType.valueOf(ban.get("Type").toString());

        switch (type){
            case BAN:
                lang = Language.BANNED_JOIN;
                break;
            case TEMP:
                lang = Language.TEMP_BANNED_JOIN;
                break;
            case PERM:
                lang = Language.PERM_BANNED_JOIN;
                break;
            case UNBAN:
                return;
        }



        String name = AxiomAPI.PlayerManager.getOfflineAxiomPlayer(event.getUniqueId()).getOfflinePlayer().getName();


        String admin = ban.get("Admin").equals(AxiomAPI.consoleUUID) ? "Console" : AxiomAPI.PlayerManager.getOfflineAxiomPlayer(ban.get("Admin").toString()).getOfflinePlayer().getName();
        String dateStr = "";

        if(type.equals(BanType.TEMP)) {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM d yyyy h:mm a z");
            Date date = new Date(Long.parseLong((String) ban.get("Unban")));
            dateStr = sdf.format(date);
        }

        event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, Format.replace(lang.getMessage(),
                new String[] {"%player%", "%admin%", "%reason%", "%unban%"},
                new Object[] {name, admin, ban.get("Reason"), dateStr} ));
    }


}
