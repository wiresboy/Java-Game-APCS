package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import tile.Tile;

import map.Map;

public abstract class Entity implements IEntity{
	private static int id_count = -1;
	public static ArrayList<Entity> list = new ArrayList<Entity>();
	private int id;
	protected Map map;
	private int x, y;
	protected int lastX, lastY;
	protected Rectangle boundingBox;
	protected BufferedImage image;
	protected int speedX, speedY;
	public Entity(){
		id = id_count++;
		list.add(this);
	}
	public final void setMap(Map m){ this.map = m; }
	public final int getX(){ return x; }
	public final int getY(){ return y; }
	public Shape boundingBox(){
		boundingBox.setLocation(x,y);
		return boundingBox;
	}
	public abstract void update();
	public final BufferedImage getImage(){ return image; }
	public final void setImage(BufferedImage b){ image = b; }
	public int getId(){ return id; }
	public void draw(Graphics2D g){
		g.drawImage(image,x,y,null);
	}
	public final void setX(int newx){
		lastX = x;
		x = newx;
	}
	public final void setY(int newy){
		lastY = y;
		y = newy;
	}
	public final int[] getDirection(){
		int[] val = new int[2];
		val[0] =(x-lastX);
		val[1] = (y-lastY);
		if(val[0] != 0){
			val[0] = Math.abs(val[0])/val[0];
		}
		if(val[1] != 0){
			val[1] = Math.abs(val[1])/val[1];
		}
		return val;
	}
	public final void setDirection(int[] newDir){
		lastX = x-newDir[0];
		lastY = y-newDir[1];
	}
	public final void setLocation(int newx, int newy){
		setX(newx);setY(newy);
	}
	/**
	 * Finds the first block along the 
	 * @param startX
	 * @param startY
	 * @param angleRadians
	 * @return double array, [blockX, blockY, faceOfBlock], where face of block is 0 for top, 1 for right, 2 for bottom, and 3 for left.
	 * returns null if block isn't found.
	 */
	public final int[] findNextIntersection(int startX, int startY, double angleRadians)
	{
		double x = startX;
		double y = startY;
		Tile t=null;
		
		double xChange = Math.sin(angleRadians);
		double yChange = Math.cos(angleRadians);

		while (t==null && map.onMapPixel((int)x, (int)y))
		{
			t = map.getTile(Map.pixelsToTiles((int)x), Map.pixelsToTiles((int)y));
			
			x+= xChange;
			y+= yChange;
		}
		
		if (t==null)//if we jumped the loop by going off of the screen, then return null to indicate it.
			return null;
		int[] found = new int[3];
		found[0] = Map.pixelsToTiles((int)x);
		found[1] = Map.pixelsToTiles((int)y);
		
		int top = Map.tilesToPixels(found[0]);//returns top y coordinate of box for use with calculating first impact point.
		int left = Map.tilesToPixels(found[0]);//returns left x coordinate of box for use with calculating first impact point.
		int bottom = top+Map.TILE_SIZE;
		int right = left+Map.TILE_SIZE;		
		
		found[2] = Tile.LEFT;//TODO: Fix this so that it actually calculates properly.
		
		
		return found;
	}
}
