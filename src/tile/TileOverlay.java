package tile;

import java.awt.Graphics2D;

import util.Resources;

public abstract class TileOverlay extends Tile {
	protected int offsetX, offsetY;
	public TileOverlay(){
		offsetX = 0; offsetY = 0;
		String className = this.getClass().getSimpleName();
		try{
		overlay = Resources.getOverlay(className);
		}catch(Exception e){}
	}
	@Override
	public final boolean hasOverlay(){ return true; }
	public void drawOverlay(Graphics2D g, int x, int y){
		g.drawImage(overlay,x+offsetX,y+offsetY,null);
	}
}
