package com.oharaicaine.progressivemobs;



import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MobarmouryHandler {

	@SubscribeEvent
	public void spawnHandler(EntityJoinWorldEvent event){
		
		if(event.entity instanceof EntityLivingBase && !(event.entity instanceof EntityPlayer)){
			EntityLivingBase ent = (EntityLivingBase) event.entity;
			if(!ent.getEntityData().getBoolean("modhealth")){
				if(ent instanceof EntityZombie){
					ent.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
				}
				if(ent instanceof EntitySkeleton){
					ItemStack bow = new ItemStack(Items.bow);
					bow.addEnchantment(Enchantment.flame, 1);
					ent.setCurrentItemOrArmor(0, bow);
				}
				ent.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("health", 0.3, 2));
				ent.setHealth(ent.getMaxHealth());
				ent.getEntityData().setBoolean("modhealth", true);
			}
		}
		
	}
}
