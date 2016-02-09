package com.oharaicaine.progressivemobs;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExpDropHandler {

	@SubscribeEvent
	public void ExpHandler(LivingExperienceDropEvent event){
		if(event.entityLiving instanceof EntityMob){
			EntityPlayerMP player = (EntityPlayerMP)event.getAttackingPlayer();
			float scale = event.entityLiving.getEntityData().getFloat("scale");
			if(scale > 0)event.setDroppedExperience((int) (event.getOriginalExperience()+Math.round(scale)));
			
		}
	}
}
