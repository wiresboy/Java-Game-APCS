package web;

import main.GamePanel;
import entity.EntityPlayer;

public class WebRunner implements Runnable {
	
	private EntityPlayer PlayerMe = null;
	private EntityPlayer PlayerIt = null;
	private GameStatus Game = null;
	private boolean run = true;//keep running until told to stop!
	
	public WebRunner(EntityPlayer me, EntityPlayer it, GameStatus game)
	{
		this.PlayerMe = me;
		this.PlayerIt = it;
		this.Game = game;
		run = true;
		if (GamePanel.debug)
			System.out.println("Starting WebRunner");
		(new Thread(this)).start();
	}
	
	//stop the thread from updating. Use when done with a game.
	public void stop()
	{
		run = false;
	}
	
	@Override
	public void run() {
		WebJavaInterface.init();
		WebJavaInterface.v1Init(PlayerMe.getUsername(), PlayerIt.getUsername(), Game.getGameName());//initialize web interface to this game and these players.
		while (run)
		{
			WebJavaInterface.updatePlayerStatus(PlayerMe, PlayerIt);
			//TODO: get WebInterface GameStatus
			//try {
			//	Thread.sleep(100);
			//} catch (InterruptedException e) {}//continue immediately
		}
		//TODO: send some info to the server that I am quitting
	}

}
