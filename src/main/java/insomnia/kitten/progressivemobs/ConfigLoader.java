package insomnia.kitten.progressivemobs;

import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.util.*;

public class ConfigLoader {

	public static String[] vanillaAchieves = new String[28];
	public static String[] vanillaDefault = new String[7];
	public static String[] moddedAchieves = new String[AchievementList.ACHIEVEMENTS.size()];
	
	public static double checkRange = 128.0;
	public static boolean forceAchievements = true;
	
	public static Map<String,Object[]> Achievements = new HashMap<String,Object[]>();
	
	public static void load(Configuration config){
		
		List<String> vanillaDefaultList = new ArrayList<String>();
		List<String> vanillaList = new ArrayList<String>();
		List<String> moddedList = new ArrayList<String>();

		for(int i = 0; i < 34; i++){

			Achievement ach = AchievementList.ACHIEVEMENTS.get(i);

			if(ach.equals(AchievementList.ACQUIRE_IRON)) {vanillaDefaultList.add(ach.statId+"= true, 1.0");}

			else if(ach.equals(AchievementList.DIAMONDS)) {vanillaDefaultList.add(ach.statId+"= true, 1.0");}

			else if(ach.equals(AchievementList.ENCHANTMENTS)) {vanillaDefaultList.add(ach.statId+"= true, 0.5");}

			else if(ach.equals(AchievementList.PORTAL)) {vanillaDefaultList.add(ach.statId+"= true, 1.0");}

			else if(ach.equals(AchievementList.THE_END2)) {vanillaDefaultList.add(ach.statId+"= true, 1.0");}

			else if(ach.equals(AchievementList.KILL_WITHER)) {vanillaDefaultList.add(ach.statId+"= true, 1.5");}

			else if(ach.equals(AchievementList.OVERKILL)) {vanillaDefaultList.add(ach.statId+"= true, 2.0");}

			else{vanillaList.add(ach.statId+"= false, 1.0");}

		}

		for(int i = 34; i < AchievementList.ACHIEVEMENTS.size(); i++){

			Achievement ach = AchievementList.ACHIEVEMENTS.get(i);
			moddedList.add(ach.statId+"= false, 1.0");

		}

		config.load();

		List<String> order = new ArrayList<String>();
		Collections.addAll(order, "Progressive Mobs", "VanillaDefault", "Vanilla", "Modded");
		
		Property forceAchievementsProperty = config.get("Progressive Mobs", "Force Achievement unlock", true);
		forceAchievementsProperty.setComment("Unlocking Achievements even when you don't have the required sub-achievements");
		forceAchievements = forceAchievementsProperty.getBoolean(true);
		
		Property checkRangeProperty = config.get("Progressive Mobs", "Check Range", 128.0);
		checkRangeProperty.setComment("The range mobs will check for players to determine their scaling");
		checkRange = forceAchievementsProperty.getDouble(128.0);
		
		config.setCategoryPropertyOrder("Achievement", order);
		config.addCustomCategoryComment("Achievement", "Enable/Disable achievements, Double(eg 1.0) is the scaling for each achievement. Range 0.1-10.0");
		Property defaultLoad = config.get("Achievement", "VanillaDefault", convertListToArray(vanillaDefaultList));
		Property vanillaLoad = config.get("Achievement", "Vanilla", convertListToArray(vanillaList));
		Property moddedLoad = config.get("Achievement", "Modded", convertListToArray(moddedList));

		populateMap(defaultLoad.getStringList());
		populateMap(vanillaLoad.getStringList());
		populateMap(moddedLoad.getStringList());

		config.save();
	}
	
	private static void populateMap(String[] stringList) {

		if(stringList.length < 1)return;

		ArrayList<String[]> result = new ArrayList<String[]>();

		for(int i = 0; i < stringList.length; i++) {result.add(stringList[i].split("=|,"));}

		for(String[] str: result){

			str[0] = str[0].replaceAll("\\s+","");
			str[1] = str[1].replaceAll("\\s+","");
			str[2] = str[2].replaceAll("\\s+","");

			if(Boolean.parseBoolean(str[1])){

				Achievements.put(str[0], createArray(str[0], tryParseBoolean(str[1]), tryParseDouble(str[2])));

			}

		}

	}

	private static String[] convertListToArray(List<String> array) {

		String[] result = new String[array.size()];

		for(int i = 0; i < array.size(); i++) {result[i] = array.get(i);}

		return result;
	}

	private static String[] removeEmptyValues(String[] vanillaAchieves2) {

		List<String> temp = new ArrayList<String>();

		for(String str: vanillaAchieves2) {if(str != null) {temp.add(str);}}

		String[] result = new String[temp.size()];

		for(int i = 0; i < temp.size(); i++) {result[i] = temp.get(i);}

		return result;
	}

	private static Object[] createArray(String achId, boolean bool, double scale){

		Object[] input = new Object[3];

		for(Achievement ach: AchievementList.ACHIEVEMENTS){

			if(ach.statId.equals(achId)){

				input[0] = ach;
				input[1] = bool;
				input[2] = scale;

			}

		}

		return input;

	}
	
	private static double tryParseDouble(String text) {

		try {return Double.parseDouble(text);}

		catch (NumberFormatException e) {return 1.0;}

	}
	private static boolean tryParseBoolean(String text) {

		try {return Boolean.parseBoolean(text);}
		catch (NumberFormatException e) {return false;}

	}
	
/*	private static Object[] organizeArray(Object[] array, int index){
		Object[] result = new Object[array.length/index];
		for(int i = 0;i < array.length; i++){
			result[i] = array[(i*index)+index];
		}
		 return result;
	}*/
	
}
