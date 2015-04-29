package tile;

import java.awt.Shape;

public abstract class Decoration extends Tile {

	@Override
	public boolean hasTileEntity(){ return false; }

	@Override
	public boolean hasOverlay(){ return false; }

	@Override
	public boolean isPortalable(int sides){ return false; }
	
	@Override
	public Shape boundingBox(int x, int y){
		return null;
	}

}
