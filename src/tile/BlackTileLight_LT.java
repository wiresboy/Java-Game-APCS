package tile;

import util.EnumSide;

public class BlackTileLight_LT extends WallLight {

	@Override
	public String getId(){ return "23"; }

	@Override
	public boolean isPortalable(EnumSide sides){ return false; }

}
