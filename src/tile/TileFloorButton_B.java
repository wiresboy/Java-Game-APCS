package tile;

import tileentity.TileEntity;
import tileentity.TileEntityFloorButton_B;

public class TileFloorButton_B extends Tile {

	@Override
	public String getId(){ return "30"; }

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
		return new TileEntityFloorButton_B();
	}

}