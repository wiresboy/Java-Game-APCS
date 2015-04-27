package tileentity;

import java.util.ArrayList;

public class TileEntityRegistry {
	private static ArrayList<TileEntity> entities = new ArrayList<TileEntity>();
	public static void registerTileEntity(TileEntity t){
		System.out.print("Registering tile "+t.getClass().getSimpleName());
		entities.add(t.getId(), t);
		System.out.println("   Register successful.");
	}
	public static TileEntity getTileEntity(int entityId){
		for(TileEntity t : entities){
			if(t.getId() == entityId)
				return t;
		}
		return null;
	}
	public static TileEntity getTileEntity(String entityName){
		for(TileEntity t : entities){
			if(t.getName().equals(entityName))
				return t;
		}
		return null;
	}
}
