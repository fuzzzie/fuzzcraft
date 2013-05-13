package FuzzCraft.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;

public class BrickStairsBlock extends BlockStairs {
    
    public final static String[] brickStairNames = { "White Brick Stairs",
        "Orange Brick Stairs", "Magenta Brick Stairs",
        "Light Blue Brick Stairs", "Yellow Brick Stairs", "Lime Brick Stairs",
        "Pink Brick Stairs", "Dark Grey Brick Stairs", "Cyan Brick Stairs",
        "Purple Brick Stairs", "Blue Brick Stairs", "Brown Brick Stairs",
        "Green Brick Stairs", "Red Brick Stairs", "Black Brick Stairs" };

    public BrickStairsBlock(int id, Block blockModel, int blockMeta) {
        super(id, blockModel, blockMeta);
        this.setCreativeTab(CreativeTabs.tabDecorations);   
        this.setResistance(30.0F);
        this.setHardness(1.5F);
        this.setUnlocalizedName("brickstairsblock");
    }

}
