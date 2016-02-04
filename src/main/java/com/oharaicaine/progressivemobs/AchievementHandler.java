package com.oharaicaine.progressivemobs;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
				
		/*if(event.achievement.equals(AchievementList.overkill) && player.getStatFile().hasAchievementUnlocked(AchievementList.overkill.parentAchievement)){
			ProgressiveMobs.overOverKill.registerStat();
		}*/
		
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
