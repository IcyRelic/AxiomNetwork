package com.icyrelic.messages.commands;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.command.AxiomSubCommand;
import com.icyrelic.api.command.Command;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.util.Format;
import com.icyrelic.messages.AxiomMessages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author IcyRelic
 */
public class FakeJoinCommand extends AxiomSubCommand {

    public FakeJoinCommand(Command parent) {
        super(parent, "join");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        String name = AxiomMessages.Instance.getConfig().getBoolean("UseNicknames") ? p.getDisplayName() : p.getName();

        AxiomAPI.Instance.getServer().broadcastMessage(Format.replace(Language.MESSAGE_JOIN.getMessage(), new String[]{"%player%"}, new String[] {name}));
    }
}
