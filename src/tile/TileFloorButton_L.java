package tile;

import tileentity.TileEntity;
import tileentity.TileEntityFloorButton_L;

public class TileFloorButton_L extends Tile {
	@Override
	public String getId(){ return "32"; }

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
		return new TileEntityFloorButton_L();
	}

}
