package com.oharaicaine.progressivemobs;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatisticsManager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ProgressEventHandler {
	
	private List<EntityPlayerMP> players = new ArrayList<EntityPlayerMP>();
	
	@SubscribeEvent(priority=EventPriority.LOWEST)

	public void progessiveSpawnHandler(EntityJoinWorldEvent event){

		if(!event.getWorld().isRemote){

			// Searches for nearby players to mob within a config specified range.

			if(event.getEntity() instanceof EntityMob){

				double range = ConfigLoader.checkRange;

				List list = event.getWorld().getEntitiesInAABBexcluding(event.getEntity(), event.getEntity().getEntityBoundingBox().expand(range, (range*0.5), range), null);

				if(!list.isEmpty()){

					for(int i=0; i < list.size(); i++){

						if(list.get(i) instanceof EntityPlayer){

							EntityPlayerMP player = (EntityPlayerMP)list.get(i);
							players.add(player);

						}

					}

				}

				// Calculates the difficulty tier based on all players near the spawning mob.

				if(!players.isEmpty()){

					float[] tier = new float[players.size()];

					for(int i = 0; i < players.size();i++){

						EntityPlayerMP player = players.get(i);
						tier[i] = obtainPlayerTier(player);

					}

					players.clear();
					
					float result = 0;

					for(int i = 0; i < tier.length; i++){

						result += tier[i];

					}

					result = result/tier.length;

					progressiveSpawnHandler((EntityMob)event.getEntity(), result);

				}

			}

		}

	}
	
	private float obtainPlayerTier(EntityPlayerMP player) {

		float result = 0;

		StatisticsManager file = player.getStatFile();
		
		for(Object[] obj : ConfigLoader.Achievements.values()){

			if(player.hasAchievement((Achievement) obj[0]))result += (Double) obj[2];

		}
		
		return result;

	}

	private void progressiveSpawnHandler(EntityMob entity, float scale){

		// 0 means attributes have not been applied.

		if(entity.getEntityData().getFloat("scale") == 0){

			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(new AttributeModifier("pmdamage", (scale*0.2), 1));

			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(new AttributeModifier("pmspeed", (scale*0.03), 1));

			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH).applyModifier(new AttributeModifier("pmhealth", (scale*0.2), 2));

			entity.setHealth(entity.getMaxHealth());

			double range = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.FOLLOW_RANGE).getBaseValue();

			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(range+(scale*3));

			if(entity instanceof EntityZombie){

				entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(scale*0.1);
				MobArmouryHandler.ZombieArmoury((EntityZombie)entity, scale);

			}

			if(entity instanceof EntitySkeleton){

				MobArmouryHandler.SkeletonArmoury((EntitySkeleton)entity, scale);

			}

			entity.getEntityData().setFloat("scale", scale);
		}

	}

}
