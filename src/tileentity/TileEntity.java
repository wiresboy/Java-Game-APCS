package tileentity;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import map.Map;
import entity.IEntity;
/**
 * 
 * @author Lucas Rezac
 *
 */
public abstract class TileEntity implements IEntity{
	private static int id_count = -1;
	private int id;
	private String name;
	protected int x, y;
	@SuppressWarnings("rawtypes")
	public TileEntity(){
		Class thisClass = this.getClass();
		name = thisClass.getSimpleName();
		id = id_count++;
	}
	public String getName(){ return name;}
	public int getId(){ return id; }
	public BufferedImage getImageBasedOnState(){
		return null;
	}
	public BufferedImage getOverlayBasedOnState(){
		return null;
	}
	public void readFromString(String data){
		// data in format of : "ID:<other data separated by ':'>:0"
		int i = data.indexOf(':');
		this.id = Integer.parseInt(data.substring(0,i));
	}
	public String writeToString(){
		return id+":0";
	}
	public Shape boundingBox(){
		return new Rectangle(x,y,Map.TILE_SIZE,Map.TILE_SIZE);
	}
	public void setX(int x){ this.x = x; }
	public void setY(int y){ this.y = y; }
	public int getX(){ return x; }
	public int getY(){ return y; }
	public void setLocation(int newx, int newy){
		setX(newx);setY(newy);
	}
	abstract void update();
	  
}
