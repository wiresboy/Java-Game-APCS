package web;

import java.util.Scanner;

public class Chat implements MessageHookInterface, Runnable {

	public static boolean run = true;//when set to false, chat ends.
	
	private String myName;
	
	public Chat(String myName)
	{
		this.myName = myName;
		(new Thread(this)).start();
	}
	
	@Override
	public void run() {
		MessageHolder.addHook(this);
		Scanner key = new Scanner(System.in);
		while(run)
		{
			if (key.hasNextLine())
			{
				String input = myName + ": " + key.nextLine();
			
			MessageHolder.AddMessageToSend(Message.MessageFactoryStringToB64(Message.typeEnum.Chat, input));
			}
		}
		key.close();
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
