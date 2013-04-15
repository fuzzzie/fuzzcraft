package FuzzCraft.Containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Colorizor_Container extends Container {
    private FuzzCraft.TileEntity.colorizor_tileEntity furnace;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public Colorizor_Container(InventoryPlayer par1InventoryPlayer,
            FuzzCraft.TileEntity.colorizor_tileEntity par2TileEntityFurnace) {
        furnace = par2TileEntityFurnace;
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 0, 56, 17));
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(par1InventoryPlayer.player,
                par2TileEntityFurnace, 2, 116, 35));
        int var3;

        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var4
                        + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3,
                    8 + var3 * 18, 142));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, furnace.furnaceCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, furnace.furnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2,
                furnace.currentItemBurnTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < crafters.size(); ++var1) {
            ICrafting var2 = (ICrafting) crafters.get(var1);

            if (lastCookTime != furnace.furnaceCookTime) {
                var2.sendProgressBarUpdate(this, 0, furnace.furnaceCookTime);
            }

            if (lastBurnTime != furnace.furnaceBurnTime) {
                var2.sendProgressBarUpdate(this, 1, furnace.furnaceBurnTime);
            }

            if (lastItemBurnTime != furnace.currentItemBurnTime) {
                var2.sendProgressBarUpdate(this, 2, furnace.currentItemBurnTime);
            }
        }

        lastCookTime = furnace.furnaceCookTime;
        lastBurnTime = furnace.furnaceBurnTime;
        lastItemBurnTime = furnace.currentItemBurnTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            furnace.furnaceCookTime = par2;
        }

        if (par1 == 1) {
            furnace.furnaceBurnTime = par2;
        }

        if (par1 == 2) {
            furnace.currentItemBurnTime = par2;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return furnace.isUseableByPlayer(par1EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot) inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 == 2) {
                if (!this.mergeItemStack(var5, 3, 39, true))
                    return null;

                var4.onSlotChange(var5, var3);
            } else if (par2 != 1 && par2 != 0) {
                if (FuzzCraft.Handlers.Colorizor_Recipes.smelting()
                        .getSmeltingResult(var5) != null) {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                        return null;
                } else if (FuzzCraft.TileEntity.colorizor_tileEntity
                        .isItemFuel(var5)) {
                    if (!this.mergeItemStack(var5, 1, 2, false))
                        return null;
                } else if (par2 >= 3 && par2 < 30) {
                    if (!this.mergeItemStack(var5, 30, 39, false))
                        return null;
                } else if (par2 >= 30 && par2 < 39
                        && !this.mergeItemStack(var5, 3, 30, false))
                    return null;
            } else if (!this.mergeItemStack(var5, 3, 39, false))
                return null;

            if (var5.stackSize == 0) {
                var4.putStack((ItemStack) null);
            } else {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
                return null;

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }
}