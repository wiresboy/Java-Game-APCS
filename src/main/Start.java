package main;

/*import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Start {
	static boolean gameRunning = true;
	static long lastLoopTime = System.nanoTime();
	static final int TARGET_FPS = 60;
	static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	static long lastFpsTime = 0;
	static long fps = 0;
	static GamePanel panel;
	static JFrame frame;
	public static void main(String[] args){
		frame = new JFrame();
		frame.setSize(500,500);
		panel = new GamePanel();
		panel.start();
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	public static void startGame(){
		while(gameRunning){
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double)OPTIMAL_TIME);
			lastFpsTime += updateLength;
			fps++;
			if(lastFpsTime >= 1000000000){
				System.out.println("FPS: "+fps);
				lastFpsTime = 0;
				fps = 0;
			}
			panel.update();
			
			panel.repaint();
			
			try{
				Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
			}catch(Exception e){};
		}
	}
}
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Start extends JFrame implements ActionListener, KeyListener
{
   private GamePanel gamePanel = new GamePanel();
   private JButton startButton = new JButton("Start");
   private JButton quitButton = new JButton("Quit");
   private JButton pauseButton = new JButton("Pause");
   private boolean running = false;
   private boolean paused = false;
   private int fps = 60;
   private int frameCount = 0;
   
   public Start()
   {
      super("Fixed Timestep Game Loop Test");
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      JPanel p = new JPanel();
      p.setLayout(new GridLayout(1,2));
      p.add(startButton);
      p.add(pauseButton);
      p.add(quitButton);
      cp.add(gamePanel, BorderLayout.CENTER);
      //cp.add(p, BorderLayout.SOUTH);
      setSize(500, 500);
      
      startButton.addActionListener(this);
      quitButton.addActionListener(this);
      pauseButton.addActionListener(this);
      addKeyListener(this);
   }
   
   public static void main(String[] args)
   {
      Start glt = new Start();
      glt.setVisible(true);
      glt.runGameLoop();
   }
   
   public void actionPerformed(ActionEvent e)
   {
      Object s = e.getSource();
      if (s == startButton)
      {
         running = !running;
         if (running)
         {
            startButton.setText("Stop");
            runGameLoop();
         }
         else
         {
            startButton.setText("Start");
         }
      }
      else if (s == pauseButton)
      {
        paused = !paused;
         if (paused)
         {
            pauseButton.setText("Unpause");
         }
         else
         {
            pauseButton.setText("Pause");
         }
      }
      else if (s == quitButton)
      {
         System.exit(0);
      }
   }
   
   //Starts a new thread and runs the game loop in it.
   public void runGameLoop()
   {
      Thread loop = new Thread()
      {
         public void run()
         {
            gameLoop();
         }
      };
      loop.start();
   }
   
   //Only run this in another Thread!
   private void gameLoop()
   {
      //This value would probably be stored elsewhere.
      final double GAME_HERTZ = 30.0;
      //Calculate how many ns each frame should take for our target game hertz.
      final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
      //At the very most we will update the game this many times before a new render.
      //If you're worried about visual hitches more than perfect timing, set this to 1.
      final int MAX_UPDATES_BEFORE_RENDER = 5;
      //We will need the last update time.
      double lastUpdateTime = System.nanoTime();
      //Store the last time we rendered.
      double lastRenderTime = System.nanoTime();
      
      //If we are able to get as high as this FPS, don't render again.
      final double TARGET_FPS = 60;
      final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
      
      //Simple way of finding FPS.
      int lastSecondTime = (int) (lastUpdateTime / 1000000000);
      
      while (running)
      {
         double now = System.nanoTime();
         int updateCount = 0;
         
         if (!paused)
         {
             //Do as many game updates as we need to, potentially playing catchup.
            while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
            {
               updateGame();
               lastUpdateTime += TIME_BETWEEN_UPDATES;
               updateCount++;
            }
   
            //If for some reason an update takes forever, we don't want to do an insane number of catchups.
            //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
            if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
            {
               lastUpdateTime = now - TIME_BETWEEN_UPDATES;
            }
         
            //Render. To do so, we need to calculate interpolation for a smooth render.
            float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
            drawGame(interpolation);
            lastRenderTime = now;
         
            //Update the frames we got.
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime)
            {
               System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
               fps = frameCount;
               frameCount = 0;
               lastSecondTime = thisSecond;
            }
         
            //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
            while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
            {
               Thread.yield();
            
               //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
               //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
               //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
               try {Thread.sleep(1);} catch(Exception e) {} 
            
               now = System.nanoTime();
            }
         }
      }
   }
   
   private void updateGame()
   {
      gamePanel.update();
   }
   
   private void drawGame(float interpolation)
   {
      gamePanel.setInterpolation(interpolation);
      gamePanel.repaint();
   }

   @Override
   public void keyPressed(KeyEvent e) {
	   // TODO Auto-generated method stub
	   gamePanel.processKey(e);
   }

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

}/**/

//JumpingJack.java
//Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* A side-scroller showing how to implement background
movement, bricks, and a jumping sprite (called 'jack)
who can run, jump, and collide with bricks.

Fireball shoot out from the rhs of the panel, and 
will explode if they hit jack.

The background is composed of multiple ribbons
(wraparound images) which move at different rates 
depending on how 'far back' they are in
the scene. This effect is called parallax scrolling.

-----
Pausing/Resuming/Quiting are controlled via the frame's window
listener methods.

Active rendering is used to update the JPanel. See WormP for
another example, with additional statistics generation.

Using Java 3D's timer: J3DTimer.getValue()
  *  nanosecs rather than millisecs for the period

The MidisLoader, ClipsLoader, ImagesLoader, and ImagesPlayer
classes are used for music, images, and animation.

The jumping and fireball sprites are subclasses of the 
Sprite class discussed in chapter 6.
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Start extends JFrame implements WindowListener
{
private static int DEFAULT_FPS = 30;      // 40 is too fast! 

private GamePanel jp;        // where the game is drawn

public Start(long period)
{ super("Zelda");

 Container c = getContentPane();    // default BorderLayout used
 jp = new GamePanel(this, period);
 c.add(jp, "Center");
 	//setSize(16*16*2,(10*16+13)*2);
 addWindowListener( this );
 pack();
 setResizable(false);
 setVisible(true);
}  // end of JumpingJack() constructor


// ----------------- window listener methods -------------

public void windowActivated(WindowEvent e) 
{ jp.resumeGame();  }

public void windowDeactivated(WindowEvent e) 
{ jp.pauseGame();  }


public void windowDeiconified(WindowEvent e) 
{  jp.resumeGame();  }

public void windowIconified(WindowEvent e) 
{  jp.pauseGame(); }


public void windowClosing(WindowEvent e)
{  
	jp.stopGame();  
}


public void windowClosed(WindowEvent e) {}
public void windowOpened(WindowEvent e) {}

// ----------------------------------------------------

public static void main(String args[])
{ 
 long period = (long) 1000.0/DEFAULT_FPS;
 // System.out.println("fps: " + DEFAULT_FPS + "; period: " + period + " ms");
 new Start(period*1000000L);    // ms --> nanosecs 
}

} // end of JumpingJack class



