package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

public abstract class Entity implements IEntity{
	private static int id_count = -1;
	private int id;
	private int x, y;
	protected int lastX, lastY;
	protected Rectangle boundingBox;
	protected BufferedImage image;
	protected int speedX, speedY;
	public Entity(){
		id = id_count++;
	}
	public final int getX(){ return x; }
	public final int getY(){ return y; }
	public Shape boundingBox(){
		boundingBox.setLocation(x,y);
		return boundingBox;
	}
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
		val[0] =(x-lastX)/(x-lastX);
		val[1] = (y-lastY)/(y-lastY);
		return val;
	}
	public final void setDirection(int[] newDir){
		lastX = x-newDir[0];
		lastY = y-newDir[1];
	}
	public final void setLocation(int newx, int newy){
		setX(newx);setY(newy);
	}
}
