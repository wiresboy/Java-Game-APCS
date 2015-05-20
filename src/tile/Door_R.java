package tile;

import java.awt.Shape;

import util.EnumSide;

public class Door_R extends TileOverlay {

	@Override
	public String getId() {
		return "6F";
	}
	public Door_R(){
		offsetX = 0;
		offsetY = -17;
	}

	@Override
	public boolean hasTileEntity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPortalable(EnumSide sides) {
		// TODO Auto-generated method stub
		return false;
	}
	public Shape boundingBox(int x, int y){
		return null;
	}

}
