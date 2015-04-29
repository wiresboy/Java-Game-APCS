package tileentity;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import util.Data;
import map.Map;
import entity.IEntity;
/**
 * 
 * @author Lucas Rezac
 *
 */
public abstract class TileEntity implements IEntity{
	private static int id_count = 0;
	private int id;
	private String name;
	protected int x, y;
	protected Map map;
	@SuppressWarnings("rawtypes")
	public TileEntity(){
		Class thisClass = this.getClass();
		name = thisClass.getSimpleName();
		
		id = id_count++;
		System.out.println("Creating new TileEntity: "+name+" with id "+id);
	}
	public final void setMap(Map m){ this.map = m; }
	public String getName(){ return name;}
	public int getId(){ return id; }
	public BufferedImage getImageBasedOnState(){
		return null;
	}
	public BufferedImage getOverlayBasedOnState(){
		return null;
	}
	public void readFromData(Data data){
		id = data.getInt("id");
		// TODO figure out if this statement is needed
		//id_count--;
	}
	public void writeToData(Data data){
		data.setInt("id", id);
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
