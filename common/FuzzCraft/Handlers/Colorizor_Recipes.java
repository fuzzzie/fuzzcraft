package FuzzCraft.Handlers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class Colorizor_Recipes {
    private static final Colorizor_Recipes smeltingBase = new Colorizor_Recipes();

    private Map<Integer, ItemStack> smeltingList = new HashMap<Integer, ItemStack>();
    private Map<Integer, Float> experienceList = new HashMap<Integer, Float>();
    private HashMap<List<Integer>, ItemStack> metaSmeltingList = new HashMap<List<Integer>, ItemStack>();
    private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

    public static final Colorizor_Recipes smelting() {
        return smeltingBase;
    }

    private Colorizor_Recipes() {

        this.addSmelting(Block.sand.blockID, new ItemStack(Block.glass), 0.1F);
        this.addSmelting(Block.cobblestone.blockID, new ItemStack(Block.stone),
                0.1F);

    }

    public void addSmelting(int par1, ItemStack par2ItemStack, float par3) {
        smeltingList.put(Integer.valueOf(par1), par2ItemStack);
        experienceList.put(Integer.valueOf(par2ItemStack.itemID),
                Float.valueOf(par3));
    }

    @Deprecated
    public ItemStack getSmeltingResult(int par1) {
        return smeltingList.get(Integer.valueOf(par1));
    }

    public Map<Integer, ItemStack> getSmeltingList() {
        return smeltingList;
    }

    @Deprecated
    // In favor of ItemStack sensitive version
    public float getExperience(int par1) {
        return experienceList.containsKey(Integer.valueOf(par1)) ? experienceList
                .get(Integer.valueOf(par1)).floatValue() : 0.0F;
    }

    public void addSmelting(int itemID, int metadata, ItemStack itemstack,
            float experience) {
        metaSmeltingList.put(Arrays.asList(itemID, metadata), itemstack);
        metaExperience.put(Arrays.asList(itemID, metadata), experience);
    }

    public ItemStack getSmeltingResult(ItemStack item) {
        if (item == null)
            return null;
        ItemStack ret = metaSmeltingList.get(Arrays.asList(item.itemID,
                item.getItemDamage()));
        if (ret != null)
            return ret;
        return smeltingList.get(Integer.valueOf(item.itemID));
    }

    public float getExperience(ItemStack item) {
        if (item == null || item.getItem() == null)
            return 0;
        float ret = item.getItem().getSmeltingExperience(item);
        if (ret < 0
                && metaExperience.containsKey(Arrays.asList(item.itemID,
                        item.getItemDamage()))) {
            ret = metaExperience.get(Arrays.asList(item.itemID,
                    item.getItemDamage()));
        }
        if (ret < 0 && experienceList.containsKey(item.itemID)) {
            ret = experienceList.get(item.itemID).floatValue();
        }
        return ret < 0 ? 0 : ret;
    }
}