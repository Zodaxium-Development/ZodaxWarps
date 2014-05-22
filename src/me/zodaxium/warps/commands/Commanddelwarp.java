package me.zodaxium.warps.commands;

import me.zodaxium.warps.Warps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commanddelwarp implements CommandExecutor{

	Warps plugin;
	
	public Commanddelwarp(Warps plugin, String cmd){
		plugin.getCommand(cmd).setExecutor(this);
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.hasPermission("zodaxium.admin")){
				if(!(args.length < 1)){
					String warp = args[0].toLowerCase();
					if(plugin.warps.containsKey(warp)){
						plugin.warps.remove(warp);
						plugin.getConfig().set("Warps." + warp, null);
						plugin.saveConfig();
						p.sendMessage(plugin.colorize("&9[&6Zodaxium&9] &aDeleted Warp: &9" + args[0]));
					}else{
						p.sendMessage(plugin.colorize("&9[&6Zodaxium&9] &aUnknown warp!"));
					}
				}
			}
		}
		return true;
	}
}
