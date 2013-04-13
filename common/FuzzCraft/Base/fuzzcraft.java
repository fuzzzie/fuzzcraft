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
import FuzzCraft.Blocks.EnderFlower;
import FuzzCraft.Blocks.RepulsionBlock;
import FuzzCraft.Blocks.StoneBlock;
import FuzzCraft.Blocks.StoneBlockItem;
import FuzzCraft.Handlers.*;
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

@Mod(modid="fuzzcraft", name="fuzzcraft", version="0.1.0a")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)

public class fuzzcraft {
    
    private static Property blockIdFlower, blockIdStone, blockIdBrick, blockIdChisBrick, blockIdColorizor,
        blockIdRep, itemIdCharge;
    
    public static Property repulsorPower;
    
    private Colorizor_Handler guiHandler = new Colorizor_Handler();
    
    public static ColorCharge colorCharge;
    public static EnderFlower enderFlower;
    public static StoneBlock stoneBlock;
    public static BrickBlock brickBlock;
    public static ChisBrickBlock chisbrickBlock;
    public static Colorizor colorizorBlock;
    public static RepulsionBlock repulsionBlock;
    
        @Instance("fuzzcraft")
        public static fuzzcraft instance;
       
        @SidedProxy(clientSide=modinfo.CLIENT_PROXY, serverSide=modinfo.SERVER_PROXY)
        public static CommonProxy proxy;
        
        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
            
            Configuration fc_config = new Configuration(event.getSuggestedConfigurationFile());
            
            try {
                fc_config.load();
                
                blockIdStone = fc_config.getBlock("ID.Stone", 1500);
                blockIdFlower = fc_config.getBlock("ID.Flower", 1501);
                blockIdBrick = fc_config.getBlock("ID.Brick", 1502);
                blockIdChisBrick = fc_config.getBlock("ID.Chiseled_Brick", 1503);
                blockIdColorizor = fc_config.getBlock("ID.Colorizor", 1504);
                blockIdRep = fc_config.getBlock("ID.Repulsor_Block", 1505);
                itemIdCharge = fc_config.getItem("ID.Color_Charges", 10000);
                repulsorPower.comment = "Set the power of Rupulsion Blocks (Default 2)";
                repulsorPower = fc_config.get(Configuration.CATEGORY_GENERAL, "RepulsionPower", 2);
            }
            catch (Exception e) {
                FMLLog.log(Level.SEVERE, e, "Error loading FuzzCraft configuration file!");
            }
            finally {
                FMLLog.log(Level.INFO, "FuzzCraft configuration loaded sucessfully! ");
                fc_config.save();
            }
        }
       
        @Init
        public void load(FMLInitializationEvent event) {
            
          
            proxy.registerRenderers();
         
            // Init blocks
            enderFlower = new EnderFlower(blockIdFlower.getInt());
            stoneBlock = new StoneBlock(blockIdStone.getInt());
            brickBlock = new BrickBlock(blockIdBrick.getInt());
            chisbrickBlock = new ChisBrickBlock(blockIdChisBrick.getInt());
            colorizorBlock = new Colorizor(blockIdColorizor.getInt());
            repulsionBlock = new RepulsionBlock(blockIdRep.getInt(), repulsorPower.getInt());
            
            // Init Items
            colorCharge = new ColorCharge(itemIdCharge.getInt());
            
            // Register Blocks //
            
            // Standard Blocks
            LanguageRegistry.addName(enderFlower, "Ender Flower");
            GameRegistry.registerBlock(enderFlower, "enderFlower");
            
            LanguageRegistry.addName(colorizorBlock, "Colorizor");
            GameRegistry.registerBlock(colorizorBlock, "colorizor");
            MinecraftForge.setBlockHarvestLevel(colorizorBlock, "Pick", 3);
            
            LanguageRegistry.addName(repulsionBlock, "Repulsion Block");
            GameRegistry.registerBlock(repulsionBlock, "repulsionBlock");
            MinecraftForge.setBlockHarvestLevel(repulsionBlock, "Pick", 0);
            
            // Color Charges
            GameRegistry.registerItem(colorCharge, "colorCharge");
            for (int i = 0; i < 15; i++) {
                LanguageRegistry.addName(new ItemStack(colorCharge, 1, i),
                        ColorCharge.colorChargeNames[i]);
                
            }
           
            // Colored Stone
            
            MinecraftForge.setBlockHarvestLevel(stoneBlock, "Pick", 0);
            GameRegistry.registerBlock(stoneBlock, StoneBlockItem.class, "stoneBlock");
            for (int i = 0; i < 15; i++) {
                LanguageRegistry.addName(new ItemStack(stoneBlock, 1, i),
                       StoneBlock.stoneBlockNames[i]); }
            
            // Colored Brick
            
            MinecraftForge.setBlockHarvestLevel(brickBlock, "Pick", 0);
            GameRegistry.registerBlock(brickBlock, BrickBlockItem.class, "brickBlock");
            for (int i = 0; i < 15; i++){
                LanguageRegistry.addName(new ItemStack(brickBlock, 1, i),
                        BrickBlock.brickBlockNames[i]); }
            
            // Colored Chiseled Brick
            
            MinecraftForge.setBlockHarvestLevel(chisbrickBlock, "Pick", 0);
            GameRegistry.registerBlock(chisbrickBlock, ChisBrickBlockItem.class, "chisbrickBlock");
            for (int i = 0; i < 15; i++){
                LanguageRegistry.addName(new ItemStack(chisbrickBlock, 1, i),
                        ChisBrickBlock.chisbrickBlockNames[i]); }
          
           
           // Brick Recipes
            
            for (int i = 0; i < 15; i++) {
                ItemStack stoneStack = new ItemStack(stoneBlock, 1, i);
                GameRegistry.addRecipe(new ItemStack(brickBlock, 1, i),
                       "xx", "xx", 'x', stoneStack); }
            
           // Chiseled Brick Recipes
            
            for (int i = 0; i <15; i++) {
                ItemStack brickStack = new ItemStack(brickBlock, 1, i);
                GameRegistry.addRecipe(new ItemStack(chisbrickBlock, 1, i),
                        "xx", "xx", 'x', brickStack); }
            
            NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
            GameRegistry.registerTileEntity(FuzzCraft.TileEntity.colorizor_tileEntity.class,"tileEntityYourFurnace");

            
            
       
        }
            
        
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
        }
  }