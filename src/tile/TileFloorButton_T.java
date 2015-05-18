package tile;

import tileentity.TileEntity;
import tileentity.TileEntityFloorButton_T;
import util.EnumSide;

public class TileFloorButton_T extends Tile {

	@Override
	public String getId(){ return "36"; }

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
		return new TileEntityFloorButton_T();
	}

}
