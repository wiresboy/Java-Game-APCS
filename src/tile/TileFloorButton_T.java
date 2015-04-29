package tile;

import tileentity.TileEntity;
import tileentity.TileEntityFloorButton_T;

public class TileFloorButton_T extends Tile {

	@Override
	public String getId(){ return "36"; }

	@Override
	public boolean hasTileEntity(){ return true; }

	@Override
	public boolean hasOverlay() { return false; }

	@Override
	public boolean isPortalable(int sides) {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(){
		return new TileEntityFloorButton_T();
	}

}
