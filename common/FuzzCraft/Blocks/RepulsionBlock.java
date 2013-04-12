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

public class RepulsionBlock extends Block{
    
    private Icon repText;

    public RepulsionBlock(int id) {
        super(id, Material.rock);
        this.setHardness(1.5F);
        this.setResistance(30.0F);
        this.setUnlocalizedName("repulsionBlock");
        this.setCreativeTab(CreativeTabs.tabBlock);
   }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon) {
        repText = icon.registerIcon("FuzzCraft:repblock");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int i, int j) {
        return repText;
    }
    
    @Override
    public boolean isOpaqueCube () {
        return true;
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return blockID;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        entity.motionY+=2;
    }
}
