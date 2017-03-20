package com.icyrelic.api.command;

/**
 * @author IcyRelic
 */
public abstract class AxiomSubCommand extends Command {

    private Command parent;

    public AxiomSubCommand(Command parent, String permission, String alias) {
        super(permission, parent.usage, alias);
        this.parent = parent;
    }

    public AxiomSubCommand(Command parent, String alias) {
        super(parent.permission, parent.usage, alias);
        this.parent = parent;
    }

}
