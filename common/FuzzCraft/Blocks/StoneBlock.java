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

public class StoneBlock extends Block {

    protected Icon[] icon = new Icon[15];

    public final static String[] stoneBlockNames = { "White Stone",
            "Orange Stone", "Magenta Stone", "Light Blue Stone",
            "Yellow Stone", "Lime Stone", "Pink Stone", "Dark Grey Stone",
            "Cyan Stone", "Purple Stone", "Blue Stone", "Brown Stone",
            "Green Stone", "Red Stone", "Black Stone" };

    public StoneBlock(int par1) {
        super(par1, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setResistance(30.0F);
        this.setHardness(1.5F);
        this.setUnlocalizedName("stoneblock");
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        for (int j = 0; j < 15; j++) {
            icon[j] = par1IconRegister.registerIcon("FuzzCraft:stone_" + j);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
        return icon[meta];
    }

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
