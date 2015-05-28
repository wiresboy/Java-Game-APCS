package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import tile.IDecorationGrate;
import tile.Tile;
import util.Animation;
import util.EnumSide;
import util.ImageManipulator;
import util.Resources;
import util.Texture;
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
	private Texture stillLeft, stillRight;
	private Animation leftAnim, rightAnim, leftJumpAnim, rightJumpAnim;
	private EntityShotPortal_Blue shotPortalBlue;
	private EntityShotPortal_Red shotPortalRed;
	protected IEntityPortal_Red redportal;
	protected IEntityPortal_Blue blueportal;
	private boolean willShootBlue = true;
	private String username;
	public EntityPlayer(int x, int y,String name,String username_){
		this(x,y,name);
		username = username_;
	}
	private boolean inPortalVert = false;
	private boolean inPortalHoriz = false;
	private int tempY = 0;
	public EntityPlayer(int x, int y,String name){
		setX(x);
		setY(y);
		startx = x;
		starty = y;
		this.name = name;
		boolean isMel = name.equals("Mel");
		//image = ImageManipulator.loadImage("error.png");
		//rightImage = Resources.getPlayer("Jeff\\walk_1");
		//leftImage = ImageManipulator.horizontalFlip(rightImage);
		String[] rightImgNames = {"walk_1","walk_2","walk_3","walk_4","walk_5","walk_6"};
		BufferedImage[] rightImages = new BufferedImage[rightImgNames.length];
		BufferedImage[] leftImages = new BufferedImage[rightImgNames.length];
		for(int i = 0; i < rightImgNames.length; i++){
			Texture img = Resources.getPlayer(((isMel)? "Chell" : name)+"\\"+rightImgNames[i]);
			if(isMel)img.replaceColors(new Color(255,147,0), new Color(91,170,255)).replaceColors(new Color(0,0,0),new Color(255,226,81)).replaceColors(new Color(255,223,215),new Color(255,206,193));
			rightImages[i] = img;
			leftImages[i] = ImageManipulator.horizontalFlip(img);
		}
		leftAnim = new Animation(leftImages,0);
		rightAnim = new Animation(rightImages,0);
		
		String[] jumpNames = {"jump_1","jump_2","jump_3","jump_4","jump_5","jump_6","jump_7","jump_8","jump_9","jump_10","jump_11","jump_12"};
		rightImages = new BufferedImage[jumpNames.length];
		leftImages = new BufferedImage[jumpNames.length];
		for(int i = 0; i < jumpNames.length; i++){
			Texture img = Resources.getPlayer(((isMel)? "Chell" : name)+"\\"+jumpNames[i]);
			if(isMel)img.replaceColors(new Color(255,147,0), new Color(91,170,255)).replaceColors(new Color(0,0,0),new Color(255,226,81)).replaceColors(new Color(255,223,215),new Color(255,206,193));
			rightImages[i] = img;
			leftImages[i] = ImageManipulator.horizontalFlip(img);
		}
		leftJumpAnim = new Animation(leftImages,0);
		rightJumpAnim = new Animation(rightImages,0);
		
		stillRight = Resources.getPlayer(((isMel)? "Chell" : name)+"\\still");
		if(isMel)stillRight.replaceColors(new Color(255,147,0), new Color(91,170,255)).replaceColors(new Color(0,0,0),new Color(255,226,81)).replaceColors(new Color(255,223,215),new Color(255,206,193));
		stillLeft = new Texture( ImageManipulator.horizontalFlip(stillRight));
		
		setImage(rightAnim.next());
		boundingBox = new Rectangle(x,y,image.getWidth(),image.getHeight());
	}
	public void update(){
		processKeys();
		testforportals();
		if(inPortalVert)System.out.println("In portal vert!");
		if(inPortalHoriz)System.out.println("In portal horiz!");
		if(redportal != null)redportal.update();
		if(blueportal != null)blueportal.update();
	}
	public void testforportals(){
<<<<<<< HEAD
		IEntityPortal_Red greenportal = null;
		IEntityPortal_Blue purpleportal = null;
=======
>>>>>>> origin/master

		
		if (otherplayer != null)
		{
			greenportal = otherplayer.getFirstPortal();
			purpleportal = otherplayer.getSecondPortal();
<<<<<<< HEAD
=======

>>>>>>> origin/master
		}
		IEntityPortal[] portals = {(IEntityPortal) redportal,(IEntityPortal) blueportal, greenportal, purpleportal};
		for(IEntityPortal portal : portals){
		if(portal != null){
			if(portal.isHorizontal() && portal.getDir() == EnumSide.TOP){
				if(getX() >= portal.getX() && getX() <= portal.getX()+16 && getY() >= portal.getY()-image.getHeight()-1 && getY() <= portal.getY()+16){
					/*inPortalHoriz = true;
				}else{
					if(inPortalHoriz){
						inPortalHoriz = false;
						System.out.println("Teleporting");
						teleportToOtherPortal(portal);
					}*/
					teleportToOtherPortal(portal);
				
				}
			}else if(!portal.isHorizontal() && portal.getDir() == EnumSide.LEFT){ //isVertical
				if(getX() >= portal.getX()-image.getWidth() && getX() <= portal.getX()+16 && getY() >= portal.getY()-2 && getY() <= portal.getY()+6){
					/*inPortalVert = true;
					System.out.println("in left portal");
					jumpCountDown = 0;
					isJumping = false;
					speedY = 0;
					tempY = getY();
				}else if(inPortalVert){
					inPortalVert = false;
					System.out.println("Teleporting");*/
					teleportToOtherPortal(portal);
				}
			}else if(!portal.isHorizontal() && portal.getDir() == EnumSide.RIGHT){
				if(getX() >= portal.getX()-16 && getX() <= portal.getX()+image.getWidth()-16 && getY() >= portal.getY() && getY() <= portal.getY()+6){
					/*inPortalVert = true;
					System.out.println("in right portal");
					jumpCountDown = 0;
					isJumping = false;
					speedY = 0;
					tempY = getY();
				}else if(inPortalVert){
					inPortalVert = false;
					System.out.println("Teleporting");*/
					teleportToOtherPortal(portal);
				}
			}
		}
		}/**/
	}
	int telecount = 0;
	IEntityPortal otherportal;
	public void teleportToOtherPortal(IEntityPortal portal){
		IEntityPortal other = portal;
		if(!portal.isHorizontal())
			other = portal.getOtherPortal();
		
		otherportal = other;
		telecount ++;
		if(telecount > 2){
			other = otherportal.getOtherPortal();
			telecount = 0;
		}
		if(other.isHorizontal() && other.getDir() == EnumSide.BOTTOM){
			int newx = other.getX();																																																												
			int newy = other.getY()+4;
			setX(newx);
			setY(newy);
			System.out.println("Teleporting to : "+newx+", "+newy+" with Dir = BOTTOM");
		}else if(other.isHorizontal() && other.getDir() == EnumSide.TOP){
			int newx = other.getX();
			int newy = other.getY()-16;
			setX(newx);
			setY(newy);
			System.out.println("Teleporting to : "+newx+", "+newy+" with Dir = TOP");
		}else if (other.getDir() == EnumSide.RIGHT){
			int newx = other.getX()+4;
			int newy = other.getY();
			setX(newx);
			setY(newy);
			System.out.println("Teleporting to : "+newx+", "+newy+" with Dir = RIGHT");
		}else{
			int newx = other.getX()-1-image.getWidth();
			int newy = other.getY();
			setX(newx);
			setY(newy);
			System.out.println("Teleporting to : "+newx+", "+newy+" with Dir = LEFT");
		}
	}
	public boolean willShootBlue(){return willShootBlue;}
	public void mouseClicked(int x, int y){
		
		x=(x/2);
		y=(y/2);

		int fromX = getX() + 12;
		int fromY = getY() + 18;
		
		if (getDirection()[0] < 0)
			fromX = getX() + 5;
		
		if(willShootBlue){
			//if (blueportal!=null)
				//remove last blue portal?
			shotPortalBlue = new EntityShotPortal_Blue(x,y,fromX,fromY,this);
			shotPortalBlue.setMap(map);
			if(shotPortalBlue.go())
				willShootBlue = false;
		}else{
			shotPortalRed = new EntityShotPortal_Red(x,y,fromX,fromY,this);
			shotPortalRed.setMap(map);
			if(shotPortalRed.go())
				willShootBlue = true;
		}//*/
	}
	public void mouseRightClicked()//switch portal to other color; we may want it to be a button but I though mouse would work well.
	{
		willShootBlue = !willShootBlue;
	}
	public boolean isJumping(){
		return (jumpCountDown != 0);
	}
	public void processKeys(){
		boolean[] keys = GamePanel.instance.keys;
		if(keys[KeyEvent.VK_D]){
			setImage((isJumping())? rightJumpAnim.next() : rightAnim.next());
			if(moveRight()){
				setImage((isJumping())? rightJumpAnim.back() : rightAnim.back());
			}
		}else if(keys[KeyEvent.VK_A]){
			setImage((isJumping())? leftJumpAnim.next() : leftAnim.next());
			if(moveLeft()){
				setImage((isJumping())? leftJumpAnim.back() : leftAnim.back());
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
				if (GamePanel.debug)
					System.out.println("Jump Countdown = "+jumpCountDown);
				int newy = getY()+ ((speedY < 0)? speedY++ : speedY);
				int x = Map.pixelsToTiles(getX());
				int y = Map.pixelsToTiles(newy);
				Tile t= map.getTile(y, x);
				if((t != null && isSolid(t))){
					speedY = 0;
					setY(Map.tilesToPixels(y)+16);
					
					fall();
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
		if(!inPortalVert && (t != null && speedX == 0 && speedY == 0) && isSolid(t)){
			setY(getY()-1);
		}
		if(getX()<0)setX(0);
		if(getX()>(map.getPreferredSize().width/2)-16)setX((map.getPreferredSize().width/32)-16);
		if(getY()>(map.getPreferredSize().height/2))kill();
	}
	
	public int getSpeedX(){ return speedX; }
	
	private boolean isSolid(Tile t){
		return (t.boundingBox(0, 0) != null || t instanceof IDecorationGrate);
	}
	
	public void kill(){
		System.out.println("player died!");
		setX(startx);
		setY(starty);
		map.reset();
	}
	EntityPlayer otherplayer = GamePanel.instance.otherPlayer;
	@Override
	public void draw(Graphics2D g){
		super.draw(g);
		//System.out.println(name()+" drawing!");
		if(redportal != null)
			redportal.draw(g);
		if(blueportal != null)
			blueportal.draw(g);
		if(otherplayer != null){
			IEntityPortal_Red greenportal = otherplayer.getFirstPortal();
			IEntityPortal_Blue purpleportal = otherplayer.getSecondPortal();
			if(greenportal != null)
				greenportal.drawOtherColor(g);
			if(purpleportal != null)
				purpleportal.drawOtherColor(g);
		}
	}
	public int getSpeedY(){ return speedY; }
	public void jump(){
		speedY = -6;
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
		if(!inPortalHoriz && ((t != null && isSolid(t)) || (t2 != null && isSolid(t2))) ){
			
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
		if(!inPortalHoriz && ((t != null && isSolid(t)) || (t2 != null && isSolid(t2))) ){
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
		if(inPortalVert){
			setY(tempY);
		}
		int newy = getY()+((speedY == 0)? speedY = 1 :(speedY < 0)? speedY+=1.5 : (Math.abs(speedY) < MAX_SPEEDY)? speedY+=1.5 : speedY)+32;
		int x = Map.pixelsToTiles(getX());
		int y = Map.pixelsToTiles(newy);
		int x2 = Map.pixelsToTiles(getX()+16);
		Tile t = map.getTile(y, x);
		Tile t2 =map.getTile(y, x2);
		if(!inPortalVert && ((t != null && isSolid(t)) || (t2 != null && isSolid(t2)))){
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
		if (GamePanel.debug)
			System.out.println("Setting red portal");
		redportal =portal;
		redportal.setOtherPortal(blueportal);
		if(blueportal!=null)
		blueportal.setOtherPortal(redportal);
	}
	public void setBluePortal(IEntityPortal_Blue portal){
		if (GamePanel.debug)
			System.out.println("Setting blue portal");

		blueportal = portal;
		blueportal.setOtherPortal(redportal);
		if(redportal != null)
		redportal.setOtherPortal(blueportal);
	}
	
	
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

		String[] redPortalX = {"",""};
		String[] redPortalY = {"",""};
		String[] redPortalDir = {"",""};

		String[] bluePortalX = {"",""};
		String[] bluePortalY = {"",""};
		String[] bluePortalDir = {"",""};
		
<<<<<<< HEAD
		String[] redPortalX = new String[] {"rx",((Integer)redportal.getX()).toString()};
		String[] redPortalY = new String[] {"ry",((Integer)redportal.getY()).toString()};
		String[] redPortalDir  = new String[] {"rd",((Integer)redportal.getDirInt()).toString()};
		
		String[] bluePortalX = new String[] {"bx",((Integer)blueportal.getX()).toString()};
		String[] bluePortalY = new String[] {"by",((Integer)blueportal.getY()).toString()};
		String[] bluePortalDir  = new String[] {"bd",((Integer)blueportal.getDirInt()).toString()};
		String[] anim = {"anim", (speedX <= 0 && speedY == 0)? "left" : (speedX > 0 && speedY == 0)? "right" : (speedX <= 0 && speedY != 0)? "leftjump" : "rightjump"};
		Animation currAnim = anim[1].equals("left")? leftAnim : anim[1].equals("right")? rightAnim : anim[1].equals("leftjump")? leftJumpAnim : rightJumpAnim;
		String[] data = currAnim.getData();
		String[] a1 = {"a1", data[0]};
		String[] a2 = {"a2", data[1]};
		String[] a3 = {"a3", data[2]};
		String[] a4 = {"a4", data[3]};
=======
		
		if (redportal != null)
		{
		redPortalX = new String[] {"rx",((Integer)redportal.getX()).toString()};
		redPortalY = new String[] {"ry",((Integer)redportal.getY()).toString()};
		redPortalDir  = new String[] {"rd",((Integer)redportal.getDirInt()).toString()};
		}
		
		if (blueportal != null)
		{
		bluePortalX = new String[] {"bx",((Integer)blueportal.getX()).toString()};
		bluePortalY = new String[] {"by",((Integer)blueportal.getY()).toString()};
		bluePortalDir  = new String[] {"bd",((Integer)blueportal.getDirInt()).toString()};
		}
		
>>>>>>> origin/master
		return new String[][]{
				xRow,yRow, 
				redPortalX, redPortalY, redPortalDir, 
				bluePortalX, bluePortalY, bluePortalDir,
				anim, a1, a2, a3, a4
				};
	}
	
	/** update all internal variables to new values based on data packed by a different client.
	 * Should follow exact same pattern for decoding as it does for encoding.
	 */
	@Override
	public void unpackData(String[][] update) {
		Animation currAnim = leftAnim;
		int[] animdata = new int[4];
		for(String[] row : update)//loop through each item to use with this frame update
		{

<<<<<<< HEAD
				case "bx"://blue portal stuff
					blueportal.setX(Integer.decode(row[1]));
					break;
				case "by":
					blueportal.setY(Integer.decode(row[1]));
					break;
				case "bz":
					blueportal.setDirInt(Integer.decode(row[1]));
					break;
					
				case "anim": // animation stuff
					String name = row[1];
					if(name.equals("left"))
						currAnim = leftAnim;
					else if(name.equals("right"))
						currAnim = rightAnim;
					else if(name.equals("leftjump"))
						currAnim = leftJumpAnim;
					else
						currAnim = rightJumpAnim;
					break;
				case "a1":
					animdata[0] = Integer.parseInt(row[1]);
					break;
				case "a2":
					animdata[1] = Integer.parseInt(row[1]);
					break;
				case "a3":
					animdata[2] = Integer.parseInt(row[1]);
					break;
				case "a4":
					animdata[3] = Integer.parseInt(row[1]);
=======
			if (row != null && row.length == 2)
			{
				switch (row[0]){
					case "x":
						setX(Integer.decode(row[1]));
						break;
					case "y":
						setY(Integer.decode(row[1]));
						break;
		
					case "rx"://red portal stuff
						if(redportal != null)redportal.setX(Integer.decode(row[1]));
						break;
					case "ry":
						if(redportal != null)redportal.setY(Integer.decode(row[1]));
						break;
					case "rz":
						if(redportal != null)redportal.setDirInt(Integer.decode(row[1]));
						break;
		
		
					case "bx"://blue portal stuff
						if(blueportal != null)blueportal.setX(Integer.decode(row[1]));
						break;
					case "by":
						if(blueportal != null)blueportal.setY(Integer.decode(row[1]));
						break;
					case "bz":
						if(blueportal != null)blueportal.setDirInt(Integer.decode(row[1]));
						break;
				}
>>>>>>> origin/master
			}
		}
		currAnim.setData(animdata);
		setImage(currAnim.getCurrent());
	}
	
	@Override//not used in the player class, but is in other entities.
	public int getIdentifier() {
		return 0;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username_) {
		username = username_;
	}
	
	public IEntityPortal_Red getFirstPortal(){ return redportal; }
	public IEntityPortal_Blue getSecondPortal(){ return blueportal;}
}
