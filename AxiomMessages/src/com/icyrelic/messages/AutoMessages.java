package com.icyrelic.messages;


import com.icyrelic.api.language.Language;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class AutoMessages extends BukkitRunnable{

		private int last = 0;
		AxiomMessages plugin;
		private static String type = "";
		List<?> Messages = (null);
		boolean permission = false;
		String node = "";
	    public AutoMessages(AxiomMessages instance, String name) {
	       plugin = instance;
	       type = name;
	       Messages = plugin.getConfig().getList("AutoMessages.Messages."+type+".list");
	       permission = plugin.getConfig().getBoolean("AutoMessages.Messages."+type+".use_permission");
	       node = plugin.getConfig().getString("AutoMessages.Messages."+type+".permission");
	       
	    }
	 
	    public void run() {
	        // What you want to schedule goes here
	    		//Loading The Messages and Getting the next one
	    	    if (Messages.size() < last + 1) {last = 0;}
	    	    
	    	    //Setting The Next Message
	    	    ChatColor bold = ChatColor.BOLD;
	    	    ChatColor italic = ChatColor.ITALIC;
	    	    ChatColor underline = ChatColor.UNDERLINE;
	    	    ChatColor magic = ChatColor.MAGIC;
	    	    ChatColor strike = ChatColor.STRIKETHROUGH;
	    	    ChatColor reset = ChatColor.RESET;
	    	    
	    	    String msg = (Messages.get(last).toString().replaceAll("(&([a-f0-9]))", "\u00A7$2").replace("&l", bold+"").replace("&r", reset+"").replace("&o", italic+"").replace("&n", underline+"").replace("&k", magic+"").replace("&m", strike+""));
	    	    String head = Language.AUTO_MESSAGE_PREFIX.getMessage();
	    	    String [] split = msg.split("/n");
	    	    boolean newlineprefix = plugin.getConfig().getBoolean("AutoMessages.NewLinePrefix");
	    	    int y = 0;
	    	    boolean first = true;
	    	    
	    	    //Sending Message

	    	    
	    	    sendAutoMessage(head, split, newlineprefix, y, first);    	    
	    	    
	    	    
	    	    
	    	    last++;
	    		
	        
	    }
	    
	    
	    private void sendAutoMessage(String head, String [] split, boolean newlineprefix, int y, boolean first){
	    	
    	    while(y < split.length){
    	    		
    	    		for(Player p : plugin.getServer().getOnlinePlayers()){
    	    			
    	    			if(!p.hasPermission("AxiomMessages.AutoMessages.Hide")){

							if(permission && !p.hasPermission(node)) continue;

							if(first){
								first = false;
								p.sendMessage(head + split[y]);
							}else{
								if(newlineprefix){
									p.sendMessage(head + split[y]);
								}else{
									p.sendMessage(split[y]);
								}
							}
    	    			}
    	    		}
				  y++;
			}
	    }
}
