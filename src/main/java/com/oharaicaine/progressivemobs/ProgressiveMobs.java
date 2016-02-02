package com.oharaicaine.progressivemobs;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = "0.1")
public class ProgressiveMobs {

	//public static Achievement progressStoneSword;
	public static boolean forceAchievements;
	
	
	@Instance(Reference.MOD_ID)
	public static ProgressiveMobs instance;
	
	@EventHandler
	public void perInit(FMLPreInitializationEvent event){
		//progressStoneSword = new Achievement("achievement.progressStoneSword","progressStoneSword", -1, -1, Items.stone_sword, (Achievement)null).registerStat();
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		Property forceAchievementsProperty = config.get("Progressive Mobs", "force Achievement unlock", true);
		forceAchievementsProperty.comment = "Unlocking Achievements even when you don't have the required sub-achievements";
		forceAchievements = forceAchievementsProperty.getBoolean(true);
		
		config.save();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		
		MinecraftForge.EVENT_BUS.register(new ProgressEventHandler());
		if(forceAchievements){
			MinecraftForge.EVENT_BUS.register(new AchievementForceHandler());
		}else{
			MinecraftForge.EVENT_BUS.register(new AchievementHandler());
		}
		
		
	
	}
	
	
	
}
