package tile;

import util.EnumSide;

public class WhiteTileLight_RB extends WallLight {

	@Override
	public String getId(){ return "2A"; }

	@Override
	public boolean isPortalable(EnumSide sides){ return true; }

}
