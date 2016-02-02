package com.oharaicaine.progressivemobs;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.scene.traversal.Hueristic2D;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatisticsFile;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AchievementEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ProgressEventHandler {
	
	private List<EntityPlayerMP> players = new ArrayList<EntityPlayerMP>();
	
	@SubscribeEvent
	public void progessiveSpawnHandler(EntityJoinWorldEvent event){
		if(!event.world.isRemote){
			/*
			 * Checks for players around the mobs
			 */
			if(event.entity instanceof EntityMob){
				List list = event.world.getEntitiesInAABBexcluding(event.entity, event.entity.getEntityBoundingBox().expand(128.0, 32.0, 128.0), null);
				if(!list.isEmpty()){
					for(int i=0; i < list.size(); i++){
						if(list.get(i) instanceof EntityPlayer){
							EntityPlayerMP player = (EntityPlayerMP)list.get(i);
							players.add(player);
						}
					}	
				}
				/*
				 * Calculates the difficulty tier
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
					progressiveSpawnHandler((EntityMob) event.entity, result);
				}
			}
		}
	}
	
	private float obtainPlayerTier(EntityPlayerMP player) {
		float result = 0;
		StatisticsFile file = player.getStatFile(); 
		if(file.hasAchievementUnlocked(AchievementList.acquireIron))result += 1.0f;
		if(file.hasAchievementUnlocked(AchievementList.diamonds))result += 1.0f;
		if(file.hasAchievementUnlocked(AchievementList.enchantments))result += 1.0f;
		if(file.hasAchievementUnlocked(AchievementList.portal))result += 1.0f;
		if(file.hasAchievementUnlocked(AchievementList.theEnd2))result += 1.0f;
		if(file.hasAchievementUnlocked(AchievementList.killWither))result += 1.0f;
		
		return result;
	}

	private void progressiveSpawnHandler(EntityMob entity, float tier){
		if(!entity.getEntityData().getBoolean("boosted")){ 
			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("pmhealth", (tier*0.3), 2));
			entity.setHealth(entity.getMaxHealth());
			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage).applyModifier(new AttributeModifier("pmdamage", (tier*0.2), 2));
			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed).applyModifier(new AttributeModifier("pmspeed", (tier*0.03), 2));
			
			double range = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.followRange).getBaseValue();
			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.followRange).setBaseValue(range+(tier*5));
			
			if(entity instanceof EntityZombie)
				entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance).setBaseValue(tier*0.1);
		
			entity.getEntityData().setBoolean("boosted", true);
		}
	}
	
	@SubscribeEvent
	public void hitTest(LivingHurtEvent event){
		if(event.entityLiving instanceof EntityPlayer){	
			System.out.println("damaged "+event.ammount);
		}
	}	
}
