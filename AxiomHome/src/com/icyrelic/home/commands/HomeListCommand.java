package com.icyrelic.home.commands;

import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.util.Format;
import com.icyrelic.home.AxiomHome;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * @author IcyRelic
 */
public class HomeListCommand extends AxiomCommand {

    public HomeListCommand() {
        super("AxiomHome.HomeList", Language.INVALID_USAGE_HOME_LIST, "homes");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 0) { invalidUsage(sender); return; }
        if(sender instanceof ConsoleCommandSender) { sendMessage(sender, Language.MUST_BE_PLAYER); return; }
        Player player = (Player) sender;
        if(AxiomHome.HomeManager.getHomeNumber(player) == 0) { sendMessage(sender, Language.NO_HOMES, Language.HOME_PREFIX); return;}

        sendMessage(sender, Format.replace(Language.HOME_LIST.getMessage(Language.HOME_PREFIX),
                new String[]{"%homes%"}, new Object[]{AxiomHome.HomeManager.getHomes(player)}));
    }
}
