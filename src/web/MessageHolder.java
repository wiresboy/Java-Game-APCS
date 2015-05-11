package web;

import java.util.ArrayList;

public class MessageHolder {


	private static ArrayList<Message> toSend = new ArrayList<Message>();
	private static ArrayList<Message> received = new ArrayList<Message>();//holds messages until they have been claimed.
	
	private static ArrayList<MessageHookInterface> hooks = new ArrayList<MessageHookInterface>();
	
	/**
	 * Adds messages from a 
	 * @param toAdd
	 */
	public static void AddReceivedMessages(String toAddRaw)
	{
		received.add(new Message(toAddRaw));
		processMessagesWithHooks();
	}
	
	
	/**
	 * Add message to the queue to send.
	 * @param m
	 */
	public static void AddMessageToSend(Message m)
	{
		toSend.add(m);
	}
	
	
	/**
	 * Generates a string that will send all of the messages that need sending.
	 * @return String that has colons included for sending to server. Will need player object status prepended.
	 */
	public static String genStringOfMessagesToSend()
	{
		String ret = "";
		while(toSend.size()>0)
		{
			ret += ":" + toSend.remove(0);
		}
		return ret;
	}
	
	
	public static void addHook(MessageHookInterface h)
	{
		hooks.add(h);
	}
	
	public static void processMessagesWithHooks()
	{
		for (Message m : received)
		{
			for (MessageHookInterface hook: hooks)
			{
				if(hook.MessageHook(m))
				{
					received.remove(m);
					break;//go on to next message.
				}
			}
		}
	}
}
