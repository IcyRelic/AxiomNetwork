package com.icyrelic.home.commands;

import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.language.Language;
import com.icyrelic.home.AxiomHome;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * @author IcyRelic
 */
public class HomeCommand extends AxiomCommand {

    public HomeCommand() {
        super("AxiomHome.Home", Language.INVALID_USAGE_HOME, "home");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length > 1) { invalidUsage(sender); return; }
        if(sender instanceof ConsoleCommandSender) { sendMessage(sender, Language.MUST_BE_PLAYER); return; }
        Player player = (Player) sender;
        String name = args.length == 0 ? "home" : args[0];
        if(!AxiomHome.HomeManager.hasHome(player, name)) { sendMessage(sender, Language.HOME_NOT_FOUND, Language.HOME_PREFIX); return;}

        player.teleport(AxiomHome.HomeManager.getHome(player, name).add(0.5, 0, 0.5));
        sendMessage(sender, Language.TELEPORTED_HOME, Language.HOME_PREFIX);
    }
}
