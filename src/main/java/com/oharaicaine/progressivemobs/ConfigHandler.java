package com.oharaicaine.progressivemobs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sun.javafx.runtime.async.AsyncOperationListener;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import scala.actors.threadpool.Arrays;

public class ConfigHandler {

	public static boolean forceAchievements;
	public static double checkRange;
	
	public static boolean acquireIronAchieve;
	public static double acquireIronScale;
	
	public static boolean diamondsAchieve;
	public static double diamondsScale;
	
	public static boolean enchantmentsAchieve;
	public static double enchantmentsScale;

	public static boolean portalAchieve;
	public static double portalScale;
	
	public static boolean theEnd2Achieve;
	public static double theEnd2Scale;
	
	public static boolean killWitherAchieve;
	public static double killWitherScale;
	//Extras
	public static boolean overKillAchieve;
	public static double overKillScale;
	
	public static boolean overPoweredAchieve;
	public static double overPoweredScale;
	
	public static boolean potionAchieve;
	public static double potionScale;
	
	public static boolean beaconAchieve;
	public static double beaconScale;
	
	public static List<String> order = new ArrayList<String>();
	
	
	public static void init(Configuration config){
		config.load();

		Collections.addAll(order, "AcquireIron", "AcquireIronScale", "Diamonds","DiamondsScale", "Enchantments", "EnchantmentsScale",
				"Portal", "PortalScale", "TheEnd2", "TheEnd2Scale", "KillWither", "KillWitherScale", "Beacon", "BeaconScale", "Potion", "PotionScale" ,"OverKill", "OverKillScale","OverPowered", "OverPoweredScale");
		
		Property forceAchievementsProperty = config.get("Progressive Mobs", "Force Achievement unlock", true);
		forceAchievementsProperty.comment = "Unlocking Achievements even when you don't have the required sub-achievements";
		forceAchievements = forceAchievementsProperty.getBoolean(true);
		
		Property checkRangeProperty = config.get("Progressive Mobs", "Check Range", 128.0);
		checkRangeProperty.comment = "The range mobs will check for players to determine their scaling";
		checkRange = forceAchievementsProperty.getDouble(128.0);
		
	//	config.setCategoryComment("Achievements", "this is a comment");
		config.setCategoryPropertyOrder("Achievements", order);
		acquireIronAchieve = config.get("Achievements", "AcquireIron", true).getBoolean();
		acquireIronScale = config.get("Achievements", "AcquireIronScale", 1.0).getDouble();
		diamondsAchieve = config.get("Achievements", "Diamonds", true).getBoolean();
		diamondsScale = config.get("Achievements", "DiamondsScale", 0.5).getDouble();
		enchantmentsAchieve = config.get("Achievements", "Enchantments", true).getBoolean();
		enchantmentsScale = config.get("Achievements", "EnchantmentsScale", 0.5).getDouble();
		portalAchieve = config.get("Achievements", "Portal", true).getBoolean();
		portalScale = config.get("Achievements", "PortalScale", 1.0).getDouble();
		theEnd2Achieve = config.get("Achievements", "TheEnd2", true).getBoolean();
		theEnd2Scale = config.get("Achievements", "TheEnd2Scale", 2.0).getDouble();
		killWitherAchieve = config.get("Achievements", "KillWither", true).getBoolean();
		killWitherScale = config.get("Achievements", "KillWitherScale", 1.0).getDouble();
		//Extras
		beaconAchieve = config.get("Achievements","Beacon", false,"Extras").getBoolean();
		beaconScale = config.get("Achievements", "BeaconScale", 0.5).getDouble();
		potionAchieve = config.get("Achievements","Potion", false).getBoolean();
		potionScale = config.get("Achievements", "PotionScale", 0.5).getDouble();
		overKillAchieve = config.get("Achievements","OverKill", false).getBoolean();
		overKillScale = config.get("Achievements", "OverKillScale", 0.5).getDouble();
		overPoweredAchieve = config.get("Achievements","OverPowered", false).getBoolean();
		overPoweredScale = config.get("Achievements", "OverPoweredScale", 0.5).getDouble();
		config.save();
	}
}
