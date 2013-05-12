package com.au_craft.GGDamageChanger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class GGDamageChanger extends JavaPlugin implements Listener {
  public String[] jarVersion = {"0.2"};
	public int contactDamage,  drowningDamage, fallDamage, fireDamage, fire_tickDamage, lavaDamage, lightningDamage, poisonDamage, starvationDamage, suffocationDamage, voidDamage;
	public boolean contactEnabled, drowningEnabled, fallEnabled, fireEnabled, fire_tickEnabled, lavaEnabled, lightningEnabled, poisonEnabled, starvationEnabled, suffocationEnabled, voidEnabled;
	protected UpdateChecker updateChecker;
	
	@Override
	public void onDisable(){
	}

	@Override
	public void onEnable(){
		this.updateChecker = new UpdateChecker(this, "http://dev.bukkit.org/server-mods/ggdamagechanger/files.rss");
		if (this.updateChecker.updateNeeded() && getConfig().getBoolean("checkForUpdates")){
			getLogger().info("A new version is available: "+this.updateChecker.getVersion());
			getLogger().info("Get it from: " + this.updateChecker.getLink());
		}
		if (!getConfig().getBoolean("checkForUpdates")){
			getLogger().warning("Update Checking is Disabled. It is advised to be enabled. Change settings in config.yml");
		}
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
		contactEnabled = getConfig().getBoolean("contactEnabled");
		contactDamage = getConfig().getInt("contactDamage");
		drowningEnabled = getConfig().getBoolean("drowningEnabled");
		drowningDamage = getConfig().getInt("drowningDamage");
		fallEnabled = getConfig().getBoolean("fallEnabled");
		fallDamage = getConfig().getInt("fallDamage");
		fireEnabled = getConfig().getBoolean("fireEnabled");
		fireDamage = getConfig().getInt("fireDamage");
		fire_tickEnabled = getConfig().getBoolean("fire_tickEnabled");
		fire_tickDamage = getConfig().getInt("fire_tickDamage");
		lavaEnabled = getConfig().getBoolean("lavaEnabled");
		lavaDamage = getConfig().getInt("lavaDamage");
		lightningEnabled = getConfig().getBoolean("lightningEnabled");
		lightningDamage = getConfig().getInt("lightningDamage");
		poisonEnabled = getConfig().getBoolean("poisonEnabled");
		poisonDamage = getConfig().getInt("poisonDamage");
		starvationEnabled = getConfig().getBoolean("starvationEnabled");
		starvationDamage = getConfig().getInt("starvationDamage");
		suffocationEnabled = getConfig().getBoolean("suffocationEnabled");
		suffocationDamage = getConfig().getInt("suffocationDamage");
		voidEnabled = getConfig().getBoolean("voidEnabled");
		voidDamage = getConfig().getInt("voidDamage");
		
	}
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event){
		switch (event.getCause()){
			case CONTACT:
				if (contactEnabled){
					if (contactDamage == 0){
						event.setCancelled(true);						
					} else {
						event.setDamage(contactDamage);
					}
				}
				break;
			case DROWNING:
				if (drowningEnabled){
					if (drowningDamage == 0){
						event.setCancelled(true);						
					} else {
						event.setDamage(drowningDamage);
					}
				}
				break;
			case FALL:
				if (fallEnabled){
					if (fallDamage == 0){
						event.setCancelled(true);						
					} else {
						event.setDamage(fallDamage);
					}
				}
				break;
			case FIRE:
				if (fireEnabled){
					if (fireDamage == 0){
						event.setCancelled(true);						
					} else {
						event.setDamage(fireDamage);
					}
				}
				break;
			case FIRE_TICK:
				if (fire_tickEnabled){
					if (fire_tickDamage == 0){
						event.setCancelled(true);						
					} else {
						event.setDamage(fire_tickDamage);
					}
				}
				break;
			case LAVA:
				if (lavaEnabled){
					if (lavaDamage == 0){
						event.setCancelled(true);						
					} else {
						event.setDamage(lavaDamage);
					}
				}
				break;
			case LIGHTNING:
				if (lightningEnabled){
					if (lightningDamage == 0){
						event.setCancelled(true);						
					} else {
						event.setDamage(lightningDamage);
					}
				}
				break;
			case POISON:
				if (poisonEnabled){
					if (poisonDamage == 0){
						event.setCancelled(true);						
					} else {
						event.setDamage(poisonDamage);
					}
				}
				break;
			case STARVATION:
				if (starvationEnabled){
					if (starvationDamage == 0){
						event.setCancelled(true);						
					} else {
						event.setDamage(starvationDamage);
					}
				}
				break;
			case SUFFOCATION:
				if (suffocationEnabled){
					if (suffocationDamage == 0){
						event.setCancelled(true);						
					} else {
						event.setDamage(suffocationDamage);
					}
				}
				break;
			case VOID:
				if (voidEnabled){
					if (voidDamage == 0){
						event.setCancelled(true);						
					} else {
						event.setDamage(voidDamage);
					}
				}
				break;
			default:{}
		}
	}
}
