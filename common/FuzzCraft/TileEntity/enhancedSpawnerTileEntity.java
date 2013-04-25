/* package FuzzCraft.TileEntity;

import FuzzCraft.Base.fuzzcraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class enhancedSpawnerTileEntity extends TileEntity { 
    
    public static int spawnRate;
    public static int mobIndex;
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        
        super.readFromNBT(nbt);
        spawnRate = nbt.getInteger("spawnrate");
        mobIndex = nbt.getInteger("mobindex");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        
        super.writeToNBT(nbt);
        nbt.setInteger("spawnrate", this.getSpawnRate());
        nbt.setInteger("mobindex", this.getMobIndex());
    }
    
    public void spawnerData(World world) {
        
        world.notifyBlockChange(xCoord, yCoord, zCoord, fuzzcraft.enhancedspawnerBlockA.blockID);
    }
    
    
    public int getMobIndex() {
        
        int mob = FuzzCraft.GUI.EnhancedSpawnerGUI.mobIndex;
        mobIndex = mob;
        
        return mobIndex;
    }

    public int getSpawnRate() {
        int spawn = FuzzCraft.GUI.EnhancedSpawnerGUI.spawnRate;
        if (spawn == 0) {
            spawnRate = 200;
        } else if (spawn == 1){
            spawnRate = 100;
        } else if (spawn == 2){
            spawnRate = 50;
        }
        
        return spawnRate;
    }
    
    @Override
    public void updateEntity() {
    }
    
}
*/