package FuzzCraft.Blocks;

import java.util.Random;
import FuzzCraft.Base.fuzzcraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;


public class ZombieESpawner extends Block {
    
    private final boolean powered;

    public ZombieESpawner(int id, boolean par2) {
        super(id, Material.iron);
        this.powered = par2;
        this.setCreativeTab(CreativeTabs.tabMisc);
        if (par2)
        {
            this.setLightValue(1.0F);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        if (this.powered)
        {
            this.blockIcon = par1IconRegister.registerIcon("FuzzCraft:spawnerA");
        }
        else
        {
            this.blockIcon = par1IconRegister.registerIcon("FuzzCraft:spawnerI");
        }
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            if (this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
            {
                world.scheduleBlockUpdate(x, y, z, this.blockID, 4);
            }
            else if (!this.powered && world.isBlockIndirectlyGettingPowered(x, y, z))
            {
                world.setBlock(x, y, z, fuzzcraft.zombieespawnerBlockA.blockID, 0, 2);
            }
        }
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, int nID)
    {
        if (!world.isRemote)
        {
            if (this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
            {
                world.scheduleBlockUpdate(x, y, z, this.blockID, 4);
            }
            else if (!this.powered && world.isBlockIndirectlyGettingPowered(x, y, z))
            {
                world.setBlock(x, y, z, fuzzcraft.zombieespawnerBlockA.blockID, 0, 2);
            }
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote && this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
        {
            world.setBlock(x, y, z, fuzzcraft.zombieespawnerBlockI.blockID, 0, 2);
        }
    }

    public int idDropped(int par1, Random rand, int par3)
    {
        return fuzzcraft.zombieespawnerBlockI.blockID;
    }

    @SideOnly(Side.CLIENT)
    public int idPicked(World world, int par2, int par3, int par4)
    {
        return fuzzcraft.zombieespawnerBlockI.blockID;
    }
}

