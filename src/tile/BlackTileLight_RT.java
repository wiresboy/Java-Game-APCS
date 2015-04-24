package tile;

public class BlackTileLight_RT extends WallLight {
	
	@Override
	public String getId(){ return "24"; }

	@Override
	public boolean isPortalable(int sides){ return false; }
}
