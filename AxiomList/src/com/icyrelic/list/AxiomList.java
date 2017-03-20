package com.icyrelic.list;

import com.icyrelic.api.AxiomModule;
import com.icyrelic.list.commands.ListCommand;

/**
 * @author IcyRelic
 */
public class AxiomList extends AxiomModule {

    public void enable() {
        loadCommands();
    }

    private void loadCommands(){
        new ListCommand();
    }
}
