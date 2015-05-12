package main;

import javax.swing.*;

import map.Map;

import util.*;
import web.GameStatus;
import web.WebRunner;
import entity.Entity;
import entity.EntityPlayer;
import entity.EntityPlayerWebControlled;
import entity.Player;

import java.awt.image.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;
/**
 * 
 * @author Lucas Rezac, Brandon John
 *
 */
public class GamePanel extends JPanel implements Runnable, KeyListener,MouseListener,MouseMotionListener{

	private static final long serialVersionUID = 1L;
	public static final int PWIDTH = 16*16*2; // size of panel
	public static final int PHEIGHT = (10*16+9)*2; 
	public static final int DHEIGHT = 32*16;
	public static final int DWIDTH= 256*16;
	public static final Color BLUE = new Color(121,214,253);
	public static final Color RED = new Color(250,147,38);
	public static int offset = 0;
	//private static int MAP_ID = 0;

	private static final int NO_DELAYS_PER_YIELD = 16;
	/* Number of frames with a delay of 0 ms before the animation thread yields
  	to other running threads. */
	private static final int MAX_FRAME_SKIPS = 5;

	private Thread animator;           // the thread that performs the animation
	private volatile boolean running = false;   // used to stop the animation thread
	private volatile boolean isPaused = false;

	private long period;                // period between drawing in _nanosecs_

	//private int tempX, tempY;
	private int mousex = 0, mousey = 0;
	
	private Start mainFrame;
	private EntityPlayer player; 
	public static GamePanel instance;

	private long gameStartTime;   // when the game started
	private int timeSpentInGame;

	// used at game termination
	private volatile boolean gameOver = false;
	private int score = 0;

	// for displaying messages
	private Font msgsFont;
	private FontMetrics metrics;

	// off-screen rendering
	private Graphics dbg; 
	private Image dbImage = null;

	// to display the title/help screen
	private boolean showHelp;
	private BufferedImage helpIm;

	//private int numHits = 0;

	//private ArrayList<Integer> walkableTiles;
	
	private boolean debug = true;
	
	// **************WEB STUFF***************
	private boolean singlePlayer = false; //when testing this at school before I have the web thing figured out, you will need to set this to true.
					//this may become a nice 1 vs 2 player feature, that can be set somewhere. For now, it is just a testing thing.
	private EntityPlayer otherPlayer = null; 
	private GameStatus gameStatus = null;//holds the status of the game for use with transfering thread info stuff. After initialization, DO NOT modify this!
	private WebRunner webRunner = null;
	
	public static int[] tempLineHelp = new int[4];

	public boolean[] keys = new boolean[192];
	
	private Map map;
	public GamePanel(Start frame, long period){
		mainFrame = frame;
		instance = this;
		this.period = period;
		setDoubleBuffered(false);
		setBackground(Color.white);
		

		setFocusable(true);
		requestFocus();    // the JPanel now has focus, so receives key events, in theory
		addKeyListener(this); 
		addMouseListener(this);
		addMouseMotionListener(this);
		map = new Map();
		int[] playerLocs = map.loadMap("testmap");//playerLocs holds location of the player. For now, both players start in the same place, so used for both.
		player = new EntityPlayer(playerLocs[0],playerLocs[1],"Chell");
		player.setMap(map);
		
		if (!singlePlayer)
		{
			//TODO: Start menu that generates this info?
			Scanner key = new Scanner(System.in);//get a scanner object for getting players names. This SHOULD be replaced by a menu.
			//initialize web settings:
			
			String mapName = map.mapName();
			System.out.println("Please enter your username: (Note, the other player will have to enter yours identically! So don't make it too crazy!) ");
			String myName = key.nextLine();
			System.out.println("Please enter the other players username. They must have the same map open as you do. You are playing |"+mapName+"|.");
			String theirName = key.nextLine();
			key.close();//must close the scanner so we don't cause any keyboard input issues.
			
			player.setUsername(myName);
			otherPlayer = new EntityPlayerWebControlled(playerLocs[0],playerLocs[1],"Chell",theirName);
			otherPlayer.setMap(map);
			
			gameStatus = new GameStatus(mapName);
			
			webRunner = new WebRunner(player,otherPlayer,gameStatus);
			
			//TODO: we may want to wait here for the other player to sign in?
			
		}
		
		setBackground(Color.GRAY);
		setPreferredSize(map.getPreferredSize());
	}  // end of GamePanel

	private void processKey(KeyEvent e){
		int keyCode = e.getKeyCode();
	
		if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q) || (keyCode == KeyEvent.VK_END) || ((keyCode == KeyEvent.VK_C) && e.isControlDown()) )
			running = false;

		if (!isPaused && !gameOver) {			
		}
	}  // end of processKey()

	public void addNotify(){
		super.addNotify();   // creates the peer
		startGame();         // start the thread
	}

	private void startGame(){ 
		if (animator == null || !running) {
			animator = new Thread(this);
			animator.start();
		}
	} // end of startGame()

	public void resumeGame(){
		if (!showHelp)    // CHANGED
			isPaused = false;  
	} 

	public void pauseGame(){
		isPaused = true;
	} 

	public void stopGame(){
		running = false;
	}

	public void run(){
		long beforeTime, afterTime, timeDiff, sleepTime;
		long overSleepTime = 0L;
		int noDelays = 0;
		long excess = 0L;
	
		gameStartTime = System.nanoTime();//J3DTimer.getValue();
		beforeTime = gameStartTime;
	
		running = true;
	
		while(running) {
			gameUpdate();
			gameRender();
			paintScreen();
	
			afterTime = System.nanoTime();
			timeDiff = afterTime - beforeTime;
			sleepTime = (period - timeDiff) - overSleepTime;  
	
			if (sleepTime > 0) {   // some time left in this cycle
				try {
					//Thread.sleep(sleepTime/1000000L);  // nano -> ms
					Thread.sleep(50);
				}
				catch(InterruptedException ex){}
				overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
			}
			else {    // sleepTime <= 0; the frame took longer than the period
				excess -= sleepTime;  // store excess time value
				overSleepTime = 0L;
	
				if (++noDelays >= NO_DELAYS_PER_YIELD) {
					Thread.yield();   // give another thread a chance to run
					noDelays = 0;
				}
			}
	
			beforeTime = System.nanoTime();
	
			int skips = 0;
			while((excess > period) && (skips < MAX_FRAME_SKIPS)) {
				excess -= period;
				gameUpdate();    // update state but don't render
				skips++;
			}
		}
		System.exit(0);   // so window disappears
	} // end of run()
	
	
	private void gameUpdate(){ 
		if (!isPaused && !gameOver){
			player.update();
			
			if (!singlePlayer)
			{
				otherPlayer.update();
			}
			
			for(Entity e : Entity.list){
				e.update();
			}
		} 
	   
	}  // end of gameUpdate()
	
	
	private void gameRender(){
		if (dbImage == null){
			dbImage = createImage(DWIDTH, DHEIGHT);
		if (dbImage == null) {
			System.out.println("dbImage is null");
			return;
		}
		else
			dbg = dbImage.getGraphics();
		}
	
		// draw a white background
		dbg.setColor(Color.GRAY);
		dbg.fillRect(0, 0, PWIDTH, PHEIGHT);
	
	 
		reportStats(dbg);
		
		/*
		┌───────────────────────────────┐
		│								│
		│	DRAW GAME GRAPHICS HERE		│
		│								│
		└───────────────────────────────┘
		*/
		Graphics2D g = (Graphics2D)dbg;
		
		map.draw(g);
		player.draw(g);
		
		if (!singlePlayer)//Draw the other player only if they exist. 
			otherPlayer.draw(g);
		
		if(debug){
			g.setColor(Color.WHITE);
			g.drawString("X:"+player.getX()+" Y:"+player.getY()+" speedX:"+player.getSpeedX()+" speedY:"+player.getSpeedY(),0,10);
			g.drawString("mousex:"+mousex+" mousey:"+mousey,0,20);
			
		}
		if(player.willShootBlue()){
			g.setColor(BLUE);
			
		}else{
			g.setColor(RED);
		}
		
		
		g.fillRect(2,map.getPreferredSize().height/2-18, 16,16);
		if(player.willShootBlue()){
			g.setColor(RED);
		}else{
			g.setColor(BLUE);
		}
		if(tempLineHelp != null){
			g.drawLine(tempLineHelp[0], tempLineHelp[1], tempLineHelp[2], tempLineHelp[3]);
			tempLineHelp = null;
		}
		
		//END DRAWING GAME SECTION
		if (gameOver)
			gameOverMessage(dbg);
		
		if (showHelp)    // draw the help at the very front (if switched on)
			dbg.drawImage(helpIm, (PWIDTH-helpIm.getWidth())/2,(PHEIGHT-helpIm.getHeight())/2, null);
	}  // end of gameRender()
	
	
	private void reportStats(Graphics g){
		if (!gameOver)    // stop incrementing the timer once the game is over
			timeSpentInGame = (int) ((System.nanoTime() - gameStartTime)/1000000000L);  // ns --> secs
		g.setColor(Color.red);
		g.setFont(msgsFont);
		
		g.drawString("Time: " + timeSpentInGame + " secs", 0,15);
		g.setColor(Color.black);
	}  // end of reportStats()
	
	
	private void gameOverMessage(Graphics g){
		String msg = "Game Over. Your score: " + score;
	
		int x = (PWIDTH - metrics.stringWidth(msg))/2; 
		int y = (PHEIGHT - metrics.getHeight())/2;
		g.setColor(Color.black);
		g.setFont(msgsFont);
		g.drawString(msg, x, y);
	}  // end of gameOverMessage()
	
	
	private void paintScreen(){ 
		Graphics g;
		try {
			g = this.getGraphics();
			if ((g != null) && (dbImage != null))
				g.drawImage(dbImage.getScaledInstance(dbImage.getWidth(null)*2, dbImage.getHeight(null)*2, Image.SCALE_FAST), -offset, 0, null);
			// Sync the display on some systems.
			// (on Linux, this fixes event queue problems)
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		}
		catch (Exception e){ System.out.println("Graphics context error: " + e);}
	} // end of paintScreen()
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = true;
		processKey(arg0);
	}
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = false;
	}
	
	
	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*tempLineHelp[0] = player.getX();
		tempLineHelp[1] = player.getY();
		tempLineHelp[2] = e.getX()-player.getX();
		tempLineHelp[3] = e.getY()-player.getY();
		*/
		if (e.getButton()==MouseEvent.BUTTON1)//was the left button clicked?
			player.mouseClicked(e.getX(),e.getY());//then shoot
		else
			//assume that it was the equivalent of the right button.
			player.mouseRightClicked();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mousex = e.getX();
		mousey = e.getY();
	}

}  // end of class

