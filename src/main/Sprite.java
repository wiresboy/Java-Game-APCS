package main;

import java.awt.*;
import java.awt.image.*;

import util.ImageManipulator;

public class Sprite {
	private BufferedImage image;
	private int id;
	private static int id_count = -1;
	private int x, y;
	public Sprite(int x, int y, BufferedImage image){
		this.image = ImageManipulator.scale(image,16);
		this.x = x;
		this.y = y;
		this.id = id_count++;
	}
	public void draw(Graphics2D g){
		if(image != null)
			g.drawImage(image,x,y,null);
	}
	public void setImage(BufferedImage image){
		this.image = image;
	}
	public void setX(int newX){
		this.x = newX;
	}
	public void setY(int newY){
		this.y = newY;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public BufferedImage getImage(){
		return image;
	}
	public String toString(){
		return "Sprite#"+id+"[x:"+x+", y:"+y+", img:"+image.toString()+"]";
	}
}
