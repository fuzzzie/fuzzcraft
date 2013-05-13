package FuzzCraft.Blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class SpikeBlock extends Block {

    private Icon spikeText;
    
    public SpikeBlock(int id) {
        super(id, Material.iron);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setResistance(30.0F);
        this.setHardness(1.5F);
        this.setUnlocalizedName("spikeBlock");
        this.setBlockBounds(1, 1, 1, 1, 1, 1);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon) {
        spikeText = icon.registerIcon("FuzzCraft:repblock");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int i, int j) {
        return spikeText;
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x,
            int y, int z) {
        return null;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z,
            Entity entity) {
        
        entity.setInWeb();
        entity.setDead();

    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return blockID;
    }

}
