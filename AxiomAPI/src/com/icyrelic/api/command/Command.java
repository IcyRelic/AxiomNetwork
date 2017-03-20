package com.icyrelic.api.command;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.language.Language;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author IcyRelic
 */
public abstract class Command implements IAxiomCommand {

    public String[] aliases;
    public List<AxiomSubCommand> subCommands;
    public String permission;
    public Language usage;

    public Command(String permission, Language usage, String... aliases){
        this.aliases = aliases;
        this.permission = permission;
        this.usage = usage;
        this.subCommands = new ArrayList<>();
    }

    public void call(CommandSender sender, String[] args){
        if (canExecute(sender)) execute(sender, args);
        else noPermission(sender);
    }

    public void callSubCommand(String alias, CommandSender sender, String[] args){
        Optional<AxiomSubCommand> cmd = subCommands.stream().filter(subCommand -> subCommand.hasAlias(alias)).findFirst();
        if(cmd.isPresent()) cmd.get().execute(sender, args);
        else {
            sendMessage(sender, Language.COMMAND_NOT_FOUND);
            invalidUsage(sender);
        }
    }

    public boolean hasAlias(String s){
        return Arrays.asList(aliases).contains(s);
    }

    protected void addSubCommand(AxiomSubCommand subCommand){
        this.subCommands.add(subCommand);
    }

    protected void invalidUsage(CommandSender sender){
        sendMessage(sender, usage);
    }

    protected void sendMessage(CommandSender sender, Language message){
        sender.sendMessage(message.getMessage());
    }

    protected void broadcastMessage(String message) {
        AxiomAPI.Instance.getServer().broadcastMessage(message);
    }

    protected void sendMessage(CommandSender sender, String message){
        sender.sendMessage(message);
    }

    protected void sendMessage(CommandSender sender, Language message, Language prefix){
        sender.sendMessage(message.getMessage(prefix));
    }

    private boolean canExecute(CommandSender sender){
        if(sender instanceof ConsoleCommandSender) return true;
        Player player = (Player) sender;
        return AxiomAPI.PlayerManager.getAxiomPlayer(player.getUniqueId()).hasPermission(permission);
    }

    private void noPermission(CommandSender sender){
        sendMessage(sender, Language.INSUFFICIENT_ACCESS);
    }

    protected static String getFinalArg(final String[] args, final int start)
    {
        final StringBuilder bldr = new StringBuilder();
        for (int i = start; i < args.length; i++)
        {
            if (i != start)
            {
                bldr.append(" ");
            }
            bldr.append(args[i]);
        }
        return bldr.toString();
    }
}
