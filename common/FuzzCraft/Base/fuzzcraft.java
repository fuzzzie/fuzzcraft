package FuzzCraft.Base;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import FuzzCraft.Blocks.BrickBlock;
import FuzzCraft.Blocks.ChisBrickBlock;
import FuzzCraft.Blocks.EnhancedSpawner;
import FuzzCraft.Blocks.RepulsionBlock;
import FuzzCraft.Blocks.StoneBlock;
import FuzzCraft.Handlers.GUI_Handler;
import FuzzCraft.Items.ColorCharge;
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

@Mod(modid = "fuzzcraft", name = "fuzzcraft", version = "0.1.1a")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels={"FuzzCraft"}, packetHandler = FuzzCraft.Handlers.PacketHandler.class)
public class fuzzcraft {

    private static Property blockIdStone, blockIdBrick, blockIdChisBrick,
            blockIdRep, blockIdSpawnerI, blockIdSpawnerA, itemIdColorCharge;

    
//    private staic Property blockIdColorizorI, blockIdColorizorA;

    public static int repulsorPower = 15;
    public static int seConfig = 1 ;
    public static boolean spawnerEmit = true;
 
    static GUI_Handler guiHandler = new GUI_Handler();

    public static StoneBlock stoneBlock;
    public static BrickBlock brickBlock;
    public static ChisBrickBlock chisbrickBlock;
    public static RepulsionBlock repulsionBlock;
    public static EnhancedSpawner enhancedspawnerBlockI;
    public static EnhancedSpawner enhancedspawnerBlockA;
//  public static Colorizor colorizorBlockA;
//  public static Colorizor colorizorBlockI;
    
    public static ColorCharge colorCharge;

    
    
    @Instance("fuzzcraft")
    public static fuzzcraft instance;

    @SidedProxy(clientSide = modinfo.CLIENT_PROXY, serverSide = modinfo.SERVER_PROXY)
    public static CommonProxy proxy;
    

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {

        Configuration fc_config = new Configuration(event.getSuggestedConfigurationFile());

        try {
            fc_config.load();

            blockIdStone = fc_config.getBlock("ID.Stone", 1500);
            blockIdBrick = fc_config.getBlock("ID.Brick", 1502);
            blockIdChisBrick = fc_config.getBlock("ID.Chiseled_Brick", 1503);
            
            blockIdRep = fc_config.getBlock("ID.Repulsor_Block", 1505);

            blockIdSpawnerI = fc_config.getBlock("ID.Spawner", 1506);
            blockIdSpawnerA = fc_config.getBlock("ID.ActiveSpawner", 1507);

//          blockIdColorizorA = fc_config.getBlock("ID.Colorizor_Active", 1504);
//          blockIdColorizorI = fc_config.getBlock("Id.Colorizor_Inactive", 1501);
            
            itemIdColorCharge = fc_config.getItem("Id.Color_Charge", 8500);
            
            Property rP = fc_config.get(Configuration.CATEGORY_GENERAL, "repulsion_power", 15);
            rP.comment = "Set the power of Repulsion Blocks (Between 1 - 30, Default 15)";
            repulsorPower = rP.getInt();

            if (repulsorPower == 0) {
                repulsorPower = 1;
            } else if (repulsorPower > 30) {
                repulsorPower = 30;
            }
            
            Property sE = fc_config.get(Configuration.CATEGORY_GENERAL, "SpawnersEmitLight", 1);
            sE.comment = "Mob Spawners Emit Light (1 = True, 0 = False";
            seConfig = sE.getInt();
            
            if (seConfig == 1) {
                spawnerEmit = true; }
            else if (seConfig == 0) {
                spawnerEmit = false;}
            else if (seConfig > 1) {
                spawnerEmit = false;
            }
            
        } catch (Exception e) {
            FMLLog.log(Level.SEVERE, e,
                    "Error loading FuzzCraft configuration file!");
        } finally {
            FMLLog.log(Level.INFO,
                    "FuzzCraft configuration loaded sucessfully! ");
            fc_config.save();
        }
    
    // Init blocks
        
    stoneBlock = new StoneBlock(blockIdStone.getInt());
    brickBlock = new BrickBlock(blockIdBrick.getInt());
    chisbrickBlock = new ChisBrickBlock(blockIdChisBrick.getInt());
    repulsionBlock = new RepulsionBlock(blockIdRep.getInt(), repulsorPower);
    enhancedspawnerBlockI = new EnhancedSpawner(blockIdSpawnerI.getInt(), false, spawnerEmit);
    enhancedspawnerBlockA = new EnhancedSpawner(blockIdSpawnerA.getInt(), true, spawnerEmit);
//  colorizorBlockI = new Colorizor(blockIdColorizorI.getInt(), false);
//  colorizorBlockA = new Colorizor(blockIdColorizorA.getInt(), true);
    
    colorCharge = new ColorCharge(itemIdColorCharge.getInt());
    
    }
    
    
    @Init
    public void load(FMLInitializationEvent event) {

        proxy.registerBlocks();
        
        proxy.registerItems();
        
        proxy.registerGUI();
      
        proxy.registerTileEntities();
        
        proxy.registerRecipies();
         
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
    }
}