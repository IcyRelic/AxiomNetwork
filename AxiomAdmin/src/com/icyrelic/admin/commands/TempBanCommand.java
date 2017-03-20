package com.icyrelic.admin.commands;

import com.icyrelic.admin.AxiomAdmin;
import com.icyrelic.admin.managers.BanType;
import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.util.Format;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author IcyRelic
 */
public class TempBanCommand extends AxiomCommand {

    public TempBanCommand() {
        super("AxiomAdmin.TempBan", Language.INVALID_USAGE_TEMP_BAN, "tempban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length < 4) { invalidUsage(sender); return; }
        UUID uuid = AxiomAPI.PlayerManager.getPlayerUUID(args[0]);
        UUID admin = sender instanceof Player ? ((Player) sender).getUniqueId() : UUID.fromString(AxiomAPI.consoleUUID);
        if(!isInt(args[1])) { sendMessage(sender, Language.INVALID_INTEGER); return; }
        if(!validTime(args[2])) { sendMessage(sender, Language.INVALID_TIME); return; }

        long unban = System.currentTimeMillis() + convert(Integer.parseInt(args[1]), args[2]);
        String dateStr = "";

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d yyyy h:mm a z");
        Date date = new Date(unban);
        dateStr = sdf.format(date);

        if(!AxiomAdmin.AdminManager.createBan(uuid, admin, BanType.TEMP, getFinalArg(args, 3), unban)) { sendMessage(sender, Language.ERROR_USER_BANNED); return; }

        if(Bukkit.getOfflinePlayer(uuid).isOnline()) Bukkit.getPlayer(uuid).kickPlayer(Format.replace(Language.TEMP_BANNED_JOIN.getMessage(),
                new String[] {"%player%", "%admin%", "%reason%", "%unban%"},
                new Object[] {Bukkit.getOfflinePlayer(uuid).getName(), sender instanceof Player ? sender.getName() : "Console", getFinalArg(args, 3), dateStr} ) );


        broadcastMessage( Format.replace(Language.TEMP_BANNED_PLAYER.getMessage(),
                new String[] {"%player%", "%admin%", "%reason%", "%unban%", "%time%"},
                new Object[] {Bukkit.getOfflinePlayer(uuid).getName(), sender instanceof Player ? sender.getName() : "Console", getFinalArg(args, 3), dateStr, args[1] + " " + args[2].toLowerCase() + "(s)"} ) );

    }

    private int convert(int x, String time){
        if(time.equalsIgnoreCase("minute")) return (1000 * 60) * x;
        if(time.equalsIgnoreCase("hour")) return ((1000 * 60) * 60) * x;
        if(time.equalsIgnoreCase("day")) return (((1000 * 60) * 60) * 24 ) * x;

        return 0;
    }

    private boolean isInt(String s){
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException ex){
            return false;
        }

        return true;
    }

    private boolean validTime(String s){
        return s.equalsIgnoreCase("minute") || s.equalsIgnoreCase("hour") || s.equalsIgnoreCase("day");
    }
}
