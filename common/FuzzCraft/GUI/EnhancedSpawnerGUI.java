package FuzzCraft.GUI;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;


import org.lwjgl.opengl.GL11;



public class EnhancedSpawnerGUI extends GuiScreen {
    
    public final int xSizeOfTexture = 176;
    public final int ySizeOfTexture = 88;
    

    
    public String[] mobs = new String[]{"Zombies", "Skeletons", "Creepers", "Endermen",
            "Spiders", "Cave Spiders", "Slimes"};
    public String[] spawn = new String[]{"Slow", "Medium", "Fast"};
    public static int mobIndex = 0;
    public static int spawnRate = 0;


    public EnhancedSpawnerGUI(EntityPlayer player) {

    }
    @Override
    public void drawScreen(int x, int y, float f) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/FuzzCraft/textures/gui/enhancedspawnergui.png");
       
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);  

        fontRenderer.drawString("Spawning : " + mobs[mobIndex], posX + 30, posY + 10, 4210752);
        fontRenderer.drawString("Speed : " + spawn[spawnRate], posX + 30, posY + 20, 4210752);
        
        super.drawScreen(x, y, f);
    }
    
    @SuppressWarnings("unchecked")
    public void initGui() {
        this.buttonList.clear();;

        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;

        this.buttonList.add(new GuiButton(0, posX + 40, posY + 40, 100, 20, "Switch Mob"));
        this.buttonList.add(new GuiSmallButton(1, posX + 40, posY + 63, 20, 20, "S"));
        this.buttonList.add(new GuiSmallButton(2, posX + 80, posY + 63, 20, 20, "M"));
        this.buttonList.add(new GuiSmallButton(3, posX + 120, posY + 63, 20, 20, "F"));
    }
    
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public void actionPerformed(GuiButton button) {
        switch(button.id)
        {
            case 0: if(mobIndex < mobs.length - 1)
            {
                mobIndex += 1;
               
                return;
            }else
            {
                mobIndex = 0;
            }
            case 1: 
                spawnRate = 0;
                return;
                
            case 2: 
                spawnRate = 1;
                return;
            
            case 3: 
                spawnRate = 2;
                return;
            
    //        default:
        }
     }
            
    public static int getMob() {
        
        return mobIndex;
    }

    public static int getRate() {
        return spawnRate;
    }
}
    
