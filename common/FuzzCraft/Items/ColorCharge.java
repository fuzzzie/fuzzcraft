package FuzzCraft.Items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ColorCharge extends Item {

    protected Icon icon ;

   

    public ColorCharge(int par1) {
        super(par1);
        this.setUnlocalizedName("charge_13");
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMaterials);
  
        
    }
    
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icon = par1IconRegister.registerIcon("FuzzCraft:charge_13");
    }

    
}
