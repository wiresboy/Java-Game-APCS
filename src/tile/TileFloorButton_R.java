package tile;

import tileentity.TileEntity;
import tileentity.TileEntityFloorButton_R;
import util.EnumSide;

public class TileFloorButton_R extends Tile {
	private boolean isFlipped = false;
	public TileFloorButton_R(){}
	public TileFloorButton_R(boolean b){
		isFlipped = b;
		if(isFlipped)
			id = "35";
	}
	private String id = "34";
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
		return new TileEntityFloorButton_R(isFlipped);
	}

}
