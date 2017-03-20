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
public class SetHomeCommand extends AxiomCommand {

    public SetHomeCommand() {
        super("AxiomHome.SetHome", Language.INVALID_USAGE_SET_HOME, "sethome");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length > 1) { invalidUsage(sender); return; }
        if(sender instanceof ConsoleCommandSender) { sendMessage(sender, Language.MUST_BE_PLAYER); return; }
        Player player = (Player) sender;
        String name = args.length == 0 ? "home" : args[0];
        if(AxiomHome.HomeManager.hasMaxHomes(player)) { player.sendMessage(Language.MAX_HOMES.getMessage(Language.HOME_PREFIX)); return; }

        setHome(player, name);


        sendMessage(player, Format.replace(Language.HOME_SET.getMessage(Language.HOME_PREFIX),
                new String[]{"%name%"}, new Object[]{name}));
    }

    private void setHome(Player player, String name){
        AxiomHome.HomeManager.addHome(player, name);
    }
}
