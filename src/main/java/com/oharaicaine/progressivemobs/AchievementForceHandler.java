package com.oharaicaine.progressivemobs;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.player.AchievementEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AchievementForceHandler {
	
	private static ChatComponentText chatText = new ChatComponentText("§4The enemies around you become more powerfull!");
	
	@SubscribeEvent
	public void achievmentHandler(AchievementEvent event){
		
		EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;
				
		if(!player.getStatFile().hasAchievementUnlocked(event.achievement)){
			
			if(event.achievement.equals(AchievementList.acquireIron)){
				player.getStatFile().unlockAchievement(player, AchievementList.acquireIron, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.diamonds)){
				player.getStatFile().unlockAchievement(player, AchievementList.diamonds, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.portal)){
				player.getStatFile().unlockAchievement(player, AchievementList.portal, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.enchantments)){
				player.getStatFile().unlockAchievement(player, AchievementList.enchantments, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.theEnd2)){
				player.getStatFile().unlockAchievement(player, AchievementList.theEnd2, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.killWither)){
				player.getStatFile().unlockAchievement(player, AchievementList.killWither, 1);
				player.addChatComponentMessage(chatText);
			}
			event.isCanceled();
		}
	}
}
