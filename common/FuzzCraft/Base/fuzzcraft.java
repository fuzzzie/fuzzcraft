package FuzzCraft.Base;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
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

@Mod(modid="modinfo.MOD_ID", name="modinfo.MOD_NAME", version="modinfo.MOD_VERSION", dependencies="modinfo.DEPENDENCIES")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)

public class fuzzcraft {

        @Instance(modinfo.MOD_ID)
        public static fuzzcraft instance;
       
        @SidedProxy(clientSide=modinfo.CLIENT_PROXY, serverSide=modinfo.SERVER_PROXY)
        public static CommonProxy proxy;
       
        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
            
            Configuration config = new Configuration(event.getSuggestedConfigurationFile());
            
            try {
                config.load();
            } catch (Exception e) {
                FMLLog.log(Level.SEVERE, e, "Error Loading FuzzCraft Configuration!");               
            } finally {
                FMLLog.log(Level.INFO, "FuzzCraft Configuration Loaded Successfully!");                
                config.save();
            }
            
        }
       
        @Init
        public void load(FMLInitializationEvent event) {
                
            
            proxy.registerRenderers();
        }
       
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
               
        }
}