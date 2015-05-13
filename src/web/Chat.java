package web;

import java.util.Scanner;

public class Chat implements MessageHookInterface, Runnable {

	public static boolean run = true;//when set to false, chat ends.
	
	private String myName;
	private Scanner key;
	
	public Chat(String myName, Scanner scanner_)
	{
		this.myName = myName;
		this.key = scanner_;
		(new Thread(this)).start();
	}
	
	@Override
	public void run() {
		MessageHolder.addHook(this);
		while(run)
		{
			String input = myName + ": " + key.nextLine();
			MessageHolder.AddMessageToSend(Message.MessageFactoryStringToB64(Message.typeEnum.Chat, input));
		}
		MessageHolder.removeHook(this);
	}

	@Override
	public boolean MessageHook(Message m) {
		if (m.getType() == Message.typeEnum.Chat)
		{
			String message = m.getStringContentFromB64();
			System.out.println(message);
			return true;
		}
		return false;
	}

}
