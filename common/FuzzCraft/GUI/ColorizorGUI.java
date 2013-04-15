package FuzzCraft.GUI;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class ColorizorGUI extends GuiContainer {

    private FuzzCraft.TileEntity.colorizor_tileEntity furnaceInventory;

    public ColorizorGUI(InventoryPlayer par1InventoryPlayer,
            FuzzCraft.TileEntity.colorizor_tileEntity par2TileEntityFurnace) {
        super(new FuzzCraft.Containers.Colorizor_Container(par1InventoryPlayer,
                par2TileEntityFurnace));
        furnaceInventory = par2TileEntityFurnace;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.furnace"), 60, 6,
                4210752);
        fontRenderer.drawString(
                StatCollector.translateToLocal("container.inventory"), 8,
                ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
            int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        // this.mc.renderEngine.bindTexture("/gui/furnace.png");
        mc.renderEngine
                .bindTexture("/mods/FuzzCraft/textures/gui/colorizorgui.png");
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
        int var7;

        if (furnaceInventory.isBurning()) {
            var7 = furnaceInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(var5 + 56, var6 + 36 + 12 - var7, 176,
                    12 - var7, 14, var7 + 2);
        }

        var7 = furnaceInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);
    }

}