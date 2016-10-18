package com.oharaicaine.progressivemobs;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExpDropHandler {

	@SubscribeEvent
	public void ExpHandler(LivingExperienceDropEvent event){

		if(event.getEntityLiving() instanceof EntityMob){

			EntityPlayerMP player = (EntityPlayerMP)event.getAttackingPlayer();
			float scale = event.getEntityLiving().getEntityData().getFloat("scale");
			if(scale > 0)event.setDroppedExperience((int) (event.getOriginalExperience()+Math.round(scale)));

        }

	}

}
