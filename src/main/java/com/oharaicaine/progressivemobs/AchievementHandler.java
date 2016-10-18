package com.oharaicaine.progressivemobs;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.Achievement;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.AchievementEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AchievementHandler {

private static TextComponentString chatText = new TextComponentString(TextFormatting.RED + "The enemies around you grow stronger..");
	
	@SubscribeEvent
	public void AchievmentHandler(AchievementEvent event){

		EntityPlayerMP player = (EntityPlayerMP) event.getEntityPlayer();

		if(!player.getStatFile().hasAchievementUnlocked(event.getAchievement()) && ConfigLoader.forceAchievements){
			
			for(Object[] obj : ConfigLoader.Achievements.values()){

				if(event.getAchievement().equals(obj[0])){

					player.getStatFile().unlockAchievement(player, (Achievement)obj[0], 1);
					player.addChatComponentMessage(chatText);
					break;

				}

			}

			event.isCanceled();

		}

        else if(!player.getStatFile().hasAchievementUnlocked(event.getAchievement()) && !(ConfigLoader.forceAchievements)){
			
			for(Object[] obj : ConfigLoader.Achievements.values()){

				if(event.getAchievement().equals(obj[0]) && player.getStatFile().canUnlockAchievement((Achievement)obj[0])){

					player.addChatComponentMessage(chatText);
					break;

				}

			}

		}

	}

}
