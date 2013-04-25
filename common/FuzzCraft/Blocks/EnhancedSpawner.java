/* package FuzzCraft.Blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import FuzzCraft.Base.fuzzcraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EnhancedSpawner extends BlockContainer {

    private final boolean powered;
    private int spawnRate;
    private int mobIndex;
       
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
    
    public TileEntity createNewTileEntity(World world)
    {
        try
        {
            return new FuzzCraft.TileEntity.enhancedSpawnerTileEntity();
        }
        catch (Exception var3)
        {
            throw new RuntimeException(var3);
        }
        
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int nID) {
      if (!world.isRemote) {
           
            if (powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) { 
                
                world.scheduleBlockUpdate(x, y, z, blockID, 4);
                
            } else if (!powered && world.isBlockIndirectlyGettingPowered(x, y, z)) {
                
                world.setBlock(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, 0, 2);
                
              mobIndex = FuzzCraft.GUI.EnhancedSpawnerGUI.mobIndex;
                int spawn = FuzzCraft.GUI.EnhancedSpawnerGUI.spawnRate;

                if (spawn == 0) {
                    spawnRate = 200;
                } else if (spawn == 1){
                    spawnRate = 100;
                } else if (spawn == 2){
                    spawnRate = 50;
                } 
                
                spawnRate = FuzzCraft.TileEntity.EnahncedSpawner_tileEntitiy.getSpawnRate();
                mobIndex = FuzzCraft.TileEntity.EnahncedSpawner_tileEntitiy.getMobIndex(); 
                
                FuzzCraft.TileEntity.enhancedSpawnerTileEntity t = (FuzzCraft.TileEntity.enhancedSpawnerTileEntity) world.getBlockTileEntity(x, y, z);
                t.spawnerData(world);
                
                
                if (mobIndex == 0) {
                    
                    EntityZombie entityzombie = new EntityZombie(world);
                    entityzombie.setLocationAndAngles(x + 1.5D, y, z + 1.5D, 0.0F, 0.0F);
                    world.spawnEntityInWorld(entityzombie);
                    entityzombie.spawnExplosionParticle();

                      world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
                }
                else if (mobIndex == 1) {
                
                    EntitySkeleton entityskeleton = new EntitySkeleton(world);
                    entityskeleton.setLocationAndAngles(x + 1.5D,  y,  z + 1.5D, 0.0F, 0.0F);
                    world.spawnEntityInWorld(entityskeleton);
                    entityskeleton.spawnExplosionParticle();
                      
                    world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
                }
                else if (mobIndex == 2) {
                    
                    EntityCreeper entitycreeper = new EntityCreeper(world);
                    entitycreeper.setLocationAndAngles(x + 1.5D, y, z + 1.5D,  0.0F,  0.0F);
                    world.spawnEntityInWorld(entitycreeper);
                    entitycreeper.spawnExplosionParticle();
                    
                    world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
                }
               else if (mobIndex == 3) {
                   
                   EntityEnderman entityenderman = new EntityEnderman(world);
                   entityenderman.setLocationAndAngles(x + 1.5D, y, z + 1.5D,  0.0F,  0.0F);
                   world.spawnEntityInWorld(entityenderman);
                   entityenderman.spawnExplosionParticle();
                   
                   world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
                   
               }
               else if (mobIndex == 4) {
                   
                   EntitySpider entityspider = new EntitySpider(world);
                   entityspider.setLocationAndAngles(x + 1.5D, y, z + 1.5D,  0.0F,  0.0F);
                   world.spawnEntityInWorld(entityspider);
                   entityspider.spawnExplosionParticle();
                   
                   world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
               }
               else if (mobIndex == 5) {
                   
                   EntityCaveSpider entitycavespider = new EntityCaveSpider(world);
                   entitycavespider.setLocationAndAngles(x + 1.5D, y, z + 1.5D,  0.0F,  0.0F);
                   world.spawnEntityInWorld(entitycavespider);
                   entitycavespider.spawnExplosionParticle();
                   
                   world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
               }
               else if (mobIndex == 6) {
                   
                   EntitySlime entityslime = new EntitySlime(world);
                   entityslime.setLocationAndAngles(x + 1.5D, y, z + 1.5D,  0.0F,  0.0F);
                   world.spawnEntityInWorld(entityslime);
                   entityslime.spawnExplosionParticle();
                   
                   world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
               }
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
    
            mobIndex = FuzzCraft.GUI.EnhancedSpawnerGUI.mobIndex;
            int spawn = FuzzCraft.GUI.EnhancedSpawnerGUI.spawnRate;

            if (spawn == 0) {
                spawnRate = 200;
            } else if (spawn == 1){
                spawnRate = 100;
            } else if (spawn == 2){
                spawnRate = 50;
            } 
            
            spawnRate = FuzzCraft.TileEntity.EnahncedSpawner_tileEntitiy.getSpawnRate();
            mobIndex = FuzzCraft.TileEntity.EnahncedSpawner_tileEntitiy.getMobIndex();
            
            FuzzCraft.TileEntity.enhancedSpawnerTileEntity t = (FuzzCraft.TileEntity.enhancedSpawnerTileEntity) world.getBlockTileEntity(x, y, z);
            t.spawnerData(world);
            
            
            if (mobIndex == 0) {
            
                    EntityZombie entityzombie = new EntityZombie(world);
                    entityzombie.setLocationAndAngles(x + 1.5D, y, z + 1.5D, 0.0F, 0.0F);
                    world.spawnEntityInWorld(entityzombie);
                    entityzombie.spawnExplosionParticle();

                    world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
            }
            else if (mobIndex == 1) {
                
                    EntitySkeleton entityskeleton = new EntitySkeleton(world);
                    entityskeleton.setLocationAndAngles(x + 1.5D,  y,  z + 1.5D, 0.0F, 0.0F);
                    world.spawnEntityInWorld(entityskeleton);
                    entityskeleton.spawnExplosionParticle();
                    
                    world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
            }
            else if (mobIndex == 2) {
                
                EntityCreeper entitycreeper = new EntityCreeper(world);
                entitycreeper.setLocationAndAngles(x + 1.5D, y, z + 1.5D,  0.0F,  0.0F);
                world.spawnEntityInWorld(entitycreeper);
                entitycreeper.spawnExplosionParticle();
                
                world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
            }
            else if (mobIndex == 3) {
                
                EntityEnderman entityenderman = new EntityEnderman(world);
                entityenderman.setLocationAndAngles(x + 1.5D, y, z + 1.5D,  0.0F,  0.0F);
                world.spawnEntityInWorld(entityenderman);
                entityenderman.spawnExplosionParticle();
                
                world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
            }
            else if (mobIndex == 4) {
                
                EntitySpider entityspider = new EntitySpider(world);
                entityspider.setLocationAndAngles(x + 1.5D, y, z + 1.5D,  0.0F,  0.0F);
                world.spawnEntityInWorld(entityspider);
                entityspider.spawnExplosionParticle();
                
                world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
            }
            else if (mobIndex == 5) {
                
                EntityCaveSpider entitycavespider = new EntityCaveSpider(world);
                entitycavespider.setLocationAndAngles(x + 1.5D, y, z + 1.5D,  0.0F,  0.0F);
                world.spawnEntityInWorld(entitycavespider);
                entitycavespider.spawnExplosionParticle();
                
                world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
            }
            else if (mobIndex == 6) {
                
                EntitySlime entityslime = new EntitySlime(world);
                entityslime.setLocationAndAngles(x + 1.5D, y, z + 1.5D,  0.0F,  0.0F);
                world.spawnEntityInWorld(entityslime);
                entityslime.spawnExplosionParticle();
                
                world.scheduleBlockUpdate(x, y, z, fuzzcraft.enhancedspawnerBlockA.blockID, spawnRate);
            }
        }
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
        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
        
        if (tile_entity == null || player.isSneaking()) {

            return false;
        }
        
        FuzzCraft.TileEntity.enhancedSpawnerTileEntity t = (FuzzCraft.TileEntity.enhancedSpawnerTileEntity) world.getBlockTileEntity(x, y, z);
        t.spawnerData(world);
        
        player.openGui(fuzzcraft.instance, 1, world, x, y, z);
        
        
        return true;
   
    }
    
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
}
 */