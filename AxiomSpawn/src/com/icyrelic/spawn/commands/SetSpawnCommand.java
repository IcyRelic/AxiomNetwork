package com.icyrelic.spawn.commands;

import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.language.Language;
import com.icyrelic.spawn.AxiomSpawn;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * @author IcyRelic
 */
public class SetSpawnCommand extends AxiomCommand {

    public SetSpawnCommand() {
        super("AxiomSpawn.SetSpawn", Language.INVALID_USAGE_SET_SPAWN, "setspawn");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 0) { invalidUsage(sender); return; }
        if(sender instanceof ConsoleCommandSender) { sendMessage(sender, Language.MUST_BE_PLAYER); return; }
        Player player = (Player) sender;

        AxiomSpawn.Instance.setSpawnLocation(player.getLocation());
        player.getLocation().getWorld().setSpawnLocation
                (player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());

        sendMessage(sender, Language.SPAWN_SET, Language.SPAWN_PREFIX);
    }

}
