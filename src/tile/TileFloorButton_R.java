package tile;

import tileentity.TileEntity;
import tileentity.TileEntityFloorButton_R;

public class TileFloorButton_R extends Tile {

	@Override
	public String getId(){ return "34"; }

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
		return new TileEntityFloorButton_R();
	}

}
