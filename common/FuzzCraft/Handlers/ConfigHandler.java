package FuzzCraft.Handlers;

import java.io.File;
import java.util.logging.Level;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.event.Event;
import cpw.mods.fml.common.FMLLog;

public abstract class ConfigHandler  {
    
    public static void init(File configFile) {
        
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
       
        try {
            config.load();   
        }            
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, "Error Loading FuzzCraft Configuration!");               
        } 
        finally {
            FMLLog.log(Level.INFO, "FuzzCraft Configuration Loaded Successfully!");                
            config.save();
        }   
    }
}
