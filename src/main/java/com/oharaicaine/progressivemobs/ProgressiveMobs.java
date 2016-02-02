package com.oharaicaine.progressivemobs;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = "0.1")
public class ProgressiveMobs {

	@Instance(Reference.MOD_ID)
	public static ProgressiveMobs instance;
	
	@EventHandler
	public void perInit(FMLPreInitializationEvent event){
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		
	//	MinecraftForge.EVENT_BUS.register(new MobarmouryHandler());
		MinecraftForge.EVENT_BUS.register(new ProgressEventHandler());
	}
	
	
	
}
