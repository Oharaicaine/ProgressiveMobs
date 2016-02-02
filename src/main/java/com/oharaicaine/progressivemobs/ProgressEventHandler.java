package com.oharaicaine.progressivemobs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AchievementEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ProgressEventHandler {
	
	private List<EntityPlayerMP> players = new ArrayList<EntityPlayerMP>();
	
	@SubscribeEvent
	public void progessiveSpawnHandler(EntityJoinWorldEvent event){
		if(!event.world.isRemote){
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

				if(!players.isEmpty()){
					float[] tier = new float[players.size()];
					for(int i = 0; i < players.size();i++){
						EntityPlayerMP player = players.get(i);
						tier[i] = player.getEntityData().getFloat("AchievTier");
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
	
	private void progressiveSpawnHandler(EntityMob entity, float tier){
		if(!entity.getEntityData().getBoolean("boosted")){ //return if already boosted
			System.out.println("entity tier "+tier);
			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("health", (tier*0.2), 2));
			entity.setHealth(entity.getMaxHealth());
			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage).applyModifier(new AttributeModifier("damage", (tier*0.1), 2));
			entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed).applyModifier(new AttributeModifier("speed", (tier*0.05), 2));
			entity.getEntityData().setBoolean("boosted", true);
		}
		//entity.getEntityData().setFloat("tier", tier);
	}
	
	@SubscribeEvent
	public void achievmentTierHandler(AchievementEvent event){
		
		EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;
		
		if(!player.getStatFile().hasAchievementUnlocked(event.achievement)){
		
			float playerTier = player.getEntityData().getFloat("AchievTier");
			Achievement ach = event.achievement;
			
			if(ach.equals(AchievementList.mineWood)){
				player.getEntityData().setFloat("AchievTier", playerTier += 1.0f);
			}
			if(ach.equals(AchievementList.openInventory)){
				player.getEntityData().setFloat("AchievTier", playerTier += 1.0f);
			}
			if(ach.equals(AchievementList.killCow)){
				player.getEntityData().setFloat("AchievTier", playerTier += 1.0f);
			}
			if(ach.equals(AchievementList.blazeRod)){
				player.getEntityData().setFloat("AchievTier", playerTier += 1.0f);
			}
			System.out.println("player Tier "+player.getEntityData().getFloat("AchievTier"));
			player.addChatComponentMessage(new ChatComponentText("�4The enemies around you have become more powerfull!"));
		}
	}
}