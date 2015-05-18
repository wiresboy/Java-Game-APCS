package tile;

import java.awt.Shape;

import util.EnumSide;
import util.Resources;

public class Door_L extends TileOverlay {

	@Override
	public String getId() {
		return "6E";
	}
	public Door_L(){
		offsetX = -2; 
		offsetY = -17;
	}
	@Override
	public boolean hasTileEntity() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isPortalable(EnumSide sides) {
		return false;
	}
	public Shape boundingBox(int x, int y){
		return null;
	}

}
