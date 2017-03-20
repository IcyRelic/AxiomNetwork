package com.icyrelic.api.command;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.language.Language;

import java.util.Arrays;

/**
 * @author IcyRelic
 */
public abstract class AxiomCommand extends Command {

    public AxiomCommand(String permission, Language usage, String... aliases){
        super(permission, usage, aliases);
        Arrays.asList(aliases).forEach(cmd -> AxiomAPI.CommandManager.addCommand(cmd, this));
    }
}
