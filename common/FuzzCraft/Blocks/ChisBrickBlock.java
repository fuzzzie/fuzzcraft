package FuzzCraft.Blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ChisBrickBlock extends Block {

    protected Icon[] icon = new Icon[15];

    public final static String[] chisbrickBlockNames = {
            "White Chiseled Brick", "Orange Chiseled Brick",
            "Magenta Chiseled Brick", "Light Blue Chiseled Brick",
            "Yellow Chiseled Brick", "Lime Chiseled Brick",
            "Pink Chiseled Brick", "Dark Grey Chiseled Brick",
            "Cyan Chiseled Brick", "Purple Chiseled Brick",
            "Blue Chiseled Brick", "Brown Chiseled Brick",
            "Green Chiseled Brick", "Red Chiseled Brick",
            "Black Chiseled Brick" };

    public ChisBrickBlock(int par1) {
        super(par1, Material.rock);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setResistance(30.0F);
        this.setHardness(1.5F);
        this.setUnlocalizedName("chisbrickblock");
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        for (int j = 0; j < 15; j++) {
            icon[j] = par1IconRegister.registerIcon("FuzzCraft:chisbrick_" + j);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
        return icon[meta];
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
        for (int ix = 0; ix < 15; ix++) {
            subItems.add(new ItemStack(this, 1, ix));
        }
    }

    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }
}
