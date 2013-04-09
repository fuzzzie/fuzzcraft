package FuzzCraft.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class EnderFlower extends Block {
    
    private Icon sides, bottom, top;

    
    public EnderFlower(int par1) {
        super(par1, Material.plants);
        this.setUnlocalizedName("Ender Flower");
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.5F, 1.0F);
        this.setTickRandomly(true);
    }

@Override
public void registerIcons(IconRegister par1IconRegister) {
    sides = par1IconRegister.registerIcon("FuzzCraft:Ender_Flower");
    bottom = par1IconRegister.registerIcon("FuzzCraft:Ender_Flower");
    top = par1IconRegister.registerIcon("FuzzCraft:Ender_Flower");
}

@Override
public Icon getBlockTextureFromSideAndMetadata(int i, int j) {
    if (i == 0)
        return bottom;
    if (i == 1)
        return top;
    else
        return sides;
}


}
