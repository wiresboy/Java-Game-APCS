package tile;

import util.EnumSide;

public class BlackTileLight_LB extends WallLight {
	@Override
	public String getId(){ return "27"; }

	@Override
	public boolean isPortalable(EnumSide sides){ return false; }

}
