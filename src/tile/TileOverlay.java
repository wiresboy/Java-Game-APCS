package tile;

import util.Resources;

public abstract class TileOverlay extends Tile {
	protected int offsetX, offsetY;
	public TileOverlay(){
		offsetX = 0; offsetY = 0;
		String className = this.getClass().getSimpleName();
		overlay = Resources.getOverlay(className);
	}
	@Override
	public final boolean hasOverlay(){ return true; }
}
