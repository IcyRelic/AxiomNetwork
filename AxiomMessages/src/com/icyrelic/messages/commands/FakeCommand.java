package com.icyrelic.messages.commands;

import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.language.Language;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class FakeCommand extends AxiomCommand {

	public FakeCommand() {
		super("AxiomMessages.Fake", Language.INVALID_USAGE_FAKE, "fake");
		addSubCommand(new FakeJoinCommand(this));
		addSubCommand(new FakeQuitCommand(this));
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length < 1) { invalidUsage(sender); return; }
		if(sender instanceof ConsoleCommandSender) { sendMessage(sender, Language.MUST_BE_PLAYER); return; }
		callSubCommand(args[0], sender, args);
	}
	
}
