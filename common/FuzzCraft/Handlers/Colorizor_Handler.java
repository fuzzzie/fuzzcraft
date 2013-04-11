package FuzzCraft.Handlers;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Colorizor_Handler implements IGuiHandler
{

@Override
public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
{

TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

switch(id)
{

case 0: return new FuzzCraft.Containers.Colorizor_Container(player.inventory, (FuzzCraft.TileEntity.colorizor_tileEntity) tile_entity);

}
return null;
}

@Override
public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
{

TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

switch(id)
{

case 0: return new FuzzCraft.GUI.colorizor_gui(player.inventory, (FuzzCraft.TileEntity.colorizor_tileEntity) tile_entity);

}

return null;

}

}