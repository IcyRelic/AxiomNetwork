package com.icyrelic.api.manager.types;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.manager.Manager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author IcyRelic
 */
public class CommandManager extends Manager {

    HashMap<String, AxiomCommand> commands = new HashMap<>();

    public void addCommand(String name, AxiomCommand command){
        if(!commands.containsKey(name))
            commands.put(name, command);
        AxiomAPI.Instance.sendConsoleMessage(ChatColor.GREEN + "Registering Command: " + ChatColor.YELLOW + name + ChatColor.GRAY + " for " + ChatColor.YELLOW + command.getClass().getSimpleName());

    }

    public void removeCommand(String name, AxiomCommand command){
        if(commands.containsKey(name))
            commands.remove(name, command);
    }

    public List<AxiomCommand> getCommands(){
        List<AxiomCommand> axiomCommands = new ArrayList<>();
        commands.keySet().forEach(key -> {
            if(!axiomCommands.contains(commands.get(key)))
                axiomCommands.add(commands.get(key));
        });

        return axiomCommands;
    }

    public boolean process(CommandSender sender, String cmd){
        List<String> args = parse(cmd);
        if(!commands.containsKey(args.get(0).toLowerCase())) { return false; }
        AxiomCommand command = commands.get(args.get(0).toLowerCase());
        command.call(sender, args.subList(1, args.size()).toArray(new String[args.size()-1]));
        return true;
    }


    private List<String> parse(String commandString){
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, commandString.split(" "));
        if (strings.size() > 0) strings.set(0, strings.get(0).replace("/", ""));
        return strings;
    }


}
