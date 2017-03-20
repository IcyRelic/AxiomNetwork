package com.icyrelic.api.commands.cmd;

import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.commands.sub.MapCMDCommand;
import com.icyrelic.api.language.Language;
import org.bukkit.command.CommandSender;

/**
 * @author IcyRelic
 */
public class MapCommand extends AxiomCommand {

    public MapCommand() {
        super("API.MAP", Language.INVALID_USAGE_APIMAP, "apimap");
        addSubCommand(new MapCMDCommand(this));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length < 1) { invalidUsage(sender); return; }
        callSubCommand(args[0], sender, args);
    }

}
