package com.oharaicaine.progressivemobs;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.player.AchievementEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AchievementHandler {

private static ChatComponentText chatText = new ChatComponentText("§4The enemies around you become more powerfull!");
	
	@SubscribeEvent
	public void achievmentHandler(AchievementEvent event){
		
		EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;
				
		if(!player.getStatFile().hasAchievementUnlocked(event.achievement)){
			
			if(event.achievement.equals(AchievementList.acquireIron) && player.getStatFile().canUnlockAchievement(AchievementList.acquireIron)){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.diamonds) && player.getStatFile().canUnlockAchievement(AchievementList.diamonds)){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.portal) && player.getStatFile().canUnlockAchievement(AchievementList.portal)){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.enchantments) && player.getStatFile().canUnlockAchievement(AchievementList.enchantments)){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.theEnd2) && player.getStatFile().canUnlockAchievement(AchievementList.theEnd2)){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.killWither) && player.getStatFile().canUnlockAchievement(AchievementList.killWither)){
				player.addChatComponentMessage(chatText);
			}
		}
	}
}
