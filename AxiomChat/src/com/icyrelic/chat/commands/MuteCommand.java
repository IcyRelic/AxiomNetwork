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

import java.util.Arrays;

/**
 * @author IcyRelic
 */
public class MuteCommand extends AxiomCommand {

    public MuteCommand() { super("AxiomChat.Mute", Language.INVALID_USAGE_MUTE, "mute"); }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length < 2) { invalidUsage(sender); return; }
        if(!AxiomAPI.PlayerManager.playerExists(args[0])){ sendMessage(sender, Language.PLAYER_NOT_FOUND); return; }

        StringBuilder reason = new StringBuilder();
        Arrays.asList(args).subList(1, args.length).forEach(reason.append(" ")::append);
        OfflinePlayer target = AxiomAPI.PlayerManager.getPlayerFromName(args[0]);
        String finalReason = reason.toString().substring(1, reason.length());
        AxiomChat.ChatManager.mute(target, finalReason);
        String admin = sender instanceof Player ? sender.getName() : "Console";

        sendMessage(sender, Format.replace(Language.MUTE_SENDER.getMessage(Language.CHAT_PREFIX),
                new String[]{"%player%", "%reason%"}, new String[]{target.getName(), finalReason}));

        if(target.isOnline()) ((Player) target).sendMessage(Format.replace(Language.MUTE_TARGET.getMessage(Language.CHAT_PREFIX),
                new String[]{"%player%", "%reason%"}, new String[]{admin, finalReason}));

        Chat.sendGlobal(Format.replace(Language.MUTE_GLOBAL.getMessage(Language.CHAT_PREFIX),
                new String[]{"%player%", "%target%", "%reason%"}, new String[]{admin, target.getName(), finalReason}));





    }
}
