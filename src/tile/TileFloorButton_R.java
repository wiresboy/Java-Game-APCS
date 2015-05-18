package tile;

import tileentity.TileEntity;
import tileentity.TileEntityFloorButton_R;
import util.EnumSide;

public class TileFloorButton_R extends Tile {

	@Override
	public String getId(){ return "34"; }

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
		return new TileEntityFloorButton_R();
	}

}
