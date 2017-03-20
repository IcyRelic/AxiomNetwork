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
public class SpawnCommand extends AxiomCommand {

    public SpawnCommand() {
        super("AxiomSpawn.Spawn", Language.INVALID_USAGE_SPAWN, "spawn");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 0) { invalidUsage(sender); return; }
        if(sender instanceof ConsoleCommandSender) { sendMessage(sender, Language.MUST_BE_PLAYER); return; }
        Player player = (Player) sender;

        player.teleport(AxiomSpawn.Instance.getSpawnLocation());
        sendMessage(sender, Language.TELEPORTED_TO_SPAWN, Language.SPAWN_PREFIX);

    }

}
