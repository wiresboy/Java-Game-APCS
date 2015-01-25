package main;

import javax.swing.*;

import util.*;
import entity.Player;

import java.awt.image.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	private static final int PWIDTH = 16*16*2;   // size of panel
	private static final int PHEIGHT = (10*16+9)*2; 

	private static int MAP_ID = 0;

	private static final int NO_DELAYS_PER_YIELD = 16;
	/* Number of frames with a delay of 0 ms before the animation thread yields
  	to other running threads. */
	private static final int MAX_FRAME_SKIPS = 5;

	private Thread animator;           // the thread that performs the animation
	private volatile boolean running = false;   // used to stop the animation thread
	private volatile boolean isPaused = false;

	private long period;                // period between drawing in _nanosecs_

	private int tempX, tempY;

	private Start mainFrame;
	private Player player;          // the sprites

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

	private int numHits = 0;   // the number of times the player has been hit

	private ArrayList<Integer> walkableTiles;

	private static int[][] mapIndexes = new int[8][16];
	private int mapRow = 7, mapColumn = 7;

	public GamePanel(Start frame, long period){
		mainFrame = frame;
		this.period = period;
		player = new Player(7*32,5*32);
		setDoubleBuffered(false);
		setBackground(Color.white);
		setPreferredSize( new Dimension(PWIDTH, PHEIGHT));

		setFocusable(true);
		requestFocus();    // the JPanel now has focus, so receives key events, in theory
		addKeyListener(this); 
		int index = 0;
		for(int r = 0;r<8;r++)
			for(int c = 0;c<16;c++){
				mapIndexes[r][c] = index;
				index++;
			}
		walkableTiles = new ArrayList<Integer>();
		walkableTiles.add(2);
		walkableTiles.add(8);
		walkableTiles.add(14);
		walkableTiles.add(22);
		walkableTiles.add(28);
		walkableTiles.add(34);
	}  // end of GamePanel

	private void processKey(KeyEvent e){
		int keyCode = e.getKeyCode();
	
		if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q) || (keyCode == KeyEvent.VK_END) || ((keyCode == KeyEvent.VK_C) && e.isControlDown()) )
			running = false;

		// game-play keys
		if (!isPaused && !gameOver) {
			// move the sprite and ribbons based on the arrow key pressed
			//move LEFT
			if (keyCode == KeyEvent.VK_LEFT){
								
			}
			//move RIGHT
			else if (keyCode == KeyEvent.VK_RIGHT) {
				
			} 
			//move UP
			else if (keyCode == KeyEvent.VK_UP){
								
			//move DOWN
			}else if (keyCode == KeyEvent.VK_DOWN) {
								
			}
			
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
		} 
	   
	}  // end of gameUpdate()
	
	
	private void gameRender(){
		if (dbImage == null){
			dbImage = createImage(PWIDTH, PHEIGHT);
		if (dbImage == null) {
			System.out.println("dbImage is null");
			return;
		}
		else
			dbg = dbImage.getGraphics();
		}
	
		// draw a white background
		dbg.setColor(Color.white);
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
		
		player.draw(g);
		g.setColor(Color.WHITE);
		g.drawString("X:"+player.x+" Y:"+player.y,0,10);
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
		
		g.drawString("Time: " + timeSpentInGame + " secs"+"   Image Index: "+player.imageIndex(), 0,15);
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
				g.drawImage(dbImage, 0, 0, null);
			// Sync the display on some systems.
			// (on Linux, this fixes event queue problems)
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		}
		catch (Exception e){ System.out.println("Graphics context error: " + e);}
	} // end of paintScreen()
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		processKey(arg0);
	}
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {}
	
	
	@Override
	public void keyTyped(KeyEvent arg0) {}

}  // end of class

