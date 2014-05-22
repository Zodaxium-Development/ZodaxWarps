package me.zodaxium.warps.commands;

import me.zodaxium.warps.Warps;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commandaddwarp implements CommandExecutor{

	Warps plugin;
	
	public Commandaddwarp(Warps plugin, String cmd){
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
					if(!plugin.warps.containsKey(warp)){
						plugin.warps.put(warp, p.getLocation());
						plugin.getConfig().set("Warps." + warp, serialize(p.getLocation()));
						plugin.saveConfig();
						p.sendMessage(plugin.colorize("&9[&6Zodaxium&9] &aAdding Warp: &9" + args[0]));
					}else{
						p.sendMessage(plugin.colorize("&9[&6Zodaxium&9] &aWarp already exists!"));
					}
				}
			}
		}
		return true;
	}
	
	private String serialize(Location loc){
		return loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getYaw() + ":" + loc.getPitch(); 
	}
}
