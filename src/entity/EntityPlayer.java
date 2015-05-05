package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import tile.Tile;
import util.Animation;
import util.ImageManipulator;
import util.Resources;
import main.GamePanel;
import map.Map;

public class EntityPlayer extends Entity{
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
	private IEntityPortal_Red redportal;
	private IEntityPortal_Blue blueportal;
	private boolean willShootBlue = true;
	private boolean inPortal = false;
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
		testforportals();
		if(inPortal)System.out.println("In portal!");
	}
	public void testforportals(){
		IEntityPortal[] portals = {(IEntityPortal) redportal,(IEntityPortal) blueportal};
		for(IEntityPortal portal : portals){
		if(portal != null){
			if(portal.isHorizontal()){
				if(getX() >= portal.getX() && getX() <= portal.getX()+16 && getY() >= portal.getY()-image.getHeight()-1 && getY() <= portal.getY()){
					inPortal = true;
				}else{
					if(inPortal){
						inPortal = false;
						System.out.println("Teleporting");
						teleportToOtherPortal(portal);
					}
				}
			}else{ //isVertical
				if(getX() >= portal.getX()-image.getWidth() && getX() <= portal.getX() && getY() >= portal.getY() && getY() <= portal.getY()+6){
					inPortal = true;
				}else if(inPortal){
					inPortal = false;
					System.out.println("Teleporting");
					teleportToOtherPortal(portal);
				}
			}
		}
		}
	}
	public void teleportToOtherPortal(IEntityPortal portal){
		IEntityPortal other = portal.getOtherPortal();
		int x = other.getX();
		int y = other.getY();
		if(other.isHorizontal()){
			setX(other.getX());
			setY(other.getY()+3);
		}else{
			setX(other.getX()+3);
			setY(other.getY());
		}
	}
	public boolean willShootBlue(){return willShootBlue;}
	public void mouseClicked(int x, int y){
		
		//TODO: make sure that the start location is from the head or another decent place, not from an arbitrary corner.
		x=x/2;
		y=y/2;
		if(willShootBlue){
			//if (blueportal!=null)
				//remove last blue portal?
			shotPortalBlue = new EntityShotPortal_Blue(x,y,getX(),getY(),this);
			shotPortalBlue.setMap(map);
			if(shotPortalBlue.go())
				willShootBlue = false;
		}else{
			shotPortalRed = new EntityShotPortal_Red(x,y,getX(),getY(),this);
			shotPortalRed.setMap(map);
			if(shotPortalRed.go())
				willShootBlue = true;
		}//*/
	}
	public void mouseRightClicked()//switch portal to other color; we may want it to be a button but I though mouse would work well.
	{
		willShootBlue = !willShootBlue;
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
				//System.out.println("Jump Countdown = "+jumpCountDown);
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
		if(!inPortal && (t != null && speedX == 0 && speedY == 0)){
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
	@Override
	public void draw(Graphics2D g){
		super.draw(g);
		//System.out.println(name()+" drawing!");
		if(redportal != null)
			redportal.draw(g);
		if(blueportal != null)
			blueportal.draw(g);
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
		if(!inPortal && ((t != null && t.boundingBox(0, 0) != null) || (t2 != null && t2.boundingBox(0, 0) != null)) ){
			
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
		if(!inPortal && ((t != null && t.boundingBox(0, 0) != null) || (t2 != null && t2.boundingBox(0, 0) != null)) ){
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
		if(!inPortal && ((t != null && t.boundingBox(0, 0) != null) || (t2 != null && t2.boundingBox(0, 0) != null))){
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
	public void setRedPortal(IEntityPortal_Red portal){
		System.out.println("Setting red portal");
		redportal =portal;
		redportal.setOtherPortal(blueportal);
		if(blueportal!=null)
		blueportal.setOtherPortal(redportal);
	}
	public void setBluePortal(IEntityPortal_Blue portal){
		System.out.println("Setting blue portal");
		blueportal = portal;
		blueportal.setOtherPortal(redportal);
		if(redportal != null)
		redportal.setOtherPortal(blueportal);
	}
	public IEntityPortal_Red getRedPortal(){ return redportal; }
	public IEntityPortal_Blue getBluePortal(){ return blueportal;}
}
