package me.zodaxium.warps.commands;

import java.util.Iterator;
import java.util.Map.Entry;

import me.zodaxium.warps.Warps;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commandwarp implements CommandExecutor{

	Warps plugin;
	
	public Commandwarp(Warps plugin, String cmd){
		plugin.getCommand(cmd).setExecutor(this);
		this.plugin = plugin;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.hasPermission("zodaxium.admin")){
				if(args.length == 1){
					String warp = args[0].toLowerCase();
					if(plugin.warps.containsKey(warp)){
						if(plugin.warps.get(warp) != null){
							p.teleport(plugin.warps.get(warp));
							p.sendMessage(plugin.colorize("&9[&6Zodaxium&9] &aWarping to: &9" + args[0]));
						}else{
							p.sendMessage(plugin.colorize("&9[&6Zodaxium&9] &aInvalid warp location!"));
						}
					}else{
						p.sendMessage(plugin.colorize("&9[&6Zodaxium&9] &aUnknown warp!"));
					}
				}else if(!(args.length < 2)){
					Player t = plugin.getServer().getPlayer(args[1]);
					if(t != null){
						String warp = args[0].toLowerCase();
						if(plugin.warps.containsKey(warp)){
							if(plugin.warps.get(warp) != null){
								t.teleport(plugin.warps.get(warp));
								t.sendMessage(plugin.colorize("&9[&6Zodaxium&9] &aWarping to: &9" + args[0]));
								p.sendMessage(plugin.colorize("&9[&6Zodaxium&9] &aWarping player: &9" + t.getName() + " &ato: &9" + args[0]));
							}else{
								p.sendMessage(plugin.colorize("&9[&6Zodaxium&9] &aInvalid warp location!"));
							}
						}else{
							p.sendMessage(plugin.colorize("&9[&6Zodaxium&9] &aUnknown warp!"));
						}
					}
				}else{
					StringBuffer sb = new StringBuffer();
				    for(Iterator<Entry<String, Location>> it = plugin.warps.entrySet().iterator(); it.hasNext();){
				    	String name = it.next().getKey();
				    	sb.append(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase());
				    	if(it.hasNext()){
				    		sb.append("&a,&9 ");
				    	}
				    }
				    p.sendMessage(plugin.colorize("&9[&6Zodaxium&9] &aWarps: &9" + sb.toString()));
				}
			}
		}
		return true;
	}
}
