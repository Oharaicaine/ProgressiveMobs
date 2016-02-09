package com.oharaicaine.progressivemobs;



import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class MobarmouryHandler {
	
	private static Random random = new Random();
	
	public static void ZombieArmoury(EntityZombie zombie, float scale){
		ArmourSet(zombie, scale);
		//WeaponSet(zombie, scale);
	}

	public static void SkeletonArmoury(EntitySkeleton skeleton, float scale){
		ArmourSet(skeleton, scale);
		if(skeleton.getSkeletonType() == 0)WeaponSet(skeleton, scale);
	}
	
	private static void WeaponSet(EntityMob mob, float scale) {
		if(1+(random.nextInt(30)+(scale*2)) >= 30 ){
			if(mob instanceof EntityZombie){
				int result = (1+random.nextInt(80))+Math.round(scale*5);
				if(result <= 40){//40
					ItemStack sword = new ItemStack(Items.wooden_sword);
					mob.setCurrentItemOrArmor(0, sword);
				}
				else if(result > 40 && result <= 70){//30
					ItemStack sword = new ItemStack(Items.wooden_sword);
					mob.setCurrentItemOrArmor(0, sword);
				}
				else if(result > 70 && result <= 90){//20
					ItemStack sword = new ItemStack(Items.wooden_sword);
					mob.setCurrentItemOrArmor(0, sword);
				}
				else if(result > 90){//10
					ItemStack sword = new ItemStack(Items.wooden_sword);
					mob.setCurrentItemOrArmor(0, sword);
				}
			}
			if(mob instanceof EntitySkeleton){
				int result = (1+random.nextInt(80))+Math.round(scale*5);
				ItemStack bow = new ItemStack(Items.bow);
				if(result <= 40){//40
					bow.addEnchantment(Enchantment.punch, 1);
				}
				else if(result > 40 && result <= 70){//30
					bow.addEnchantment(Enchantment.power, 1);
				}
				else if(result > 70 && result <= 90){//20
					bow.addEnchantment(Enchantment.flame, 1);
				}
				else if(result > 90){//10
					bow.addEnchantment(Enchantment.power, 2);
					bow.addEnchantment(Enchantment.punch, 1);
					bow.addEnchantment(Enchantment.flame, 1);
				}
				if(((EntitySkeleton)mob).getSkeletonType() == 0)mob.setCurrentItemOrArmor(0, bow);
			}
		}
		
	}

	private static void ArmourSet(EntityMob mob, float scale){
		if(1+(random.nextInt(30)+(scale*2)) >= 30 ){
			
			int result = (1+random.nextInt(80))+Math.round(scale*5);
			if(result <= 40){//40
				if(random.nextInt(2)== 0){
					ItemStack helm = new ItemStack(Items.leather_helmet);
					mob.setCurrentItemOrArmor(4, helm);
				}
				if(random.nextInt(2)== 0){
					ItemStack chest = new ItemStack(Items.leather_chestplate);
					mob.setCurrentItemOrArmor(3, chest);
				}
				if(random.nextInt(2)== 0){
					ItemStack legs = new ItemStack(Items.leather_leggings);
					mob.setCurrentItemOrArmor(2, legs);
				}
				if(random.nextInt(2)== 0){
					ItemStack boots = new ItemStack(Items.leather_boots);
					mob.setCurrentItemOrArmor(1, boots);
				}
			}
			else if(result > 40 && result <= 70){//30
				if(random.nextInt(2)== 0){
					ItemStack helm = new ItemStack(Items.chainmail_helmet);
					mob.setCurrentItemOrArmor(4, helm);
				}
				if(random.nextInt(2)== 0){
					ItemStack chest = new ItemStack(Items.chainmail_chestplate);
					mob.setCurrentItemOrArmor(3, chest);
				}
				if(random.nextInt(2)== 0){
					ItemStack legs = new ItemStack(Items.chainmail_leggings);
					mob.setCurrentItemOrArmor(2, legs);
				}
				if(random.nextInt(2)== 0){
					ItemStack boots = new ItemStack(Items.chainmail_boots);
					mob.setCurrentItemOrArmor(1, boots);
				}
			}
			else if(result > 70 && result <= 90){//20
				if(random.nextInt(2)== 0){
					ItemStack helm = new ItemStack(Items.iron_helmet);
					mob.setCurrentItemOrArmor(4, helm);
				}
				if(random.nextInt(2)== 0){
					ItemStack chest = new ItemStack(Items.iron_chestplate);
					mob.setCurrentItemOrArmor(3, chest);
				}
				if(random.nextInt(2)== 0){
					ItemStack legs = new ItemStack(Items.iron_leggings);
					mob.setCurrentItemOrArmor(2, legs);
				}
				if(random.nextInt(2)== 0){
					ItemStack boots = new ItemStack(Items.iron_boots);
					mob.setCurrentItemOrArmor(1, boots);
				}
			}
			else if(result > 90){//10
				if(random.nextInt(2)== 0){
					ItemStack helm = new ItemStack(Items.diamond_helmet);
					mob.setCurrentItemOrArmor(4, helm);
				}
				if(random.nextInt(2)== 0){
					ItemStack chest = new ItemStack(Items.diamond_chestplate);
					mob.setCurrentItemOrArmor(3, chest);
				}
				if(random.nextInt(2)== 0){
					ItemStack legs = new ItemStack(Items.diamond_leggings);
					mob.setCurrentItemOrArmor(2, legs);
				}
				if(random.nextInt(2)== 0){
					ItemStack boots = new ItemStack(Items.diamond_boots);
					mob.setCurrentItemOrArmor(1, boots);
				}
			}
			
		}
	}
}
