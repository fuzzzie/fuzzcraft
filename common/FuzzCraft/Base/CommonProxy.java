package FuzzCraft.Base;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import FuzzCraft.Blocks.BrickBlock;
import FuzzCraft.Blocks.BrickBlockItem;
import FuzzCraft.Blocks.BrickStairsBlock;
import FuzzCraft.Blocks.ChisBrickBlock;
import FuzzCraft.Blocks.ChisBrickBlockItem;
import FuzzCraft.Blocks.StoneBlock;
import FuzzCraft.Blocks.StoneBlockItem;
import FuzzCraft.Blocks.SpikeBlock;
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
        
        LanguageRegistry.addName(fuzzcraft.spikeBlock, "Spike");
        GameRegistry.registerBlock(fuzzcraft.spikeBlock, "spikeBlock");
        MinecraftForge.setBlockHarvestLevel(fuzzcraft.spikeBlock, "Pick", 0);

//      LanguageRegistry.addName(fuzzcraft.enhancedspawnerBlockI, "Enhanced Zombie Spawner");
//      GameRegistry.registerBlock(fuzzcraft.enhancedspawnerBlockI, "zombieespawnerBlockI");
//      MinecraftForge.setBlockHarvestLevel(fuzzcraft.enhancedspawnerBlockI, "Pick", 3);

//      LanguageRegistry.addName(fuzzcraft.enhancedspawnerBlockA, "Enhanced Zombie Spawner");
//      GameRegistry.registerBlock(fuzzcraft.enhancedspawnerBlockA, "zombieespawnerBlockA");
//      MinecraftForge.setBlockHarvestLevel(fuzzcraft.enhancedspawnerBlockA, "Pick", 3);
     
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
        
        MinecraftForge.setBlockHarvestLevel(fuzzcraft.brickStairsBlock, "Pick", 1);
        GameRegistry.registerBlock(fuzzcraft.brickStairsBlock, "brickStairsBlock");
        for (int i = 0; i < 15; i++) {
            LanguageRegistry.addName(new ItemStack(fuzzcraft.brickStairsBlock, 1, i),
                    BrickStairsBlock.brickStairNames[i]);
        }

       
       
    }
    
    public void registerGUI() {
        NetworkRegistry.instance().registerGuiHandler(this, fuzzcraft.guiHandler);
        
       
    }
    
/*    public void registerTileEntities() {
        GameRegistry.registerTileEntity(
                FuzzCraft.TileEntity.colorizorTileEntity.class,
                "colorizor_tileEntity");
        
        GameRegistry.registerTileEntity(FuzzCraft.TileEntity.enhancedSpawnerTileEntity.class, 
                "enhancedspawner_tileentity");
    } */
    
    public void registerItems() {
        LanguageRegistry.addName(fuzzcraft.colorCharge, "Color Charge");
        
    }
    
    public void registerRecipies() {
        
        // Color Charge
        
        ItemStack blueStack = new ItemStack(Item.dyePowder, 1, 4);
        ItemStack redStack = new ItemStack(Item.dyePowder, 1, 1);
        ItemStack greenStack = new ItemStack(Item.dyePowder, 1, 2);
        ItemStack yellowStack = new ItemStack(Item.dyePowder, 1, 11);
        ItemStack whiteStack = new ItemStack(Item.dyePowder, 1, 15);
        ItemStack blackStack = new ItemStack(Item.dyePowder, 1, 0);
        ItemStack slimeStack = new ItemStack(Item.slimeBall);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.colorCharge, 64), "wbk", "rsg", "kyw",
                'w', whiteStack, 'b', blueStack, 'k', blackStack, 'r', redStack, 's', slimeStack, 
                'g', greenStack, 'y', yellowStack);
        
        // Brick Recipes

        for (int i = 0; i < 15; i++) {
            ItemStack stoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, i);
            GameRegistry.addRecipe(new ItemStack(fuzzcraft.brickBlock, 4, i), "xx", "xx",
                    'x', stoneStack);
        }

        // Chiseled Brick Recipes

        for (int i = 0; i < 15; i++) {
            ItemStack brickStack = new ItemStack(fuzzcraft.brickBlock, 1, i);
            GameRegistry.addRecipe(new ItemStack(fuzzcraft.chisbrickBlock, 4, i), "xx",
                    "xx", 'x', brickStack); 
        }
        
        // Colored Stone
        
        ItemStack colorchargeStack = new ItemStack(fuzzcraft.colorCharge);
        ItemStack vstoneStack = new ItemStack(Block.stone);
        ItemStack wstoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 0);
        ItemStack ostoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 1);
        ItemStack mstoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 2);
        ItemStack lbstoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 3);
        ItemStack ystoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 4);
        ItemStack listoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 5);
        ItemStack pistoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 6);
        ItemStack dgstoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 7);
        ItemStack cstoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 8);
        ItemStack pstoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 9);
        ItemStack blstoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 10);
        ItemStack brstoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 11);
        ItemStack grstoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 12);
        ItemStack rdstoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 13);
        ItemStack blastoneStack = new ItemStack(fuzzcraft.stoneBlock, 1, 14);
        
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 0), "cb", 'c', colorchargeStack, 'b', vstoneStack); 
            
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 1), "s", 's', wstoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 2), "s", 's', ostoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 3), "s", 's', mstoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 4), "s", 's', lbstoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 5), "s", 's', ystoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 6), "s", 's', listoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 7), "s", 's', pistoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 8), "s", 's', dgstoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 9), "s", 's', cstoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 10), "s", 's', pstoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 11), "s", 's', blstoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 12), "s", 's', brstoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 13), "s", 's', grstoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 14), "s", 's', rdstoneStack);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.stoneBlock, 1, 0), "s", 's', blastoneStack);
        
        // Repulsion Block
        
        ItemStack pistonStack = new ItemStack(Block.pistonBase);
        ItemStack ironStack = new ItemStack(Item.ingotIron);
        GameRegistry.addRecipe(new ItemStack(fuzzcraft.repulsionBlock,1), "iii", "ipi", "iii", 
                'i', ironStack, 'p', pistonStack);
        
    }
  

}