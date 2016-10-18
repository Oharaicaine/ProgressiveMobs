package com.oharaicaine.progressivemobs;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

import java.util.Random;

public class MobArmouryHandler {
	
	private static Random random = new Random();
	
	public static void ZombieArmoury(EntityZombie zombie, float scale){

		ArmourSet(zombie, scale);
		WeaponSet(zombie, scale);
	}

	public static void SkeletonArmoury(EntitySkeleton skeleton, float scale){

		ArmourSet(skeleton, scale);
		if(skeleton.getEntityId() == 0)WeaponSet(skeleton, scale);

	}
	
	private static void WeaponSet(EntityMob mob, float scale) {

		if(1+(random.nextInt(30)+(scale*2)) >= 30 ){

			if(mob instanceof EntityZombie){

				int result = (1+random.nextInt(80))+Math.round(scale*5);

				if(result <= 40){

					ItemStack sword = new ItemStack(Items.WOODEN_SWORD);
                    mob.setHeldItem(EnumHand.MAIN_HAND, sword);

				}

				else if(result > 40 && result <= 70){

					ItemStack sword = new ItemStack(Items.WOODEN_SWORD);
                    mob.setHeldItem(EnumHand.MAIN_HAND, sword);

				}

				else if(result > 70 && result <= 90){

					ItemStack sword = new ItemStack(Items.WOODEN_SWORD);
                    mob.setHeldItem(EnumHand.MAIN_HAND, sword);

				}

				else if(result > 90){

					ItemStack sword = new ItemStack(Items.WOODEN_SWORD);
                    mob.setHeldItem(EnumHand.MAIN_HAND, sword);

				}

			}

			if(mob instanceof EntitySkeleton){

				int result = (1+random.nextInt(80))+Math.round(scale*5);

				ItemStack bow = new ItemStack(Items.BOW);

                if(result <= 40) {bow.addEnchantment(Enchantment.getEnchantmentByID(49), 1);} // Punch

				else if(result > 40 && result <= 70) {bow.addEnchantment(Enchantment.getEnchantmentByID(48), 1);} // Power

				else if(result > 70 && result <= 90) {bow.addEnchantment(Enchantment.getEnchantmentByID(50), 1);} // Flame

				else if(result > 90){

                    bow.addEnchantment(Enchantment.getEnchantmentByID(48), 2); // Power

					bow.addEnchantment(Enchantment.getEnchantmentByID(49), 1); // Punch

					bow.addEnchantment(Enchantment.getEnchantmentByID(50), 1); // Flame

				}

				if((mob).getEntityId() == 0)mob.setHeldItem(EnumHand.MAIN_HAND, bow);

			}

		}

	}

	private static void ArmourSet(EntityMob mob, float scale){

		if(1+(random.nextInt(30)+(scale*2)) >= 30 ){

			int result = (1+random.nextInt(80))+Math.round(scale*5);
			if(result <= 40){

				if(random.nextInt(2)== 0){

					ItemStack helm = new ItemStack(Items.LEATHER_HELMET);
					mob.setItemStackToSlot(EntityEquipmentSlot.HEAD, helm);

				}

				if(random.nextInt(2)== 0){

					ItemStack chest = new ItemStack(Items.LEATHER_CHESTPLATE);
                    mob.setItemStackToSlot(EntityEquipmentSlot.CHEST, chest);

				}

				if(random.nextInt(2)== 0){

					ItemStack legs = new ItemStack(Items.LEATHER_LEGGINGS);
                    mob.setItemStackToSlot(EntityEquipmentSlot.LEGS, legs);

				}

				if(random.nextInt(2)== 0){

					ItemStack feet = new ItemStack(Items.LEATHER_BOOTS);
                    mob.setItemStackToSlot(EntityEquipmentSlot.FEET, feet);

				}

            }
			else if(result > 40 && result <= 70){

				if(random.nextInt(2)== 0){

					ItemStack helm = new ItemStack(Items.CHAINMAIL_HELMET);
                    mob.setItemStackToSlot(EntityEquipmentSlot.HEAD, helm);

				}

				if(random.nextInt(2)== 0){

					ItemStack chest = new ItemStack(Items.CHAINMAIL_CHESTPLATE);
                    mob.setItemStackToSlot(EntityEquipmentSlot.CHEST, chest);

				}

				if(random.nextInt(2)== 0){

					ItemStack legs = new ItemStack(Items.CHAINMAIL_LEGGINGS);
                    mob.setItemStackToSlot(EntityEquipmentSlot.LEGS, legs);

				}

				if(random.nextInt(2)== 0){

					ItemStack feet = new ItemStack(Items.CHAINMAIL_BOOTS);
                    mob.setItemStackToSlot(EntityEquipmentSlot.FEET, feet);

				}
			}
			else if(result > 70 && result <= 90){

				if(random.nextInt(2)== 0){

					ItemStack helm = new ItemStack(Items.IRON_HELMET);
                    mob.setItemStackToSlot(EntityEquipmentSlot.HEAD, helm);

				}

				if(random.nextInt(2)== 0){

					ItemStack chest = new ItemStack(Items.IRON_CHESTPLATE);
                    mob.setItemStackToSlot(EntityEquipmentSlot.CHEST, chest);

				}

				if(random.nextInt(2)== 0){
					ItemStack legs = new ItemStack(Items.IRON_LEGGINGS);
                    mob.setItemStackToSlot(EntityEquipmentSlot.LEGS, legs);
				}

				if(random.nextInt(2)== 0){

					ItemStack feet = new ItemStack(Items.IRON_BOOTS);
                    mob.setItemStackToSlot(EntityEquipmentSlot.FEET, feet);

				}
			}

			else if(result > 90){

				if(random.nextInt(2)== 0){

					ItemStack helm = new ItemStack(Items.DIAMOND_HELMET);
                    mob.setItemStackToSlot(EntityEquipmentSlot.HEAD, helm);

				}

				if(random.nextInt(2)== 0){

					ItemStack chest = new ItemStack(Items.DIAMOND_CHESTPLATE);
                    mob.setItemStackToSlot(EntityEquipmentSlot.CHEST, chest);

				}

				if(random.nextInt(2)== 0){

					ItemStack legs = new ItemStack(Items.DIAMOND_LEGGINGS);
                    mob.setItemStackToSlot(EntityEquipmentSlot.LEGS, legs);

				}

				if(random.nextInt(2)== 0){

					ItemStack feet = new ItemStack(Items.DIAMOND_BOOTS);
                    mob.setItemStackToSlot(EntityEquipmentSlot.FEET, feet);

				}

			}
			
		}

	}

}
