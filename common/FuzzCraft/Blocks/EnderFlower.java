package FuzzCraft.Blocks;


import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;


public class EnderFlower extends BlockFlower {
    
    private Icon efTexture;

    public EnderFlower(int par1) {
        super(par1, Material.plants);
        this.setUnlocalizedName("Ender Flower");
        this.setCreativeTab(CreativeTabs.tabDecorations);
        float f = 0.2F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
        this.setTickRandomly(true);        
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        efTexture = par1IconRegister.registerIcon("FuzzCraft:Ender_Flower");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int i, int j) {
        return efTexture;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x, int y, int z) {
        return null;
    }

    @Override
    public int getRenderType () {
        return 1;
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

    @Override
    public boolean isBlockReplaceable(World world, int x, int y, int z) {
        return true;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int idNeighbor) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, 0);
        }
    }
}
