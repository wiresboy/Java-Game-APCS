package tile;

import util.EnumSide;

public class BlackTile extends Tile{
	@Override
	public String getId(){ return "0E"; }
	
	@Override
	public boolean hasTileEntity(){ return false; }

	@Override
	public boolean hasOverlay(){ return false; }
	
	@Override
	public boolean isPortalable(EnumSide sides){ return false;}

}
