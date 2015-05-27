package tile;

import tileentity.TileEntity;
import tileentity.TileEntityFloorButton_B;
import util.EnumSide;

public class TileFloorButton_B extends Tile {
	private boolean isFlipped = false;
	public TileFloorButton_B(){}
	public TileFloorButton_B(boolean b){
		isFlipped = b;
		if(isFlipped)
			id = "31";
	}
	private String id = "30";
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
		return new TileEntityFloorButton_B(isFlipped);
	}

}
