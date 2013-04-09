package FuzzCraft.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

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

@Override
public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x,
        int y, int z) {
    return null;
}

@Override
public int getRenderType () {
    return 6;
}

@Override
public boolean isOpaqueCube () {
    return false;
}

@Override
public void updateTick (World world, int x, int y, int z, Random random) {
    if (world.getBlockMetadata(x, y, z) == 1) {
        return;
    }

    if (random.nextInt(isFertile(world, x, y - 1, z) ? 12 : 25) != 0) {
        return;
    }

    world.setBlockMetadataWithNotify(x, y, z, 1, z);
}
}


