package tile;

import util.EnumSide;

public class BlackTileLight_RB extends WallLight {

	@Override
	public String getId(){ return "28"; }

	@Override
	public boolean isPortalable(EnumSide sides){ return false; }

}
