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
		//System.out.println(event.achievement.toString());		
		
		//AchievementList.achievementList.get(1).toString()
		/*if(event.achievement.equals(AchievementList.overkill) && player.getStatFile().hasAchievementUnlocked(AchievementList.overkill.parentAchievement)){
			ProgressiveMobs.overOverKill.registerStat();
		}*/
		
		if(!player.getStatFile().hasAchievementUnlocked(event.achievement)){
			
			if(event.achievement.equals(AchievementList.acquireIron) && player.getStatFile().canUnlockAchievement(AchievementList.acquireIron) && ConfigHandler.acquireIronAchieve){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.diamonds) && player.getStatFile().canUnlockAchievement(AchievementList.diamonds) && ConfigHandler.diamondsAchieve){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.portal) && player.getStatFile().canUnlockAchievement(AchievementList.portal) && ConfigHandler.portalAchieve){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.enchantments) && player.getStatFile().canUnlockAchievement(AchievementList.enchantments) && ConfigHandler.enchantmentsAchieve){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.theEnd2) && player.getStatFile().canUnlockAchievement(AchievementList.theEnd2) && ConfigHandler.theEnd2Achieve){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.killWither) && player.getStatFile().canUnlockAchievement(AchievementList.killWither) && ConfigHandler.killWitherAchieve){
				player.addChatComponentMessage(chatText);
			}
			//Extras
			else if(event.achievement.equals(AchievementList.fullBeacon) && player.getStatFile().canUnlockAchievement(AchievementList.fullBeacon) && ConfigHandler.beaconAchieve){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.potion) && player.getStatFile().canUnlockAchievement(AchievementList.potion) && ConfigHandler.potionAchieve){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.overkill) && player.getStatFile().canUnlockAchievement(AchievementList.overkill) && ConfigHandler.overKillAchieve){
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.overpowered) && player.getStatFile().canUnlockAchievement(AchievementList.overpowered) && ConfigHandler.overPoweredAchieve){
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
