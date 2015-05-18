package tile;

import java.awt.Shape;

import util.EnumSide;

public abstract class Decoration extends Tile {

	@Override
	public boolean hasTileEntity(){ return false; }

	@Override
	public boolean hasOverlay(){ return false; }

	@Override
	public boolean isPortalable(EnumSide sides){ return false; }
	
	@Override
	public Shape boundingBox(int x, int y){
		return null;
	}

}
