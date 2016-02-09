package com.oharaicaine.progressivemobs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatisticsFile;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ProgressEventHandler {
	
	private List<EntityPlayerMP> players = new ArrayList<EntityPlayerMP>();
	
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void progessiveSpawnHandler(EntityJoinWorldEvent event){
		if(!event.world.isRemote){
			/*
			 * Checks for players around the mobs
			 */
			if(event.entity instanceof EntityMob){
				double range = ConfigHandler.checkRange;
				List list = event.world.getEntitiesInAABBexcluding(event.entity, event.entity.getEntityBoundingBox().expand(range, (range*0.5), range), null);
				if(!list.isEmpty()){
					for(int i=0; i < list.size(); i++){
						if(list.get(i) instanceof EntityPlayer){
							EntityPlayerMP player = (EntityPlayerMP)list.get(i);
							players.add(player);
						}
					}	
				}
				/*
				 * Calculates the difficulty tier based on all players around the spawning mob
				 */
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
					progressiveSpawnHandler((EntityMob)event.entity, result);
				}
			}
		}
	}
	
	private float obtainPlayerTier(EntityPlayerMP player) {
		float result = 0;
		StatisticsFile file = player.getStatFile(); 
		if(file.hasAchievementUnlocked(AchievementList.acquireIron) && ConfigHandler.acquireIronAchieve)result += ConfigHandler.acquireIronScale;
		if(file.hasAchievementUnlocked(AchievementList.diamonds) && ConfigHandler.diamondsAchieve)result += ConfigHandler.diamondsScale;
		if(file.hasAchievementUnlocked(AchievementList.enchantments) && ConfigHandler.enchantmentsAchieve)result += ConfigHandler.enchantmentsScale;
		if(file.hasAchievementUnlocked(AchievementList.portal) && ConfigHandler.portalAchieve)result += ConfigHandler.portalScale;
		if(file.hasAchievementUnlocked(AchievementList.theEnd2) && ConfigHandler.theEnd2Achieve)result += ConfigHandler.theEnd2Scale;
		if(file.hasAchievementUnlocked(AchievementList.killWither) && ConfigHandler.killWitherAchieve)result += ConfigHandler.killWitherScale;
		//Extras
		if(file.hasAchievementUnlocked(AchievementList.fullBeacon) && ConfigHandler.beaconAchieve)result += ConfigHandler.beaconScale;
		if(file.hasAchievementUnlocked(AchievementList.potion) && ConfigHandler.potionAchieve)result += ConfigHandler.potionScale;
		if(file.hasAchievementUnlocked(AchievementList.overkill) && ConfigHandler.overKillAchieve)result += ConfigHandler.overKillScale;
		if(file.hasAchievementUnlocked(AchievementList.overpowered) && ConfigHandler.overPoweredAchieve)result += ConfigHandler.overPoweredScale;
		
		return result;
	}

	private void progressiveSpawnHandler(EntityMob entity, float scale){
		// 0 means boosts have not been added
		if(entity.getEntityData().getFloat("scale") == 0){ 
			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage).applyModifier(new AttributeModifier("pmdamage", (scale*0.2), 1));
			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed).applyModifier(new AttributeModifier("pmspeed", (scale*0.03), 1));
			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("pmhealth", (scale*0.2), 2));
			entity.setHealth(entity.getMaxHealth());
			
			double range = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.followRange).getBaseValue();
			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.followRange).setBaseValue(range+(scale*3));
			
			if(entity instanceof EntityZombie){
				entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance).setBaseValue(scale*0.1);
				MobarmouryHandler.ZombieArmoury((EntityZombie)entity, scale);
			}
			if(entity instanceof EntitySkeleton){
				MobarmouryHandler.SkeletonArmoury((EntitySkeleton)entity, scale);
			}
			if(entity instanceof EntityCreeper){
			}
			if(entity instanceof EntitySpider){	
			}
			entity.getEntityData().setFloat("scale", scale);
		}
	}
	
	/*@SubscribeEvent
	public void hitTest(LivingHurtEvent event){
		if(event.entityLiving instanceof EntityPlayer){	
			System.out.println("damaged "+event.ammount);
		}
	}	*/
}
