package com.icyrelic.api.commands.sub;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.command.AxiomSubCommand;
import com.icyrelic.api.command.Command;
import com.icyrelic.api.language.Language;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author IcyRelic
 */
public class MapCMDCommand extends AxiomSubCommand {

    public MapCMDCommand(Command parent) {
        super(parent, "commands");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        List<AxiomCommand> commands = AxiomAPI.CommandManager.getCommands();
        YamlConfiguration config;
        File file = new File(AxiomAPI.Instance.getDataFolder() + File.separator + "CommandMap.yml");


        if(file.exists()) file.delete();

        try {
            file.createNewFile();
        } catch (IOException ex){
            ex.printStackTrace();
        }

        config = YamlConfiguration.loadConfiguration(file);
        commands.forEach(axiomCommand -> {
            String name = axiomCommand.getClass().getSimpleName();
            String perm = axiomCommand.permission;
            String[] aliases = axiomCommand.aliases;
            config.set(name + ".permission", perm);
            config.set(name + ".aliases", aliases);
        });

        try {
            config.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }

        sendMessage(sender, Language.COMMANDS_MAPPED);
    }
}
