package com.icyrelic.messages.commands;

import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.language.Language;
import com.icyrelic.messages.AxiomMessages;
import org.bukkit.command.CommandSender;

/**
 * @author IcyRelic
 */
public class MessageReloadCommand extends AxiomCommand {

    public MessageReloadCommand() {
        super("AxiomMessages.Reload", Language.INVALID_USAGE_AMRELOAD, "amreload");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        AxiomMessages.Instance.reloadConfig();

        sender.sendMessage(Language.MESSAGE_RELOAD.getMessage(Language.MESSAGES_PREFIX));
    }

}
