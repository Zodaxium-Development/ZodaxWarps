package me.zodaxium.warps;

import java.util.HashMap;

import me.zodaxium.warps.commands.Commandaddwarp;
import me.zodaxium.warps.commands.Commanddelwarp;
import me.zodaxium.warps.commands.Commandwarp;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Warps extends JavaPlugin{

	public HashMap<String, Location> warps;
	
	public void onEnable(){
		saveDefaultConfig();
		
		generateWarps();
		
		new Commandaddwarp(this, "addwarp");
		new Commanddelwarp(this, "delwarp");
		new Commandwarp(this, "warp");
	}
	
	public void generateWarps(){
		warps = new HashMap<String, Location>();
		for(String key : getConfig().getConfigurationSection("Warps").getKeys(false)){
			String[] args = getConfig().getString("Warps." + key).split(":");
			Location loc = ((getServer().getWorld(args[0]) != null) ? new Location(getServer().getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Float.parseFloat(args[4]), Float.parseFloat(args[5])) : null);
			warps.put(key.toLowerCase(), loc);
		}
	}
	
	public String colorize(String message){
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
