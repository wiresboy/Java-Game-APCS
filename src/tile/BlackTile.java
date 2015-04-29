package tile;

public class BlackTile extends Tile{
	@Override
	public String getId(){ return "0E"; }
	
	@Override
	public boolean hasTileEntity(){ return false; }

	@Override
	public boolean hasOverlay(){ return false; }
	
	@Override
	public boolean isPortalable(int sides){ return false;}

}
