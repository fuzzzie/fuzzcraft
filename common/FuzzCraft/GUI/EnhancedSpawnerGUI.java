package FuzzCraft.GUI;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

public class EnhancedSpawnerGUI extends GuiScreen {
    
    
    public final int xSizeOfTexture = 176;
    public final int ySizeOfTexture = 88;
    
    
    public String[] mobs = new String[]{"Spawning Zombies", "Spawning Skeletons", "Spawning Creepers", "Spawning Endermen",
            "Spawning Spiders", "Spawning Cave Spiders", "Spawning Wither Skeletons"};
    public int mobIndex = 0;

    
    public EnhancedSpawnerGUI(EntityPlayer player){


    }
    @Override
    public void drawScreen(int x, int y, float f) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/FuzzCraft/textures/gui/enhancedspawnergui.png");
       

        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);  
        drawString(fontRenderer, mobs[mobIndex], posX+20, posY+20, 0x000000);
        
 //   drawDefaultBackground();

    super.drawScreen(x, y, f);
    }
    
    public void initGui() {
        this.buttonList.clear();;

        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        this.buttonList.add(new GuiButton(0, posX+ 40, posY + 40, 100, 20, "Switch Mob"));
    }
    
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public void actionPerformed(GuiButton button) {
        switch(button.id)
        {
            case 0: if(mobIndex < mobs.length-1)
            {
                mobIndex += 1;
            }else
            {
                mobIndex = 0;
            }
            break;
            default:
        }
    }
    
}