package com.icyrelic.admin.commands;

import com.icyrelic.admin.AxiomAdmin;
import com.icyrelic.admin.managers.BanType;
import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.util.Format;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author IcyRelic
 */
public class BanCommand extends AxiomCommand {

    public BanCommand() {
        super("AxiomAdmin.Ban", Language.INVALID_USAGE_BAN, "ban");
    }

    public void execute(CommandSender sender, String[] args) {
        if(args.length < 2) { invalidUsage(sender); return; }
        UUID uuid = AxiomAPI.PlayerManager.getPlayerUUID(args[0]);
        UUID admin = sender instanceof Player ? ((Player) sender).getUniqueId() : UUID.fromString(AxiomAPI.consoleUUID);
        if(!AxiomAdmin.AdminManager.createBan(uuid, admin, BanType.BAN, getFinalArg(args, 1), 0)) { sendMessage(sender, Language.ERROR_USER_BANNED); return; }


        if(Bukkit.getOfflinePlayer(uuid).isOnline()) Bukkit.getPlayer(uuid).kickPlayer(Format.replace(Language.BANNED_JOIN.getMessage(),
                new String[] {"%player%", "%admin%", "%reason%"},
                new Object[] {Bukkit.getOfflinePlayer(uuid).getName(), sender instanceof Player ? sender.getName() : "Console", getFinalArg(args, 1)} ) );


        broadcastMessage( Format.replace(Language.BANNED_PLAYER.getMessage(),
                new String[] {"%player%", "%admin%", "%reason%"},
                new Object[] {Bukkit.getOfflinePlayer(uuid).getName(), sender instanceof Player ? sender.getName() : "Console", getFinalArg(args, 1)} ) );

    }
}
