package com.icyrelic.chat.commands;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.util.Chat;
import com.icyrelic.api.util.Format;
import com.icyrelic.chat.AxiomChat;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author IcyRelic
 */
public class UnMuteCommand extends AxiomCommand {

    public UnMuteCommand() { super("AxiomChat.UnMute", Language.INVALID_USAGE_UNMUTE, "unmute"); }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 1) { invalidUsage(sender); return; }

        if(!AxiomAPI.PlayerManager.playerExists(args[0])){ sendMessage(sender, Language.PLAYER_NOT_FOUND); return; }

        OfflinePlayer target = AxiomAPI.PlayerManager.getPlayerFromName(args[0]);
        AxiomChat.ChatManager.unmute(target);
        String admin = sender instanceof Player ? sender.getName() : "Console";

        sendMessage(sender, Format.replace(Language.UNMUTE_SENDER.getMessage(Language.CHAT_PREFIX),
                new String[]{"%player%"}, new String[]{target.getName()}));

        if(target.isOnline()) ((Player) target).sendMessage(Format.replace(Language.UNMUTE_TARGET.getMessage(Language.CHAT_PREFIX),
                new String[]{"%player%"}, new String[]{admin}));

        Chat.sendGlobal(Format.replace(Language.UNMUTE_GLOBAL.getMessage(Language.CHAT_PREFIX),
                new String[]{"%player%", "%target%"}, new String[]{admin, target.getName()}));




    }
}
