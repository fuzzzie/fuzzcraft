package FuzzCraft.Handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUI_Handler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world,
            int x, int y, int z) {

        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

        switch (id) {

            case 0:
                return new FuzzCraft.Containers.Colorizor_Container(
                        player.inventory,
                        (FuzzCraft.TileEntity.colorizorTileEntity) tile_entity);
            }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world,
            int x, int y, int z) {

        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

        switch (id) {

            case 0:
                return new FuzzCraft.GUI.ColorizorGUI(player.inventory,
                        (FuzzCraft.TileEntity.colorizorTileEntity) tile_entity);
                
            case 1:
                return new FuzzCraft.GUI.EnhancedSpawnerGUI(player);
//           
        }

        return null;

    }

}