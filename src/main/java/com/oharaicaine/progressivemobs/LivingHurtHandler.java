package com.oharaicaine.progressivemobs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LivingHurtHandler {

	@SubscribeEvent
	public void HurtHandler(LivingHurtEvent event){
		if(event.entityLiving instanceof EntityPlayer && event.source.isProjectile()){
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			float scale = event.source.getEntity().getEntityData().getFloat("scale");
			if(scale > 0){
				player.attackEntityFrom(DamageSource.generic, (float)(scale*0.5));
			}
		}
	}
}
