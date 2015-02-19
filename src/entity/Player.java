package entity;

import main.GamePanel;
import main.Sprite;
import web.Shareable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.io.IOException;
import java.util.ArrayList;

import util.*;

public class Player extends Sprite implements Shareable{
	public int x, y;
	private Map map;
	private static ArrayList<BufferedImage> images;
	private BufferedImage currImage;
	private int screenX;
	private final int down = 0, left = 1, up = 2, right = 3;
	//private Direction direction;
	//manages physics in the y direction
	private int gravity = 0;
	//manages physics in the x direction
	private int velocity = 0;
	//TODO: Shouldn't we have velocity in X and Y, with gravity as a constant?
	private boolean isHost;
	public Player(int x,int y){
		super(x,y,null);
		this.x = x;
		this.y = y;
		screenX=x;
		currImage = ImageManipulator.loadImage("error.png");
	}
	public void setMap(Map map){
		this.map = map;
	}
	public void draw(Graphics2D g){
		g.drawImage(currImage,x,y,null);
	}
	public void setImage(BufferedImage image){
		currImage = image;
	}
	public void update(){
		if(screenX > GamePanel.PWIDTH-64){
			//GamePanel.offset = x-GamePanel.PWIDTH+64;
			GamePanel.offset +=2;
			screenX-=2;
		}else if(screenX < 64){
			GamePanel.offset -=2;
			screenX+=2;
		}
			
	}
	public int getScreenX(){
		int xi;
		for(xi = x; xi > GamePanel.PWIDTH; xi -= GamePanel.PWIDTH);
		return xi;
	}
	public void processKey(KeyEvent key_){
		int key = key_.getKeyCode();
		if(key == KeyEvent.VK_A)
			moveLeft();
		else if (key == KeyEvent.VK_D)
			moveRight();
		else if (key == KeyEvent.VK_W)
			jump();
		else if (key == KeyEvent.VK_S)
			crouch();
	}
	private void moveLeft(){
		x-=2;
		screenX-=2;
	}
	private void moveRight(){
		x+=2;
		screenX+=2;
	}
	private void jump(){
		y-=2;
	}
	private void crouch(){
		y+=2;
	}
	
	
	
	
	/** gather any data to share with other player and package it, returning a 2D String array, containing key,value pairs.
	  * All values will be CSV formatted by Web.java
	  {{"Key1","Value1"},
	   {"Key2","Value2"},
	   {"Key3","Value3"}}		*/	
	@Override
	public String[][] packData() {
		String[] xRow = new String[] {"x",((Integer)x).toString()};
		String[] yRow = new String[] {"y",((Integer)y).toString()};
		//TODO: Add other vars as needed, like character image, etc.
		
		return new String[][]{xRow,yRow};
	}
	
	/** update all internal variables to new values based on data packed by a different client.
	 * Should follow exact same pattern for decoding as it does for encoding.
	 */
	@Override
	public void unpackData(String[][] update) {
		for(String[] row : update)//loop through each item to use with this frame update
		{
			switch (row[0]){
				case "x":
					x = Integer.decode(row[1]);
					break;
				case "y":
					y = Integer.decode(row[1]);
					break;
			}
		}
	}
	
	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}
}
