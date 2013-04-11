

package FuzzCraft.Blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class StoneBlockItem extends ItemBlock {
    
    public final static String[] subNames = {
        "white",
        "orange",
        "magenta",
        "lightBlue",
        "yellow",
        "lightGreen",
        "pink",
        "darkGrey",
        "cyan",
        "purple",
        "blue",
        "brown", 
        "green",
        "red",
        "black"
    };

    public StoneBlockItem(int id) {
        super(id);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setUnlocalizedName("stoneBlock");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    @Override
    public int getMetadata (int damageValue) {
        return damageValue;
    }
   
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return this.getUnlocalizedName() + subNames[itemstack.getItemDamage()];
    }
    
    /*@Override
    public String getItemDisplayName(ItemStack itemStack) {
        return this.getUnlocalizedName() + subName[itemStack.getItemDamage()];
    }*/

}