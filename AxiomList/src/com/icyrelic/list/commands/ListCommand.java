package com.icyrelic.list.commands;

import com.icyrelic.api.AxiomAPI;
import com.icyrelic.api.command.AxiomCommand;
import com.icyrelic.api.language.Language;
import com.icyrelic.api.player.AxiomPlayer;
import com.icyrelic.api.util.Format;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.*;

/**
 * @author IcyRelic
 */
public class ListCommand extends AxiomCommand {

    public ListCommand() {
        super("AxiomList.List", Language.INVALID_USAGE_LIST, "list", "online");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Collection<String> groups = PermissionsEx.getPermissionManager().getGroupNames();

        HashMap<String, List<Player>> online = new HashMap<>();

        groups.forEach(s -> online.put("AxiomList.List." + s.toLowerCase(), new ArrayList<>()));

        Bukkit.getOnlinePlayers().forEach(p -> {
            AxiomPlayer axiomPlayer = AxiomAPI.PlayerManager.getAxiomPlayer(p.getUniqueId());

            groups.forEach(s -> {
                if (axiomPlayer.hasPermission("AxiomList.List." + s.toLowerCase()))
                    online.get("AxiomList.List." + s.toLowerCase()).add(p);
            });
        });


        sendMessage(sender, Format.replace(Language.LIST_HEAD.getMessage()
                , new String[]{"%online%", "%max%"}
                , new Object[]{Bukkit.getOnlinePlayers().size(), Bukkit.getMaxPlayers()}));

        groups.forEach(s -> {
            StringBuilder sb = new StringBuilder();
            online.get("AxiomList.List." + s.toLowerCase()).forEach(p -> {
                if(!sb.toString().isEmpty()) sb.append(", ");
                sb.append(p.getName());
            });
            if(!sb.toString().isEmpty()) {
                sendMessage(sender, Format.replace(Language.LIST_FORMAT.getMessage()
                        , new String[]{"%prefix%" , "%rank%", "%users%"}
                        , new Object[]{Format.colorString(PermissionsEx.getPermissionManager().getGroup(s).getPrefix()), s, sb.toString()}));
            }
        });

    }

    private String firstCap(String str){
        String cap = str.substring(0, 1).toUpperCase();
        String rest = str.substring(1, str.length()).toLowerCase();

        return cap+rest;
    }
}
