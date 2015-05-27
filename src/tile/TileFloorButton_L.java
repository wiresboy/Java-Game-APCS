package tile;

import tileentity.TileEntity;
import tileentity.TileEntityFloorButton_L;
import util.EnumSide;

public class TileFloorButton_L extends Tile {
	private boolean isFlipped = false;
	public TileFloorButton_L(){}
	public TileFloorButton_L(boolean b){
		isFlipped = b;
		if(isFlipped)
			id = "33";
	}
	private String id = "32";
	@Override
	public String getId(){ return id; }

	@Override
	public boolean hasTileEntity(){ return true; }

	@Override
	public boolean hasOverlay() { return false; }

	@Override
	public boolean isPortalable(EnumSide sides) {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(){
		return new TileEntityFloorButton_L(isFlipped);
	}

}
