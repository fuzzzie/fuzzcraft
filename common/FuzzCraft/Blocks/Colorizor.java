package FuzzCraft.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import FuzzCraft.Base.fuzzcraft;
import FuzzCraft.TileEntity.colorizor_tileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Colorizor extends BlockContainer {

    private Icon sides, bottom, top, front;
    private final Random furnaceRand = new Random();
    private final boolean isActive;
    private static boolean keepFurnaceInventory = false;
    
    @SideOnly(Side.CLIENT)
    private Icon field_94458_cO;
    
    @SideOnly(Side.CLIENT)
    private Icon field_94459_cP;


    public Colorizor(int par1, boolean par2) {
        super(par1, Material.iron);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setResistance(20.0F);
        this.setHardness(1.5F);
        this.isActive = par2;
        this.setUnlocalizedName("colorizorblock");
    }

        /**
         * Returns the ID of the items to drop on destruction.
         */
  //      public int idDropped(int par1, Random rand, int par3)
    //    {
 //           return fuzzcraft.colorizorBlockI.blockID;
  //      }

        /**
         * Called whenever the block is added into the world. Args: world, x, y, z
         */
        public void onBlockAdded(World world, int x, int y, int z)
        {
            super.onBlockAdded(world, x, y, z);
            this.setDefaultDirection(world, x, y, z);
        }

        /**
         * set a blocks direction
         */
        private void setDefaultDirection(World world, int x, int y, int z)
        {
            if (!world.isRemote)
            {
                int l = world.getBlockId(x, y, z - 1);
                int i1 = world.getBlockId(x, y, z + 1);
                int j1 = world.getBlockId(x - 1, y, z);
                int k1 = world.getBlockId(x + 1, y, z);
                byte b0 = 3;

                if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
                {
                    b0 = 3;
                }

                if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
                {
                    b0 = 2;
                }

                if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
                {
                    b0 = 5;
                }

                if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
                {
                    b0 = 4;
                }

                world.setBlockMetadataWithNotify(x, y, z, b0, 2);
            }
        }

        @SideOnly(Side.CLIENT)

        /**
         * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
         */
        public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
        {
            return par1 == 1 ? this.field_94458_cO : (par1 == 0 ? this.field_94458_cO : (par1 != par2 ? this.blockIcon : this.field_94459_cP));
        }

        @SideOnly(Side.CLIENT)

        /**
         * When this method is called, your block should register all the icons it needs with the given IconRegister. This
         * is the only chance you get to register icons.
         */
        public void registerIcons(IconRegister par1IconRegister)
        {
            this.blockIcon = par1IconRegister.registerIcon("furnace_side");
            this.field_94459_cP = par1IconRegister.registerIcon(this.isActive ? "furnace_front_lit" : "furnace_front");
            this.field_94458_cO = par1IconRegister.registerIcon("furnace_top");
        }

        /**
         * Called upon block activation (right click on the block.)
         */
        @Override
        public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {
            TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

            if (tile_entity == null || player.isSneaking()) {

                return false;
            }

            player.openGui(fuzzcraft.instance, 0, world, x, y, z);

            return true;
        }

        /**
         * Update which block ID the furnace is using depending on whether or not it is burning
         */
        public static void updateFurnaceBlockState(boolean par0, World world, int x, int y, int z)
        {
            int l = world.getBlockMetadata(x, y, z);
            TileEntity tileentity = world.getBlockTileEntity(x, y, z);
            keepFurnaceInventory = true;

            if (par0)
            {
      //          world.setBlock(x, y, z, fuzzcraft.colorizorBlockA.blockID);
            }
            else
            {
      //          world.setBlock(x, y, z, fuzzcraft.colorizorBlockI.blockID);
            }

            keepFurnaceInventory = false;
            world.setBlockMetadataWithNotify(x, y, z, l, 2);

            if (tileentity != null)
            {
                tileentity.validate();
                world.setBlockTileEntity(x, y, z, tileentity);
            }
        }

        @SideOnly(Side.CLIENT)

        /**
         * A randomly called display update to be able to add particles or other items for display
         */
        public void randomDisplayTick(World world, int x, int y, int z, Random rand)
        {
            if (this.isActive)
            {
                int l = world.getBlockMetadata(x, y, z);
                float f = (float)x + 0.5F;
                float f1 = (float)y + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
                float f2 = (float)z + 0.5F;
                float f3 = 0.52F;
                float f4 = rand.nextFloat() * 0.6F - 0.3F;

                if (l == 4)
                {
                    world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                }
                else if (l == 5)
                {
                    world.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                }
                else if (l == 2)
                {
                    world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                }
                else if (l == 3)
                {
                    world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                }
            }
        }

        /**
         * Returns a new instance of a block's tile entity class. Called on placing the block.
         */
        public TileEntity createNewTileEntity(World world)
        {
            return new colorizor_tileEntity();
        }

        /**
         * Called when the block is placed in the world.
         */
        public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack stack)
        {
            int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

            if (l == 0)
            {
                world.setBlockMetadataWithNotify(x, y, z, 2, 2);
            }

            if (l == 1)
            {
                world.setBlockMetadataWithNotify(x, y, z, 5, 2);
            }

            if (l == 2)
            {
                world.setBlockMetadataWithNotify(x, y, z, 3, 2);
            }

            if (l == 3)
            {
                world.setBlockMetadataWithNotify(x, y, z, 4, 2);
            }

            if (stack.hasDisplayName())
            {
                ((colorizor_tileEntity)world.getBlockTileEntity(x, y, z)).func_94129_a(stack.getDisplayName());
            }
        }

        /**
         * ejects contained items into the world, and notifies neighbours of an update, as appropriate
         */
        public void breakBlock(World world, int x, int y, int z, int par5, int par6)
        {
            if (!keepFurnaceInventory)
            {
                colorizor_tileEntity tileentityfurnace = (colorizor_tileEntity)world.getBlockTileEntity(x, y, z);

                if (tileentityfurnace != null)
                {
                    for (int j1 = 0; j1 < tileentityfurnace.getSizeInventory(); ++j1)
                    {
                        ItemStack itemstack = tileentityfurnace.getStackInSlot(j1);

                        if (itemstack != null)
                        {
                            float f = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                            float f1 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                            float f2 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

                            while (itemstack.stackSize > 0)
                            {
                                int k1 = this.furnaceRand.nextInt(21) + 10;

                                if (k1 > itemstack.stackSize)
                                {
                                    k1 = itemstack.stackSize;
                                }

                                itemstack.stackSize -= k1;
                                EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

                                if (itemstack.hasTagCompound())
                                {
                                    entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                                }

                                float f3 = 0.05F;
                                entityitem.motionX = (double)((float)this.furnaceRand.nextGaussian() * f3);
                                entityitem.motionY = (double)((float)this.furnaceRand.nextGaussian() * f3 + 0.2F);
                                entityitem.motionZ = (double)((float)this.furnaceRand.nextGaussian() * f3);
                                world.spawnEntityInWorld(entityitem);
                            }
                        }
                    }

                    world.func_96440_m(x, y, z, par5);
                }
            }

            super.breakBlock(world, x, y, z, par5, par6);
        }

        /**
         * If this returns true, then comparators facing away from this block will use the value from
         * getComparatorInputOverride instead of the actual redstone signal strength.
         */
        public boolean hasComparatorInputOverride()
        {
            return false;
        }


    }
