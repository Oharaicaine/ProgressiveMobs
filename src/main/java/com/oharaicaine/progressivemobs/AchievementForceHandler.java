package com.oharaicaine.progressivemobs;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.AchievementEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AchievementForceHandler {
	
	private static ChatComponentText chatText = new ChatComponentText(EnumChatFormatting.RED + "The enemies around you become more powerfull!");
	
	@SubscribeEvent
	public void achievmentHandler(AchievementEvent event){
		
		EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;
		//System.out.println(event.achievement.toString());	
		
		if(!player.getStatFile().hasAchievementUnlocked(event.achievement)){
						
			if(event.achievement.equals(AchievementList.acquireIron) && ConfigHandler.acquireIronAchieve){
				player.getStatFile().unlockAchievement(player, AchievementList.acquireIron, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.diamonds) && ConfigHandler.diamondsAchieve){
				player.getStatFile().unlockAchievement(player, AchievementList.diamonds, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.portal) && ConfigHandler.portalAchieve){
				player.getStatFile().unlockAchievement(player, AchievementList.portal, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.enchantments) && ConfigHandler.enchantmentsAchieve){
				player.getStatFile().unlockAchievement(player, AchievementList.enchantments, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.theEnd2) && ConfigHandler.theEnd2Achieve){
				player.getStatFile().unlockAchievement(player, AchievementList.theEnd2, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.killWither) && ConfigHandler.killWitherAchieve){
				player.getStatFile().unlockAchievement(player, AchievementList.killWither, 1);
				player.addChatComponentMessage(chatText);
			}
			//Extras
			else if(event.achievement.equals(AchievementList.fullBeacon) && ConfigHandler.beaconAchieve){
				player.getStatFile().unlockAchievement(player, AchievementList.fullBeacon, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.potion) && ConfigHandler.potionAchieve){
				player.getStatFile().unlockAchievement(player, AchievementList.potion, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.overkill) && ConfigHandler.overKillAchieve){
				player.getStatFile().unlockAchievement(player, AchievementList.overkill, 1);
				player.addChatComponentMessage(chatText);
			}
			else if(event.achievement.equals(AchievementList.overpowered) && ConfigHandler.overPoweredAchieve){
				player.getStatFile().unlockAchievement(player, AchievementList.overpowered, 1);
				player.addChatComponentMessage(chatText);
			}
			
			event.isCanceled();
		}
	}
}
