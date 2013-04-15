package FuzzCraft.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import FuzzCraft.Base.fuzzcraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EnhancedSpawner extends Block {

    private final boolean powered;
    private final int spawnRate = 50;
    
    
    public EnhancedSpawner(int id, boolean par2, boolean par3) {
        super(id, Material.iron);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setTickRandomly(true);        
        powered = par2;
        if (par3){
            if (par2) {
                this.setLightValue(1.0F);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon) {
        if (powered) {
            blockIcon = icon.registerIcon("FuzzCraft:spawnerA");
        } else {
            blockIcon = icon.registerIcon("FuzzCraft:spawnerI");
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (!world.isRemote) {
            if (powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
                world.scheduleBlockUpdate(x, y, z, blockID, 4);
            } else if (!powered && world.isBlockIndirectlyGettingPowered(x, y, z)) {
                world.setBlock(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, 0, 2);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int nID) {
      if (!world.isRemote) {
            if (powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) { 
                
                world.scheduleBlockUpdate(x, y, z, blockID, 4);
                
            } else if (!powered && world.isBlockIndirectlyGettingPowered(x, y, z)) {
                
                world.setBlock(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, 0, 2);

                EntityZombie entityzombie = new EntityZombie(world);
                entityzombie.setLocationAndAngles(x + 1.5D, y, z + 1.5D, 0.0F, 0.0F);
                world.spawnEntityInWorld(entityzombie);
                entityzombie.spawnExplosionParticle();

                world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
            }
        }
    }
    
    @Override
    public int tickRate(World world)
    {
        return 1;
    }
   
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
    
        if (!world.isRemote && powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
            world.setBlock(x, y, z, fuzzcraft.enhancedspawnerBlockI.blockID, 0, 2);
        } 
        if (!world.isRemote && powered && world.isBlockIndirectlyGettingPowered(x, y, z)) {
            
            world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
            
            EntityZombie entityzombie = new EntityZombie(world);
            entityzombie.setLocationAndAngles(x + rand.nextInt(3), y, z + rand.nextInt(3), 0.0F, 0.0F);
            world.spawnEntityInWorld(entityzombie);
            entityzombie.spawnExplosionParticle();
        }
    }

    public void scheduleBlockUpdate(int par1, int par2, int par3, int par4, int par5) {
        
    }
    
    @Override
    public int idDropped(int par1, Random rand, int par3) {
        return fuzzcraft.enhancedspawnerBlockI.blockID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int idPicked(World world, int par2, int par3, int par4) {
        return fuzzcraft.enhancedspawnerBlockI.blockID;
    }


    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

            player.openGui(fuzzcraft.instance, 1, world, x, y, z);
        
        return true;
   
}
}