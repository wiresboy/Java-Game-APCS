package tile;
import java.awt.*;
import java.awt.image.BufferedImage;

import entity.Entity;
import map.Map;
import util.EnumSide;
import util.Resources;
import tileentity.TileEntity;
/**
 * 
 * @author Lucas Rezac
 *
 */
public abstract class Tile{
	protected BufferedImage image = null;
	protected BufferedImage overlay = null;
	public Tile(){
		try{
		image = Resources.getTile(this.getClass().getSimpleName());
		}catch(Exception e){}
	}
	/**
	 * @return a String representation of this tile's hexadecimal id number. See Lucas for the chart.
	 */
	public abstract String getId();
	public final void draw(Graphics2D g, int mapRow, int mapCol, Map map){
		int x = Map.tilesToPixels(mapCol);
		int y = Map.tilesToPixels(mapRow);
		BufferedImage img = image;
		if(hasTileEntity()){
			TileEntity te = map.getTileEntity(mapRow,mapCol);
			img = te.getImageBasedOnState();
		}
		if(img != null)
		g.drawImage(img,x,y,null);
	}
	public final Shape boundingBox(int x, int y, Map map){
		if(hasTileEntity()){
			TileEntity te = map.getTileEntity(
				Map.pixelsToTiles(y),
				Map.pixelsToTiles(x));
			return te.boundingBox();
		}
		return boundingBox(x,y);
	}
	public Shape boundingBox(int x, int y){
		return new Rectangle(x,y,Map.TILE_SIZE,Map.TILE_SIZE);
	}
	public abstract boolean hasTileEntity();
	public TileEntity createNewTileEntity(){ return null; }
	public abstract boolean hasOverlay();
	public void drawOverlay(Graphics2D g, int pixelX, int pixelY){ return; }
	//public static final int TOP = 0, RIGHT = 1, BOTTOM = 2, LEFT = 3;
	public abstract boolean isPortalable(EnumSide left);
	public void handleCollision(Entity e){
		System.out.println("                      Collision detected ! "+e.toString());
		
	}
}