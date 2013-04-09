package FuzzCraft.Base;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import FuzzCraft.Blocks.EnderFlower;


@Mod(modid="modinfo.MOD_ID", name="modinfo.MOD_NAME", version="modinfo.MOD_VERSION")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)

public class fuzzcraft {

        @Instance(modinfo.MOD_ID)
        public static fuzzcraft instance;
       
        @SidedProxy(clientSide=modinfo.CLIENT_PROXY, serverSide=modinfo.SERVER_PROXY)
        public static CommonProxy proxy;
        
        private static Property blockIdFlower;

        
        @PreInit
        public void preInit(FMLPreInitializationEvent event) { 
            
            Configuration fc_config = new Configuration(event.getSuggestedConfigurationFile());
            loadConfig(fc_config);
                        
        }
       
        @Init
        public void load(FMLInitializationEvent event) {
            proxy.registerRenderers();
         
            // Init blocks
            EnderFlower enderFlower = new EnderFlower(blockIdFlower.getInt());
            
            // Register Blocks
            
            LanguageRegistry.addName(enderFlower, "Ender Flower");
            MinecraftForge.setBlockHarvestLevel(enderFlower, "Shovel", 0);
            GameRegistry.registerBlock(enderFlower, "enderFlower");
            
            
        }
            
            
       
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {        
        }
        
        private static void loadConfig(Configuration fc_config)
        {
        blockIdFlower = fc_config.getBlock("ID.BlockCommon", 1500);
        fc_config.save();
        }

}