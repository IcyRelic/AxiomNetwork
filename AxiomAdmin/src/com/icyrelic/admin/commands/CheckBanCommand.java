package com.icyrelic.admin.commands;

import com.icyrelic.admin.AxiomAdmin;
import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.player.AxiomPlayer;
import com.icyrelic.api.util.Format;
import org.bukkit.command.CommandSender;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author IcyRelic
 */
public class CheckBanCommand extends AxiomCommand {

    public CheckBanCommand() {
        super("AxiomAdmin.CheckBan", Language.INVALID_USAGE_CHECK_BAN, "checkban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 1) { invalidUsage(sender); return; }

        UUID uuid = AxiomAPI.PlayerManager.getPlayerUUID(args[0]);
        AxiomPlayer axiomPlayer = AxiomAPI.PlayerManager.getOfflineAxiomPlayer(uuid);
        HashMap<String, Object> ban = AxiomAdmin.AdminManager.getBan(uuid);

        String name = axiomPlayer.getOfflinePlayer().getName();
        boolean banned = AxiomAdmin.AdminManager.isBanned(uuid);
        int bans = AxiomAdmin.AdminManager.getNumBans(uuid);
        int kicks = AxiomAdmin.AdminManager.getNumKicks(uuid);
        int warnings = AxiomAdmin.AdminManager.getNumWarnings(uuid);
        int reports = AxiomAdmin.AdminManager.getNumReports(uuid);


        StringBuilder sb = new StringBuilder();

        sb.append(Format.replace(Language.BAN_INFO.getMessage(),
                new String[] {"%player%", "%banned%"},
                new Object[] {name, banned}));

        sb.append(Format.replace(Language.BAN_TOTALS.getMessage(),
                new String[] {"%bans%", "%kicks%", "%warnings%", "%reports%"},
                new Object[] {bans, kicks, warnings, reports}));

        if(bans == 0)
            sb.append(Language.NO_RECENT_BAN.getMessage());
        else{
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM d yyyy h:mm a z");
            Date date = new Date(Long.parseLong((String) ban.get("Unban")));

            String type = ban.get("OType").toString();
            String admin = ban.get("Admin").toString().equals(AxiomAPI.consoleUUID) ? "Console" : AxiomAPI.PlayerManager.getOfflineAxiomPlayer(UUID.fromString(ban.get("Admin").toString())).getOfflinePlayer().getName();
            String until = ban.get("Unban").toString().equals("0") ? "Forever" : sdf.format(date);
            String reason = ban.get("Reason").toString();

            sb.append(Format.replace(Language.RECENT_BAN.getMessage(),
                    new String[] {"%type%", "%admin%", "%until%", "%reason%"},
                    new Object[] {type, admin, until, reason}));
        }



        sendMessage(sender, sb.toString());

    }

}
