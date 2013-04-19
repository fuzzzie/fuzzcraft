package FuzzCraft.Base;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import FuzzCraft.Blocks.BrickBlock;
import FuzzCraft.Blocks.BrickBlockItem;
import FuzzCraft.Blocks.ChisBrickBlock;
import FuzzCraft.Blocks.ChisBrickBlockItem;
import FuzzCraft.Blocks.StoneBlock;
import FuzzCraft.Blocks.StoneBlockItem;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {

    public void registerBlocks() {
        
        // Standard Blocks

//      LanguageRegistry.addName(colorizorBlockI, "Colorizor");
//      GameRegistry.registerBlock(colorizorBlockI, "colorizorI");
//      MinecraftForge.setBlockHarvestLevel(colorizorBlockI, "Pick", 3);

//      LanguageRegistry.addName(colorizorBlockA, "Colorizor");
//      GameRegistry.registerBlock(colorizorBlockA, "colorizorA");
//      MinecraftForge.setBlockHarvestLevel(colorizorBlockA, "Pick", 3);
        
        
        LanguageRegistry.addName(fuzzcraft.repulsionBlock, "Repulsion Block");
        GameRegistry.registerBlock(fuzzcraft.repulsionBlock, "repulsionBlock");
        MinecraftForge.setBlockHarvestLevel(fuzzcraft.repulsionBlock, "Pick", 0);

        LanguageRegistry.addName(fuzzcraft.enhancedspawnerBlockI, "Enhanced Zombie Spawner");
        GameRegistry.registerBlock(fuzzcraft.enhancedspawnerBlockI, "zombieespawnerBlockI");
        MinecraftForge.setBlockHarvestLevel(fuzzcraft.enhancedspawnerBlockI, "Pick", 3);

        LanguageRegistry.addName(fuzzcraft.enhancedspawnerBlockA, "Enhanced Zombie Spawner");
        GameRegistry.registerBlock(fuzzcraft.enhancedspawnerBlockA, "zombieespawnerBlockA");
        MinecraftForge.setBlockHarvestLevel(fuzzcraft.enhancedspawnerBlockA, "Pick", 3);
     
        // Colored Stone

        MinecraftForge.setBlockHarvestLevel(fuzzcraft.stoneBlock, "Pick", 0);
        GameRegistry.registerBlock(fuzzcraft.stoneBlock, StoneBlockItem.class,
                "stoneBlock");
        for (int i = 0; i < 15; i++) {
            LanguageRegistry.addName(new ItemStack(fuzzcraft.stoneBlock, 1, i),
                    StoneBlock.stoneBlockNames[i]);
        }

        // Colored Brick

        MinecraftForge.setBlockHarvestLevel(fuzzcraft.brickBlock, "Pick", 0);
        GameRegistry.registerBlock(fuzzcraft.brickBlock, BrickBlockItem.class,
                "brickBlock");
        for (int i = 0; i < 15; i++) {
            LanguageRegistry.addName(new ItemStack(fuzzcraft.brickBlock, 1, i),
                    BrickBlock.brickBlockNames[i]);
        }

        // Colored Chiseled Brick

        MinecraftForge.setBlockHarvestLevel(fuzzcraft.chisbrickBlock, "Pick", 0);
        GameRegistry.registerBlock(fuzzcraft.chisbrickBlock, ChisBrickBlockItem.class,
                "chisbrickBlock");
        for (int i = 0; i < 15; i++) {
            LanguageRegistry.addName(new ItemStack(fuzzcraft.chisbrickBlock, 1, i),
                    ChisBrickBlock.chisbrickBlockNames[i]);
        }

       
       
    }
    
    public void registerGUI() {
        NetworkRegistry.instance().registerGuiHandler(this, fuzzcraft.guiHandler);
        
    }
    
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(
                FuzzCraft.TileEntity.colorizorTileEntity.class,
                "colorizor_tileEntity");
        
        GameRegistry.registerTileEntity(FuzzCraft.TileEntity.enhancedSpawnerTileEntity.class, 
                "enhancedspawner_tileentity");
    }
    
    public void registerRecipies() {
        
        // Brick Recipes

        for (int i = 0; i < 15; i++) {
            ItemStack stoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, i);
            GameRegistry.addRecipe(new ItemStack(fuzzcraft.brickBlock, 1, i), "xx", "xx",
                    'x', stoneStack);
        }

        // Chiseled Brick Recipes

        for (int i = 0; i < 15; i++) {
            ItemStack brickStack = new ItemStack(fuzzcraft.brickBlock, 1, i);
            GameRegistry.addRecipe(new ItemStack(fuzzcraft.chisbrickBlock, 1, i), "xx",
                    "xx", 'x', brickStack); 
        }
        
        // Colored Stone
        
        
            
            
            
         
        
        
    }
  

}