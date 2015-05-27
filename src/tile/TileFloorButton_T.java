package tile;

import tileentity.TileEntity;
import tileentity.TileEntityFloorButton_T;
import util.EnumSide;

public class TileFloorButton_T extends Tile {
	private boolean isFlipped = false;
	public TileFloorButton_T(){}
	public TileFloorButton_T(boolean b){
		isFlipped = b;
		if(isFlipped)
			id = "37";
	}
	private String id = "36";
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
		return new TileEntityFloorButton_T(isFlipped);
	}

}
