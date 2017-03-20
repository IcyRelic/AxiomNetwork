package com.icyrelic.messages.commands;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.language.Language;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SayCommand extends AxiomCommand {

	public SayCommand() {
		super("AxiomMessages.Say", Language.INVALID_USAGE_SAY, "say");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length < 1) { invalidUsage(sender); return; }
		String name = sender instanceof Player ? ((Player) sender).getPlayer().getName() : "Console";

		AxiomAPI.Instance.sendConsoleMessage(Language.MESSAGES_PREFIX + " /say was sent by " + name);

		AxiomAPI.Instance.getServer().broadcastMessage(Language.SAY_PREFIX.getMessage() + " " + getFinalArg(args, 0));
	}




}
