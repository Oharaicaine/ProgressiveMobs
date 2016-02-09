package com.oharaicaine.progressivemobs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class AchievementLoader {

	public static String[] vanillaAchieves = new String[28];//27
	public static String[] vanillaDefault = new String[7];
	public static String[] moddedAchieves = new String[AchievementList.achievementList.size()];
	
	public static Map<String,Object[]> keys = new HashMap<String,Object[]>();
	
	public static void load(Configuration config){
		List<String> vanillaDefaultList = new ArrayList<String>();
		List<String> vanillaList = new ArrayList<String>();
		List<String> moddedList = new ArrayList<String>();

		for(int i = 0; i < 35; i++){//34
			Achievement ach = AchievementList.achievementList.get(i);
			if(ach.equals(AchievementList.acquireIron)){
				vanillaDefaultList.add(ach.statId+"= true, 1.0");
			}else if(ach.equals(AchievementList.diamonds)){
				vanillaDefaultList.add(ach.statId+"= true, 1.0");
			}else if(ach.equals(AchievementList.enchantments)){
				vanillaDefaultList.add(ach.statId+"= true, 1.0");
			}else if(ach.equals(AchievementList.portal)){
				vanillaDefaultList.add(ach.statId+"= true, 1.0");
			}else if(ach.equals(AchievementList.theEnd2)){
				vanillaDefaultList.add(ach.statId+"= true, 1.0");
			}else if(ach.equals(AchievementList.killWither)){
				vanillaDefaultList.add(ach.statId+"= true, 1.0");
			}else if(ach.equals(AchievementList.overkill)){
				vanillaDefaultList.add(ach.statId+"= true, 1.0");
			}else{
				vanillaList.add(ach.statId+"= false, 1.0");
			}
		}//34
		for(int i = 35; i < AchievementList.achievementList.size(); i++){
			Achievement ach = AchievementList.achievementList.get(i);
			moddedList.add(ach.statId+"= false, 1.0");
		}
		config.load();
		List<String> order = new ArrayList<String>();
		Collections.addAll(order, "VanillaDefault", "Vanilla", "Modded");
		config.setCategoryPropertyOrder("Achievement", order);
		Property defaultLoad = config.get("Achievement", "VanillaDefault", convertToStringArray(vanillaDefaultList));
		Property vanillaLoad = config.get("Achievement", "Vanilla", convertToStringArray(vanillaList));
		Property moddedLoad = config.get("Achievement", "Modded", convertToStringArray(moddedList));

		populateMap(defaultLoad.getStringList());
		populateMap(vanillaLoad.getStringList());
		populateMap(moddedLoad.getStringList());
		config.save();
		
		//0=id, 1=boolean, 2=double
		/*String[] temp = load[0].split("=|,");
		for(int k = 0; k < temp.length;k++){
			System.out.println(temp[k]);
		}*/
		//booleans = organizeArray(ArrayUtils.toObject(Boolean.parseBoolean(temp)), 1);
		/*String tp = temp[0];
		booleans[0] = Boolean.parseBoolean(temp[0]);
		organizeArray(ArrayUtils.toObject(scales), 1);
		scales[0] = tryParse(temp[1]);
		System.out.println(booleans[0]);j
		System.out.println(scales[0]);*/
		/*for(int j = 0;j< temp.length;j++){
			System.out.println(temp[j]);
		}*/
	}
	
	private static void populateMap(String[] stringList) {
		if(stringList.length < 1)return;
		ArrayList<String[]> result = new ArrayList<String[]>();
		for(int i = 0; i < stringList.length; i++){
			result.add(stringList[i].split("=|,"));
		}
		for(String[] str: result){
			str[0] = str[0].replaceAll("\\s+","");
			str[1] = str[1].replaceAll("\\s+","");
			str[2] = str[2].replaceAll("\\s+","");
			if(Boolean.parseBoolean(str[1])){
				keys.put(str[0], createArray(str[0], Boolean.parseBoolean(str[1]), Double.parseDouble(str[2])));
			}
		}
	}

	private static String[] convertToStringArray(List<String> array) {
		String[] result = new String[array.size()];
		for(int i = 0; i < array.size(); i++){
			result[i] = array.get(i);
		}
		return result;
	}

	private static String[] removeEmptyValues(String[] vanillaAchieves2) {
		List<String> temp = new ArrayList<String>();
		for(String str: vanillaAchieves2){
			if(str != null){
				temp.add(str);
			}
		}
		String[] result = new String[temp.size()];
		for(int i = 0; i < temp.size(); i++){
			result[i] = temp.get(i);
		}
		return result;
	}

	private static Object[] createArray(String achId, boolean bool, double scale){
		Object[] input = new Object[3];
		
		for(Achievement ach: AchievementList.achievementList){
			if(ach.statId.equals(achId)){
				input[0] = achId;
				input[1] = bool;
				input[2] = scale;
			}
		}
		return input;
	}
	
	private static double tryParseDouble(String text) {
		try {
			return Double.parseDouble(text);
		} catch (NumberFormatException e) {
		    return 1.0;
		}
	}
	
	private static Object[] organizeArray(Object[] array, int index){
		Object[] result = new Object[array.length/index];
		for(int i = 0;i < array.length; i++){
			result[i] = array[(i*index)+index];
		}
		 return result;
	}
	
}
