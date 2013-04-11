

package FuzzCraft.Blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import FuzzCraft.Handlers.*;

public class BrickBlockItem extends ItemBlock {
    
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

    public BrickBlockItem(int id) {
        super(id);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setUnlocalizedName("brickBlock");
        this.setCreativeTab(CreativeTabs.tabDecorations);
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

}