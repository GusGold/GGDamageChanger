package com.au_craft.GGDamageChanger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.java.JavaPlugin;

public final class GGDamageChanger extends JavaPlugin implements Listener {
  public String[] jarVersion = {"0.1"};
	public int cactusDamage;
	
	@Override
	public void onDisable(){
	}
	
	@Override
	public void onEnable(){
		loadConfiguration();
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("Enabled");
	}
	
	public void loadConfiguration(){
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		checkConfigCompatibility();
		getDamageSettings();
	}
	public void reloadConfiguration(){
		this.reloadConfig();
		checkConfigCompatibility();
		getDamageSettings();
	}
	public void checkConfigCompatibility(){
		String configVersion = getConfig().getString("Version");
		boolean configIsCompatible = false;
		for (String compatibleVersion: jarVersion){
			if (configVersion.equals(compatibleVersion)){
				configIsCompatible = true;
			}
		}
		if (!configIsCompatible){
			getLogger().warning("Config is incompatible. Please delete config and Reload!");
			getLogger().warning("Using Default values!");
		}	
	}
	public void getDamageSettings(){
		cactusDamage = getConfig().getInt("cactusDamage");
	}
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event){
		if (event.getCause() == DamageCause.CONTACT && getConfig().getBoolean("enableCactus")){
			if (cactusDamage == 0){
				event.setCancelled(true);
			} else {
				event.setDamage(cactusDamage);
			}
		}
	}
}
