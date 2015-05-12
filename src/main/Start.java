package main;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Start extends JFrame implements WindowListener{
	private static final long serialVersionUID = 6074277649488997023L;
	private static int DEFAULT_FPS = 25;      // 50 is too fast! 
	
	private GamePanel panel;        // where the game is drawn
	
	public Start(long period){ 
		super("we should name this?");
		PreSetup.init();
		Container c = getContentPane();    // default BorderLayout used
		panel = new GamePanel(this, period);
		c.add(panel, "Center");
		//setSize(16*16*2,(10*16+13)*2);
		addWindowListener( this );
		pack();
		setResizable(true);
		setVisible(true);
		
		
	}  // end of Start() constructor
	
	
	// ----------------- window listener methods -------------
	
	public void windowActivated(WindowEvent e) { 
		panel.resumeGame();  
	}
	
	public void windowDeactivated(WindowEvent e) { 
		panel.pauseGame();  
	}
	public void windowDeiconified(WindowEvent e) {  
		panel.resumeGame();  
	}
	public void windowIconified(WindowEvent e) {  
		panel.pauseGame();
	}
	public void windowClosing(WindowEvent e){  
		panel.stopGame();  
	}
	public void windowClosed(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	
	// ----------------------------------------------------
	
	public static void main(String args[]){
		long period = (long) 1000.0/DEFAULT_FPS;
		// System.out.println("fps: " + DEFAULT_FPS + "; period: " + period + " ms");
		new Start(period*1000000L);    // ms --> nanosecs 
	}

} // end of class



