package com.oharaicaine.progressivemobs;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AchievementEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AchievementHandler {

private static ChatComponentText chatText = new ChatComponentText(EnumChatFormatting.RED + "The enemies around you become more powerfull!");
	
	@SubscribeEvent
	public void achievmentHandler(AchievementEvent event){
		EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;

		if(!player.getStatFile().hasAchievementUnlocked(event.achievement) && ConfigLoader.forceAchievements){
			
			for(Object[] obj : ConfigLoader.achievements.values()){
				if(event.achievement.equals((Achievement)obj[0])){
					player.getStatFile().unlockAchievement(player, (Achievement)obj[0], 1);
					player.addChatComponentMessage(chatText);
					break;
				}
			}
			event.isCanceled();
		}else if(!player.getStatFile().hasAchievementUnlocked(event.achievement) && !(ConfigLoader.forceAchievements)){
			
			for(Object[] obj : ConfigLoader.achievements.values()){
				if(event.achievement.equals((Achievement)obj[0]) && player.getStatFile().canUnlockAchievement((Achievement)obj[0])){
					player.addChatComponentMessage(chatText);
					break;
				}
			}	
		}
		
		
	}
	
	/*@SubscribeEvent
	public void achievementChecker(LivingHurtEvent event){
		if(event.entityLiving instanceof EntityMob){
			if(event.source.getEntity() !=null){
				if(event.source.getEntity() instanceof EntityPlayer){
					if(event.ammount > 22.0f){
						EntityPlayer player = (EntityPlayer) event.source.getEntity();
						player.triggerAchievement(ProgressiveMobs.overOverKill);
					}
				}
			}
		}
	}*/
}
