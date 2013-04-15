package FuzzCraft.Base;

import java.util.logging.Level;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import FuzzCraft.Blocks.BrickBlock;
import FuzzCraft.Blocks.BrickBlockItem;
import FuzzCraft.Blocks.ChisBrickBlock;
import FuzzCraft.Blocks.ChisBrickBlockItem;
import FuzzCraft.Blocks.Colorizor;
import FuzzCraft.Blocks.RepulsionBlock;
import FuzzCraft.Blocks.StoneBlock;
import FuzzCraft.Blocks.StoneBlockItem;
import FuzzCraft.Blocks.EnhancedSpawner;
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
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "fuzzcraft", name = "fuzzcraft", version = "0.1.1a")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class fuzzcraft {

    private static Property blockIdStone, blockIdBrick, blockIdChisBrick,
            blockIdColorizor, blockIdRep, blockIdSpawnerI, blockIdSpawnerA,
            itemIdCharge;

    public static int repulsorPower = 15;
    public static int seConfig = 1 ;
    public static boolean spawnerEmit = true;
    

    private GUI_Handler guiHandler = new GUI_Handler();

    public static ColorCharge colorCharge;

    public static StoneBlock stoneBlock;
    public static BrickBlock brickBlock;
    public static ChisBrickBlock chisbrickBlock;
    public static Colorizor colorizorBlock;
    public static RepulsionBlock repulsionBlock;
    public static EnhancedSpawner enhancedspawnerBlockI;
    public static EnhancedSpawner enhancedspawnerBlockA;

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
            blockIdColorizor = fc_config.getBlock("ID.Colorizor", 1504);
            blockIdRep = fc_config.getBlock("ID.Repulsor_Block", 1505);

            blockIdSpawnerI = fc_config.getBlock("ID.Spawner", 1506);
            blockIdSpawnerA = fc_config.getBlock("ID.ActiveSpawner", 1507);

            itemIdCharge = fc_config.getItem("ID.White_Charge", 10000);

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
    }

    @Init
    public void load(FMLInitializationEvent event) {

        proxy.registerRenderers();

        // Init blocks
        stoneBlock = new StoneBlock(blockIdStone.getInt());
        brickBlock = new BrickBlock(blockIdBrick.getInt());
        chisbrickBlock = new ChisBrickBlock(blockIdChisBrick.getInt());
        colorizorBlock = new Colorizor(blockIdColorizor.getInt());
        repulsionBlock = new RepulsionBlock(blockIdRep.getInt(), repulsorPower);
        enhancedspawnerBlockI = new EnhancedSpawner(blockIdSpawnerI.getInt(), false, spawnerEmit);
        enhancedspawnerBlockA = new EnhancedSpawner(blockIdSpawnerA.getInt(), true, spawnerEmit);

        // Init Items
        colorCharge = new ColorCharge(itemIdCharge.getInt());

        // Register Blocks //

        // Standard Blocks

        LanguageRegistry.addName(colorizorBlock, "Colorizor");
        GameRegistry.registerBlock(colorizorBlock, "colorizor");
        MinecraftForge.setBlockHarvestLevel(colorizorBlock, "Pick", 3);

        LanguageRegistry.addName(repulsionBlock, "Repulsion Block");
        GameRegistry.registerBlock(repulsionBlock, "repulsionBlock");
        MinecraftForge.setBlockHarvestLevel(repulsionBlock, "Pick", 0);

        LanguageRegistry.addName(enhancedspawnerBlockI, "Enhanced Zombie Spawner");
        GameRegistry.registerBlock(enhancedspawnerBlockI, "zombieespawnerBlockI");
        MinecraftForge.setBlockHarvestLevel(enhancedspawnerBlockI, "Pick", 3);

        LanguageRegistry.addName(enhancedspawnerBlockA, "Enhanced Zombie Spawner");
        GameRegistry.registerBlock(enhancedspawnerBlockA, "zombieespawnerBlockA");
        MinecraftForge.setBlockHarvestLevel(enhancedspawnerBlockA, "Pick", 3);

        // Color Charges
        GameRegistry.registerItem(colorCharge, "colorCharge0");

        for (int i = 0; i < 15; i++) {
            LanguageRegistry.addName(new ItemStack(colorCharge, 1, i),
                    ColorCharge.colorChargeNames[i]);
        }

        // Colored Stone

        MinecraftForge.setBlockHarvestLevel(stoneBlock, "Pick", 0);
        GameRegistry.registerBlock(stoneBlock, StoneBlockItem.class,
                "stoneBlock");
        for (int i = 0; i < 15; i++) {
            LanguageRegistry.addName(new ItemStack(stoneBlock, 1, i),
                    StoneBlock.stoneBlockNames[i]);
        }

        // Colored Brick

        MinecraftForge.setBlockHarvestLevel(brickBlock, "Pick", 0);
        GameRegistry.registerBlock(brickBlock, BrickBlockItem.class,
                "brickBlock");
        for (int i = 0; i < 15; i++) {
            LanguageRegistry.addName(new ItemStack(brickBlock, 1, i),
                    BrickBlock.brickBlockNames[i]);
        }

        // Colored Chiseled Brick

        MinecraftForge.setBlockHarvestLevel(chisbrickBlock, "Pick", 0);
        GameRegistry.registerBlock(chisbrickBlock, ChisBrickBlockItem.class,
                "chisbrickBlock");
        for (int i = 0; i < 15; i++) {
            LanguageRegistry.addName(new ItemStack(chisbrickBlock, 1, i),
                    ChisBrickBlock.chisbrickBlockNames[i]);
        }

        // Brick Recipes

        for (int i = 0; i < 15; i++) {
            ItemStack stoneStack = new ItemStack(stoneBlock, 1, i);
            GameRegistry.addRecipe(new ItemStack(brickBlock, 1, i), "xx", "xx",
                    'x', stoneStack);
        }

        // Chiseled Brick Recipes

        for (int i = 0; i < 15; i++) {
            ItemStack brickStack = new ItemStack(brickBlock, 1, i);
            GameRegistry.addRecipe(new ItemStack(chisbrickBlock, 1, i), "xx",
                    "xx", 'x', brickStack);
        }

        NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
        GameRegistry.registerTileEntity(
                FuzzCraft.TileEntity.colorizor_tileEntity.class,
                "colorizor_tileEntity");

    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
    }
}