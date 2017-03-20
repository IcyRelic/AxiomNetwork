package com.icyrelic.admin.commands;

import com.icyrelic.admin.AxiomAdmin;
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
public class WarnCommand extends AxiomCommand {

    public WarnCommand() {
        super("AxiomAdmin.Warn", Language.INVALID_USAGE_WARN, "warn");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length < 2) { invalidUsage(sender); return; }
        UUID uuid = AxiomAPI.PlayerManager.getPlayerUUID(args[0]);
        UUID admin = sender instanceof Player ? ((Player) sender).getUniqueId() : UUID.fromString(AxiomAPI.consoleUUID);

        if(!Bukkit.getOfflinePlayer(uuid).isOnline()) { sendMessage(sender, Language.PLAYER_NOT_FOUND); return; }

        AxiomAdmin.AdminManager.createWarning(uuid, admin, getFinalArg(args, 1));


        broadcastMessage( Format.replace(Language.WARNING.getMessage(),
                new String[] {"%player%", "%admin%", "%reason%"},
                new Object[] {Bukkit.getOfflinePlayer(uuid).getName(), sender instanceof Player ? sender.getName() : "Console", getFinalArg(args, 1)} ) );

    }

}
