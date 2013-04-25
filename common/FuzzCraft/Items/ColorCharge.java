package FuzzCraft.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ColorCharge extends Item {

    public ColorCharge(int par1) {
        super(par1);
        this.setUnlocalizedName("charge_13");
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMaterials); 
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
       iconIndex = par1IconRegister.registerIcon("FuzzCraft:charge_13");
    }  
}
