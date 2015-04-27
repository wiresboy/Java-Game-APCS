package main;

import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Test extends Applet implements KeyListener{
	public Test(){
		this.addKeyListener(this);
	}
	public static void main(String[] args){
		new Test();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.toString()+" "+e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
