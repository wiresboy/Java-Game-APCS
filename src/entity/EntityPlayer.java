package entity;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import tile.Tile;
import util.Animation;
import util.ImageManipulator;
import util.Resources;
import web.Shareable;
import main.GamePanel;
import map.Map;

public class EntityPlayer extends Entity implements Shareable{
	public int speedX = 0, speedY = 0;
	private final int MAX_SPEEDX = 3, MAX_SPEEDY = 10;
	private int jumpCountDown = 0;
	private boolean isJumping = false;
	private int startx,starty;
	private String name;
	private BufferedImage stillLeft, stillRight;
	private Animation leftAnim, rightAnim, leftJumpAnim, rightJumpAnim;
	private EntityShotPortal_Blue shotPortalBlue;
	private EntityShotPortal_Red shotPortalRed;
	private EntityPortal_Red redportal;
	private EntityPortal_Blue blueportal;
	private boolean willShootBlue = false;
	public EntityPlayer(int x, int y,String name){
		setX(x);
		setY(y);
		startx = x;
		starty = y;
		this.name = name;
		//image = ImageManipulator.loadImage("error.png");
		//rightImage = Resources.getPlayer("Jeff\\walk_1");
		//leftImage = ImageManipulator.horizontalFlip(rightImage);
		String[] rightImgNames = {"walk_1","walk_2","walk_3","walk_4","walk_5","walk_6"};
		BufferedImage[] rightImages = new BufferedImage[rightImgNames.length];
		BufferedImage[] leftImages = new BufferedImage[rightImgNames.length];
		for(int i = 0; i < rightImgNames.length; i++){
			BufferedImage img = Resources.getPlayer(name+"\\"+rightImgNames[i]);
			rightImages[i] = img;
			leftImages[i] = ImageManipulator.horizontalFlip(img);
		}
		leftAnim = new Animation(leftImages,0);
		rightAnim = new Animation(rightImages,0);
		
		String[] jumpNames = {"jump_1","jump_2","jump_3","jump_4","jump_5","jump_6","jump_7","jump_8","jump_9","jump_10","jump_11","jump_12"};
		rightImages = new BufferedImage[jumpNames.length];
		leftImages = new BufferedImage[jumpNames.length];
		for(int i = 0; i < jumpNames.length; i++){
			BufferedImage img = Resources.getPlayer(name+"\\"+jumpNames[i]);
			rightImages[i] = img;
			leftImages[i] = ImageManipulator.horizontalFlip(img);
		}
		leftJumpAnim = new Animation(leftImages,0);
		rightJumpAnim = new Animation(rightImages,0);
		
		stillRight = Resources.getPlayer(name+"\\still");
		stillLeft = ImageManipulator.horizontalFlip(stillRight);
		
		setImage(rightAnim.next());
		boundingBox = new Rectangle(x,y,image.getWidth(),image.getHeight());
	}
	public void update(){
		processKeys();
	}
	public void mouseClicked(int x, int y){
		// TODO Make this work; right now there is an infinite loop somewhere
		// I want to make the player shoot a portal in the direction of the mouse click,
		// and, if the destination wall tiles can hold a portal, then a new portal
		// should be created there.
		/*
		int dirx = x-getX();
		int diry = y-getY();
		if(willShootBlue){
			shotPortalBlue = new EntityShotPortal_Blue(dirx,diry,this);
			shotPortalBlue.setMap(map);
			shotPortalBlue.go();
		}else{
			shotPortalRed = new EntityShotPortal_Red(dirx,diry,this);
			shotPortalRed.setMap(map);
			shotPortalRed.go();
		}*/
	}
	public void processKeys(){
		boolean[] keys = GamePanel.instance.keys;
		if(keys[KeyEvent.VK_D]){
			setImage((isJumping)? rightJumpAnim.next() : rightAnim.next());
			if(moveRight()){
				setImage((isJumping)? rightJumpAnim.back() : rightAnim.back());
			}
		}else if(keys[KeyEvent.VK_A]){
			setImage((isJumping)? leftJumpAnim.next() : leftAnim.next());
			if(moveLeft()){
				setImage((isJumping)? leftJumpAnim.back() : leftAnim.back());
			}
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
		if(keys[KeyEvent.VK_W] && jumpCountDown == 0 && !isJumping){
			jumpCountDown = 10;
			isJumping = true;
			jump();
		}else{
			if(jumpCountDown != 0){
				jumpCountDown--;
				System.out.println("Jump Countdown = "+jumpCountDown);
				int newy = getY()+ ((speedY < 0)? speedY++ : speedY);
				int x = Map.pixelsToTiles(getX());
				int y = Map.pixelsToTiles(newy);
				Tile t= map.getTile(y, x);
				if((t != null && t.boundingBox(0, 0) != null)){
					speedY = 0;
					setY(Map.tilesToPixels(y)+16);
					jumpCountDown = 0;
					isJumping = false;
					leftJumpAnim.reset();
					rightJumpAnim.reset();
				}else
					setY(newy);
			}else{
				fall();
				leftJumpAnim.reset();
				rightJumpAnim.reset();
			}
			/*if(speedY < 0){
				fall();
			}else if(speedY > 0){
				jump();
			}*/
		}
		
		if(!keys[KeyEvent.VK_W]&& !keys[KeyEvent.VK_A]&& !keys[KeyEvent.VK_D] && !keys[KeyEvent.VK_S]  ){
			int[] dir = getDirection();
			if(dir[0] == -1){
				setImage(stillLeft);
			}else{
				setImage(stillRight);
			}
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
	public boolean moveLeft(){
		int newx = getX()+((speedX == 0)? speedX = -1 : (speedX > 0)? speedX-- : (Math.abs(speedX) < MAX_SPEEDX)? speedX-- : speedX);
		int x = Map.pixelsToTiles(newx);
		int y = Map.pixelsToTiles(getY());
		int y2 = Map.pixelsToTiles(getY()+16);
		int x2 = Map.pixelsToTiles(newx+16);
		Tile t = map.getTile(y, x);
		Tile t2 = map.getTile(y2,x);
		if((t != null && t.boundingBox(0, 0) != null) || (t2 != null && t2.boundingBox(0, 0) != null) ){
			
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
			return true;
		}else{
			setX(newx);
			return false;
		}
	}
	public boolean moveRight(){
		int newx = getX()+((speedX == 0)? speedX =1 : (speedX < 0)? speedX++ : (Math.abs(speedX) < MAX_SPEEDX)? speedX++ : speedX)+16;
		int x = Map.pixelsToTiles(newx);
		int y = Map.pixelsToTiles(getY());
		int y2 = Map.pixelsToTiles(getY()+16);
		Tile t = map.getTile(y, x);
		Tile t2 = map.getTile(y2, x);
		if((t != null && t.boundingBox(0, 0) != null) || (t2 != null && t2.boundingBox(0, 0) != null) ){
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
			return true;
		}else{
			setX(newx-16);
			return false;
		}
	}
	public void fall(){
		int newy = getY()+((speedY == 0)? speedY = 1 :(speedY < 0)? speedY+=1.5 : (Math.abs(speedY) < MAX_SPEEDY)? speedY+=1.5 : speedY)+32;
		int x = Map.pixelsToTiles(getX());
		int y = Map.pixelsToTiles(newy);
		int x2 = Map.pixelsToTiles(getX()+16);
		Tile t = map.getTile(y, x);
		Tile t2 =map.getTile(y, x2);
		if((t != null && t.boundingBox(0, 0) != null) || (t2 != null && t2.boundingBox(0, 0) != null) ){
			setY(Map.tilesToPixels(y)-32);
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
			setY(newy-32);
		
	}
	public void setRedPortal(EntityPortal_Red portal){
		System.out.println("Setting red portal");
		redportal =portal;
	}
	public void setBluePortal(EntityPortal_Blue portal){
		System.out.println("Setting blue portal");
		blueportal = portal;
	}
	public EntityPortal_Red getRedPortal(){ return redportal; }
	public EntityPortal_Blue getBluePortal(){ return blueportal;}
	
	
	/** gather any data to share with other player and package it, returning a 2D String array, containing key,value pairs.
	  * All values will be CSV formatted by Web.java
	  {{"Key1","Value1"},
	   {"Key2","Value2"},
	   {"Key3","Value3"}}		*/	
	@Override
	public String[][] packData() {
		String[] xRow = new String[] {"x",((Integer)getX()).toString()};
		String[] yRow = new String[] {"y",((Integer)getY()).toString()};
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
					setX(Integer.decode(row[1]));
					break;
				case "y":
					setY(Integer.decode(row[1]));
					break;
			}
		}
	}
	
	@Override
	public int getIdentifier() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
