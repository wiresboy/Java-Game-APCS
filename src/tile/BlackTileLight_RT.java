package tile;

import util.EnumSide;

public class BlackTileLight_RT extends WallLight {
	
	@Override
	public String getId(){ return "24"; }

	@Override
	public boolean isPortalable(EnumSide sides){ return false; }
}
