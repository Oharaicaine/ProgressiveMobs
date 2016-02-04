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
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = "0.1")
public class ProgressiveMobs {

	public static Achievement overOverKill;
	public static boolean forceAchievements;
	public static double checkRange;
	public static double scale;
	
	
	@Instance(Reference.MOD_ID)
	public static ProgressiveMobs instance;
	
	@EventHandler
	public void perInit(FMLPreInitializationEvent event){
		overOverKill = new Achievement("achievement.overOverKill","overOverKill", -4, -2, Items.golden_sword, AchievementList.overkill).setSpecial();
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		Property forceAchievementsProperty = config.get("Progressive Mobs", "Force Achievement unlock", true);
		forceAchievementsProperty.comment = "Unlocking Achievements even when you don't have the required sub-achievements";
		forceAchievements = forceAchievementsProperty.getBoolean(true);
		
		Property checkRangeProperty = config.get("Progressive Mobs", "Check Range", 128.0);
		checkRangeProperty.comment = "The range mobs will check for players to determine their scaling";
		checkRange = forceAchievementsProperty.getDouble(128.0);
		
		Property scaleProperty = config.get("Progressive Mobs", "Scaling", 1.0);
		scaleProperty.comment = "scaling per achievement unlocked";
		scale = scaleProperty.getDouble(1.0);
		
		
		
		config.save();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		
		MinecraftForge.EVENT_BUS.register(new ProgressEventHandler());
		MinecraftForge.EVENT_BUS.register(new LivingHurtHandler());
		MinecraftForge.EVENT_BUS.register(new ExpDropHandler());
		
		
		if(forceAchievements){
			MinecraftForge.EVENT_BUS.register(new AchievementForceHandler());
		}else{
			MinecraftForge.EVENT_BUS.register(new AchievementHandler());
		}
		
		
	
	}
	
	
	
}
