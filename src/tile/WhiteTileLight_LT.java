package tile;

import util.EnumSide;

public class WhiteTileLight_LT extends WallLight {

	@Override
	public String getId(){ return "25"; }

	@Override
	public boolean isPortalable(EnumSide sides){ return true; }

}
