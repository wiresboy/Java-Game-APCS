package tile;

public class WhiteTileLight_RT extends WallLight {

	@Override
	public String getId(){ return "26"; }

	@Override
	public boolean isPortalable(int sides){ return true; }

}
