package tile;

import util.EnumSide;

public class WhiteTileLight_LB extends WallLight {

	@Override
	public String getId(){ return "29"; }

	@Override
	public boolean isPortalable(EnumSide sides){ return true; }

}
