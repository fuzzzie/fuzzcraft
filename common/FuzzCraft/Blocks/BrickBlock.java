package FuzzCraft.Blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BrickBlock extends Block {
    
    protected Icon[] icon = new Icon[15];
    
    public final static String[] brickBlockNames = {
        "White Stone Brick",
        "Orange Stone Brick",
        "Magenta Stone Brick",
        "Light Blue Stone Brick",
        "Yellow Stone Brick",
        "Lime Stone Brick",
        "Pink Stone Brick",
        "Dark Grey Stone Brick",
        "Cyan Stone Brick",
        "Purple Stone Brick",
        "Blue Stone Brick",
        "Brown Stone Brick", 
        "Green Stone Brick",
        "Red Stone Brick",
        "Black Stone Brick"
    };
    
    
    public BrickBlock(int par1) {
        super(par1, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setResistance(30.0F);
        this.setHardness(1.5F);
        this.setUnlocalizedName("brickblock");
    }
    
    public void registerIcons(IconRegister par1IconRegister) {
        for(int j = 0; j < 15; j++)
            {
                icon[j] = par1IconRegister.registerIcon("FuzzCraft:brick_" + j);
            }
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
        return icon[meta];
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
        for (int ix = 0; ix < 15; ix++) {
            subItems.add(new ItemStack(this, 1, ix));
        }
    }
    
    @Override
    public int damageDropped (int metadata) {
        return metadata;
    }
}