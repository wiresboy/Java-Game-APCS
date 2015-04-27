package entity;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import tile.Tile;
import util.ImageManipulator;
import main.GamePanel;
import map.Map;

public class EntityPlayer extends Entity{
	public int speedX = 0, speedY = 0;
	private final int MAX_SPEEDX = 5, MAX_SPEEDY = 10;
	private int jumpCountDown = 0;
	private boolean isJumping = false;
	private int startx,starty;
	public EntityPlayer(int x, int y){
		setX(x);
		setY(y);
		startx = x;
		starty = y;
		image = ImageManipulator.loadImage("error.png");
		boundingBox = new Rectangle(x,y,image.getWidth(),image.getHeight());
	}
	public void processKey(KeyEvent key){
		//int keyCode = key.getKeyCode();
		//if(keyCode == KeyEvent.VK_W){
		//	jump();
		//}
	}
	public void update(){
		//System.out.println("Update called");
		processKeys();
		//checkForCollisions();
	}
	public void processKeys(){
		boolean[] keys = GamePanel.instance.keys;
		if(keys[KeyEvent.VK_D]){
			moveRight();
		}else if(keys[KeyEvent.VK_A]){
			moveLeft();
		}else{
			//System.out.print("Not moving");
			if(speedX < 0){
				//speedX+=1;
				moveRight();
			}
			else if(speedX > 0){
				//speedX -= 1;
				moveLeft();
			}
			
			//System.out.println(" speedX = "+speedX);
		}
		/*if(keys[KeyEvent.VK_S]){
			fall();
		}else*/ if(keys[KeyEvent.VK_W] && jumpCountDown == 0 && !isJumping){
			jumpCountDown = 10;
			isJumping = true;
			jump();
		}else{
			if(jumpCountDown != 0){
				jumpCountDown--;
				System.out.println("Jump Countdown = "+jumpCountDown);
				int newy = getY()+ ((speedY < 0)? speedY++ : speedY);
				setY(newy);
			}else{
				fall();
			}
			/*if(speedY < 0){
				fall();
			}else if(speedY > 0){
				jump();
			}*/
		}
		
		int x = Map.pixelsToTiles(getX());
		int y = Map.pixelsToTiles(getY());
		Tile t = map.getTile(y, x);
		if(t != null && speedX == 0 && speedY == 0){
			setY(getY()-1);
		}
		if(getX()<0)setX(0);
		if(getX()>(map.getPreferredSize().width/2)-16)setX((map.getPreferredSize().width/32)-16);
		if(getY()>(map.getPreferredSize().height/2))kill();
	}
	
	public int getSpeedX(){ return speedX; }
	
	public void kill(){
		System.out.println("player died!");
		setX(startx);
		setY(starty);
		map.reset();
	}
	
	public int getSpeedY(){ return speedY; }
	public void jump(){
		speedY = -6;
		System.out.println("Jumping");
		/**int newy = getY()+((speedY == 0)? speedY = -1 :(speedY > 0)? speedY-- : (Math.abs(speedY) < MAX_SPEEDY)? speedY-- : speedY);
		int x = Map.pixelsToTiles(getX());
		int y = Map.pixelsToTiles(newy);
		Tile t = map.getTile(y, x);
		if(t != null){
			setY(Map.tilesToPixels(y)+16);
			speedY = 0;
			/*for(int i = 0; i < speedY; i++){
				y = Map.pixelsToTiles(newy+i);
				t = map.getTile(y,x);
				if(t == null){
					setX(newy+i);
					return;
				}
			}*
			return;
		}else
			setY(newy);
		**/
	}
	public void moveLeft(){
		int newx = getX()+((speedX == 0)? speedX = -1 : (speedX > 0)? speedX-- : (Math.abs(speedX) < MAX_SPEEDX)? speedX-- : speedX);
		int x = Map.pixelsToTiles(newx);
		int y = Map.pixelsToTiles(getY());
		Tile t = map.getTile(y, x);
		if(t != null){
			setX(Map.tilesToPixels(x)+16);
			speedX = 0;
			/*for(int i = 0; i < speedX; i++){
				x = Map.pixelsToTiles(newx+i);
				t = map.getTile(y,x);
				if(t == null){
					setX(newx+i);
					return;
				}
			}*/
			return;
		}else
			setX(newx);
	}
	public void moveRight(){
		int newx = getX()+((speedX == 0)? speedX =1 : (speedX < 0)? speedX++ : (Math.abs(speedX) < MAX_SPEEDX)? speedX++ : speedX)+16;
		int x = Map.pixelsToTiles(newx);
		int y = Map.pixelsToTiles(getY());
		Tile t = map.getTile(y, x);
		if(t != null){
			setX(Map.tilesToPixels(x)-16);
			speedX = 0;
			/*for(int i = 0; i < speedX; i++){
				x = Map.pixelsToTiles(newx-i);
				t = map.getTile(y,x);
				if(t == null){
					setX(newx-i);
					return;
				}
			}*/
			return;
		}else
			setX(newx-16);
	}
	public void fall(){
		int newy = getY()+((speedY == 0)? speedY = 1 :(speedY < 0)? speedY+=1.5 : (Math.abs(speedY) < MAX_SPEEDY)? speedY+=1.5 : speedY)+16;
		int x = Map.pixelsToTiles(getX());
		int y = Map.pixelsToTiles(newy);
		Tile t = map.getTile(y, x);
		if(t != null){
			setY(Map.tilesToPixels(y)-16);
			speedY = 0;
			isJumping = false;
			/*for(int i = 0; i < speedY; i++){
				y = Map.pixelsToTiles(newy-i);
				t = map.getTile(y,x);
				if(t == null){
					setX(newy-i);
					return;
				}
			}*/
			return;
		}else
			setY(newy-16);
		
	}
}
