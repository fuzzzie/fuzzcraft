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
    private static boolean keepFurnaceInventory = false;
    private Random colorizorRandom = new Random();

    public Colorizor(int par1) {
        super(par1, Material.iron);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setResistance(20.0F);
        this.setHardness(1.5F);
        this.setUnlocalizedName("colorizorblock");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        sides = par1IconRegister.registerIcon("FuzzCraft:colorizor_side");
        bottom = par1IconRegister.registerIcon("FuzzCraft:colorizor_bottom");
        top = par1IconRegister.registerIcon("FuzzCraft:colorizor_top");
        front = par1IconRegister.registerIcon("FuzzCraft:colorizor_side");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int i, int j) {
        if (i == 0)
            return bottom;
        if (i == 1)
            return top;
        if (i == 3)
            return front;
        else
            return sides;
    }

    @Override
    public int idDropped(int par1, Random random, int par3) {
        return 1;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new FuzzCraft.TileEntity.colorizor_tileEntity();
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z) {
        if (!world.isRemote) {
            int l = world.getBlockId(x, y, z - 1);
            int i1 = world.getBlockId(x, y, z + 1);
            int j1 = world.getBlockId(x - 1, y, z);
            int k1 = world.getBlockId(x + 1, y, z);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1]) {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l]) {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1]) {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1]) {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer entityPlayer, int par6, float par7, float par8,
            float par9) {
        if (!world.isRemote)
            return true;
        else if (!entityPlayer.isSneaking()) {

            FuzzCraft.TileEntity.colorizor_tileEntity var10 = (FuzzCraft.TileEntity.colorizor_tileEntity) world
                    .getBlockTileEntity(x, y, z);
            if (var10 != null) {
                entityPlayer.openGui(fuzzcraft.instance, 0, world, x, y, z);
            }
            return true;
        } else
            return false;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z,
            EntityLiving par5EntityLiving) {
        int var6 = MathHelper
                .floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (var6 == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, var6);
        }

        if (var6 == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, var6);
        }

        if (var6 == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, var6);
        }

        if (var6 == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, var6);
        }
    }

    @Override
    public void breakBlock(World world, int par2, int par3, int par4, int par5,
            int par6) {
        if (!keepFurnaceInventory) {
            FuzzCraft.TileEntity.colorizor_tileEntity tileentitycolorizor = (colorizor_tileEntity) world
                    .getBlockTileEntity(par2, par3, par4);

            if (tileentitycolorizor != null) {
                for (int j1 = 0; j1 < tileentitycolorizor.getSizeInventory(); ++j1) {
                    ItemStack itemstack = tileentitycolorizor
                            .getStackInSlot(j1);

                    if (itemstack != null) {
                        float f = colorizorRandom.nextFloat() * 0.8F + 0.1F;
                        float f1 = colorizorRandom.nextFloat() * 0.8F + 0.1F;
                        float f2 = colorizorRandom.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0) {
                            int k1 = colorizorRandom.nextInt(21) + 10;

                            if (k1 > itemstack.stackSize) {
                                k1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= k1;
                            EntityItem entityitem = new EntityItem(world, par2
                                    + f, par3 + f1, par4 + f2, new ItemStack(
                                    itemstack.itemID, k1,
                                    itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound()) {
                                entityitem.getEntityItem().setTagCompound(
                                        (NBTTagCompound) itemstack
                                                .getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (float) colorizorRandom
                                    .nextGaussian() * f3;
                            entityitem.motionY = (float) colorizorRandom
                                    .nextGaussian() * f3 + 0.2F;
                            entityitem.motionZ = (float) colorizorRandom
                                    .nextGaussian() * f3;
                            world.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                world.func_96440_m(par2, par3, par4, par5);
            }
        }

        super.breakBlock(world, par2, par3, par4, par5, par6);
    }

}
