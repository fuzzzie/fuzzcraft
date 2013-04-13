package FuzzCraft.Base;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import FuzzCraft.Blocks.*;
import FuzzCraft.Handlers.*;
import FuzzCraft.Items.*;
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
    
    public static int repulsorPower = 15;
    
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

                Property rP = fc_config.get(Configuration.CATEGORY_GENERAL, "repulsion_power", 15);
                rP.comment = "Set the power of Repulsion Blocks (Between 1 - 30, Default 15)"; 
                repulsorPower = rP.getInt();
                
                if (repulsorPower == 0) {
                    repulsorPower = 1;
                }
                else if  (repulsorPower > 30) {
                    repulsorPower = 30;
                }
                
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
            repulsionBlock = new RepulsionBlock(blockIdRep.getInt(), repulsorPower);
            
            
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
                        ColorCharge.colorChargeNames[i]); }
            
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
            
            // Temp Colorizor Recipes
            
            ItemStack stoneStack  = new ItemStack(Block.stone);
            
            ItemStack colorizorStack0 = new ItemStack(fuzzcraft.colorCharge, 64, 0);
            ItemStack colorizorStack1 = new ItemStack(fuzzcraft.colorCharge, 64, 1);
            ItemStack colorizorStack2 = new ItemStack(fuzzcraft.colorCharge, 64, 2);
            ItemStack colorizorStack3 = new ItemStack(fuzzcraft.colorCharge, 64, 3);
            ItemStack colorizorStack4 = new ItemStack(fuzzcraft.colorCharge, 64, 4);
            ItemStack colorizorStack5 = new ItemStack(fuzzcraft.colorCharge, 64, 5);
            ItemStack colorizorStack6 = new ItemStack(fuzzcraft.colorCharge, 64, 6);
            ItemStack colorizorStack7 = new ItemStack(fuzzcraft.colorCharge, 64, 7);
            ItemStack colorizorStack8 = new ItemStack(fuzzcraft.colorCharge, 64, 8);
            ItemStack colorizorStack9 = new ItemStack(fuzzcraft.colorCharge, 64, 9);
            ItemStack colorizorStack10 = new ItemStack(fuzzcraft.colorCharge, 64, 10);
            ItemStack colorizorStack11 = new ItemStack(fuzzcraft.colorCharge, 64, 11);
            ItemStack colorizorStack12 = new ItemStack(fuzzcraft.colorCharge, 64, 12);
            ItemStack colorizorStack13 = new ItemStack(fuzzcraft.colorCharge, 64, 13);
            ItemStack colorizorStack14 = new ItemStack(fuzzcraft.colorCharge, 64, 14); 
            
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 0), stoneStack, colorizorStack0);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 1), stoneStack, colorizorStack1);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 2), stoneStack, colorizorStack2);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 3), stoneStack, colorizorStack3);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 4), stoneStack, colorizorStack4);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 5), stoneStack, colorizorStack5);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 6), stoneStack, colorizorStack6);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 7), stoneStack, colorizorStack7);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 8), stoneStack, colorizorStack8);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 9), stoneStack, colorizorStack9);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 10), stoneStack, colorizorStack10);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 11), stoneStack, colorizorStack11);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 12), stoneStack, colorizorStack12);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 13), stoneStack, colorizorStack13);
            GameRegistry.addShapelessRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 14), stoneStack, colorizorStack14); 
                
            NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
            GameRegistry.registerTileEntity(FuzzCraft.TileEntity.colorizor_tileEntity.class,"colorizor_tileEntity");
            
        }
            
        
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
        }
  }