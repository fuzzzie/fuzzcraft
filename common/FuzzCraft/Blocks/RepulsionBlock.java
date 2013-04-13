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
    
    public float rP;
    
    private Icon repText;
   

    public RepulsionBlock(int id, int repulsorPower) {
        super(id, Material.rock);
        this.setHardness(1.5F);
        this.setResistance(30.0F);
        this.setUnlocalizedName("repulsionBlock");
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.rP = (float)repulsorPower / 10;    
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
        
        if (rP == 0) {
            rP = 1;
        }
        else if  (rP > 3) {
            rP = 5;
        }
        
        entity.motionY+= rP;
           
    }

}    