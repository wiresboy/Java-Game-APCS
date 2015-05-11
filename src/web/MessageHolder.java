package web;

import java.util.ArrayList;

public class MessageHolder {


	private static ArrayList<Message> toSend = new ArrayList<Message>();
	private static ArrayList<Message> received = new ArrayList<Message>();//holds messages until they have been claimed.

	
	/**
	 * Adds messages from a 
	 * @param toAdd
	 */
	public static void AddReceivedMessages(String toAddRaw)
	{
		received.add(new Message(toAddRaw));
	}
}
