package FuzzCraft.Handlers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


    public class FuzzCraftTab extends CreativeTabs {
        public FuzzCraftTab(int par1, String par2Str) {
            super(par1, par2Str);
        }

        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
       
            return Item.netherStar;
        }
    }  
            

        

        
        
  

