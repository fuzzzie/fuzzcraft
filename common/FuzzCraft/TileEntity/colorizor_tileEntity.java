package FuzzCraft.TileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import FuzzCraft.Base.fuzzcraft;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class colorizor_tileEntity extends TileEntity implements IInventory,
        ISidedInventory {
    private ItemStack[] furnaceItemStacks = new ItemStack[3];

    private String field_94130_e;
    private static final int[] field_102011_e = new int[] { 2, 1 };
    private static final int[] field_102010_d = new int[] { 0 };
    private static final int[] field_102009_f = new int[] { 1 };

    public int furnaceBurnTime = 0;

    public int currentItemBurnTime = 0;

    public int furnaceCookTime = 0;

    @Override
    public int getSizeInventory() {
        return furnaceItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int par1) {
        return furnaceItemStacks[par1];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (furnaceItemStacks[par1] != null) {
            ItemStack var3;

            if (furnaceItemStacks[par1].stackSize <= par2) {
                var3 = furnaceItemStacks[par1];
                furnaceItemStacks[par1] = null;
                return var3;
            } else {
                var3 = furnaceItemStacks[par1].splitStack(par2);

                if (furnaceItemStacks[par1].stackSize == 0) {
                    furnaceItemStacks[par1] = null;
                }

                return var3;
            }
        } else
            return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (furnaceItemStacks[par1] != null) {
            ItemStack var2 = furnaceItemStacks[par1];
            furnaceItemStacks[par1] = null;
            return var2;
        } else
            return null;
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        furnaceItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null
                && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName() {
        return "container.furnace";
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < furnaceItemStacks.length) {
                furnaceItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
        furnaceCookTime = par1NBTTagCompound.getShort("CookTime");
        currentItemBurnTime = getItemBurnTime(furnaceItemStacks[1]);
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short) furnaceBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short) furnaceCookTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < furnaceItemStacks.length; ++var3) {
            if (furnaceItemStacks[var3] != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) var3);
                furnaceItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int par1) {
        return furnaceCookTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1) {
        if (currentItemBurnTime == 0) {
            currentItemBurnTime = 200;
        }

        return furnaceBurnTime * par1 / currentItemBurnTime;
    }

    public boolean isBurning() {
        return furnaceBurnTime > 0;
    }

    @Override
    public void updateEntity() {
        boolean var2 = false;

        if (furnaceBurnTime > 0) {
            --furnaceBurnTime;
        }

        if (!worldObj.isRemote) {
            if (furnaceBurnTime == 0 && this.canSmelt()) {
                currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[1]);

                if (furnaceBurnTime > 0) {
                    var2 = true;

                    if (furnaceItemStacks[1] != null) {
                        --furnaceItemStacks[1].stackSize;

                        if (furnaceItemStacks[1].stackSize == 0) {
                            furnaceItemStacks[1] = furnaceItemStacks[1]
                                    .getItem().getContainerItemStack(
                                            furnaceItemStacks[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt()) {
                ++furnaceCookTime;

                if (furnaceCookTime == 200) {
                    furnaceCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            } else {
                furnaceCookTime = 0;
            }

        }

        if (var2) {
            this.onInventoryChanged();
        }
    }

    private boolean canSmelt() {
        if (furnaceItemStacks[0] == null)
            return false;
        else {
            ItemStack var1 = FuzzCraft.Handlers.Colorizor_Recipes.smelting()
                    .getSmeltingResult(furnaceItemStacks[0]);
            if (var1 == null)
                return false;
            if (furnaceItemStacks[2] == null)
                return true;
            if (!furnaceItemStacks[2].isItemEqual(var1))
                return false;
            int result = furnaceItemStacks[2].stackSize + var1.stackSize;
            return result <= getInventoryStackLimit()
                    && result <= var1.getMaxStackSize();
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack var1 = FuzzCraft.Handlers.Colorizor_Recipes.smelting()
                    .getSmeltingResult(furnaceItemStacks[0]);

            if (furnaceItemStacks[2] == null) {
                furnaceItemStacks[2] = var1.copy();
            } else if (furnaceItemStacks[2].isItemEqual(var1)) {
                furnaceItemStacks[2].stackSize += var1.stackSize;
            }

            --furnaceItemStacks[0].stackSize;

            if (furnaceItemStacks[0].stackSize <= 0) {
                furnaceItemStacks[0] = null;
            }
        }
    }

    public static int getItemBurnTime(ItemStack par0ItemStack) {
        if (par0ItemStack == null)
            return 0;
        else {
            int var1 = par0ItemStack.getItem().itemID;
            if (par0ItemStack.getItem() instanceof ItemBlock
                    && Block.blocksList[var1] != null) {
                Block var3 = Block.blocksList[var1];

                if (var3 == Block.woodSingleSlab)
                    return 150;

                if (var3.blockMaterial == Material.wood)
                    return 300;
            }

            if (var1 == fuzzcraft.colorCharge.itemID)
                return 1000;

            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }

    public static boolean isItemFuel(ItemStack par0ItemStack) {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    public boolean isUseableByPlayer1(EntityPlayer par1EntityPlayer) {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this ? false
                : par1EntityPlayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D,
                        zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

    /*
     * @Override public int getStartInventorySide(ForgeDirection side) { if
     * (side == ForgeDirection.DOWN) return 1; if (side == ForgeDirection.UP)
     * return 0; return 2; }
     */

    @Override
    public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
        return this.isStackValidForSlot(par1, par2ItemStack);
    }

    @Override
    public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
        return par3 != 0 || par1 != 1
                || par2ItemStack.itemID == Item.bucketEmpty.itemID;
    }

    @Override
    public boolean isInvNameLocalized() {
        return field_94130_e != null && field_94130_e.length() > 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this ? false
                : par1EntityPlayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D,
                        zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 == 2 ? false : par1 == 1 ? isItemFuel(par2ItemStack) : true;
    }

    @Override
    public int[] getSizeInventorySide(int par1) {
        return par1 == 0 ? field_102011_e : par1 == 1 ? field_102010_d
                : field_102009_f;
    }
}
