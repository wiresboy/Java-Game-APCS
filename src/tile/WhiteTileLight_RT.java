package tile;

import util.EnumSide;

public class WhiteTileLight_RT extends WallLight {

	@Override
	public String getId(){ return "26"; }

	@Override
	public boolean isPortalable(EnumSide sides){ return true; }

}
