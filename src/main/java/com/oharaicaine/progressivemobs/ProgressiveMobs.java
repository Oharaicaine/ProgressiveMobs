package com.oharaicaine.progressivemobs;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = "0.4")
public class ProgressiveMobs {

	//public static Achievement overOverKill;
	private static Configuration config;
	
	@Instance(Reference.MOD_ID)
	public static ProgressiveMobs instance;
	
	@EventHandler
	public void perInit(FMLPreInitializationEvent event){
		//overOverKill = new Achievement("achievement.overOverKill","overOverKill", -4, -2, Items.golden_sword, AchievementList.overkill).setSpecial().registerStat();
		config = new Configuration(event.getSuggestedConfigurationFile(), "0.3");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		
		MinecraftForge.EVENT_BUS.register(new ProgressEventHandler());
		MinecraftForge.EVENT_BUS.register(new LivingHurtHandler());
		MinecraftForge.EVENT_BUS.register(new ExpDropHandler());
		
		MinecraftForge.EVENT_BUS.register(new AchievementHandler());
	
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		ConfigLoader.load(config);
	}
	
	
	
}
