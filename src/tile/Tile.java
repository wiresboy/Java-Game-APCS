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
//TODO: Comment what these methods mean! I have no idea what they do!
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
		/*int[] dir = e.getDirection();
		int[] dir2 = new int[2];
		System.arraycopy(dir,0,dir2,0,dir.length);
		e.setX(e.getX()-dir[0]);
		e.setY(e.getY()-dir[1]);
		e.setDirection(dir2);*/
		int x = e.getX();
		int y = e.getY();
		
		
	}
}
/*import java.awt.image.*;
import main.Sprite;

public class Tile extends Sprite{
	private boolean isSolid;
	public Tile(int x, int y, BufferedImage image) {
		super(x, y, image);
		isSolid = true;
	}
	public void setIsCollidable(boolean b){
		isSolid = b;
	}
	public boolean isCollidable(){
		return isSolid;
	}
	public void update(){}
	//only animated tiles can be powered
	public void power(){}
	public String toString(){
		String s = super.toString();
		return "Tile"+s.substring(6);
	}
}*/
