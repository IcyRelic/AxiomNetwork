package com.icyrelic.admin.commands;

import com.icyrelic.admin.AxiomAdmin;
import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.player.AxiomPlayer;
import com.icyrelic.api.util.Format;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author IcyRelic
 */
public class UnbanCommand extends AxiomCommand {

    public UnbanCommand() {
        super("AxiomAdmin.UnBan", Language.INVALID_USAGE_UNBAN, "unban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 1) { invalidUsage(sender); return; }
        if(!AxiomAPI.PlayerManager.playerExists(args[0])) { sendMessage(sender, Language.PLAYER_NOT_FOUND); return; }

        AxiomPlayer axiomPlayer = AxiomAPI.PlayerManager.getOfflineAxiomPlayer(args[0]);
        String admin = sender instanceof Player ? sender.getName() : "Console";

        if(!AxiomAdmin.AdminManager.isBanned(axiomPlayer.getUUID())) { sendMessage(sender, Language.ERROR_USER_NOT_BANNED); return; }

        if(!AxiomAdmin.AdminManager.unban(axiomPlayer.getUUID())) { sendMessage(sender, Language.ERROR_UNBAN); return; }

        broadcastMessage(Format.replace(Language.UNBAN_PLAYER.getMessage(),
                new String[] {"%player%", "%admin%"},
                new Object[] {axiomPlayer.getOfflinePlayer().getName(), admin}
                ));
    }

}
