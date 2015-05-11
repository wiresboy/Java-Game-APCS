package web;

public class Message {
	private String content;//always b64 encoded while in storage.
	private typeEnum type;
	public static enum typeEnum {
	    Chat, //indicates that there is 
	    EntityUpdated,
	    error
	}
	
	/**
	 * Construct a new Message object from the b64 encoded response from the server. Must have already been split. 
	 * First character will indicate the Type, and the rest will be stored as the content.
	 * @param rawReceived
	 */
	Message(String rawReceived)
	{
		char firstChar = rawReceived.charAt(0);
		switch(firstChar)
		{
		case 'c':
			type = Message.typeEnum.Chat;
			content = rawReceived.substring(1);//char 1 to the end
			break;
		case 'e':
			type = Message.typeEnum.EntityUpdated;
			content = rawReceived.substring(1);//char 1 to the end
			break;
		default:
			//error!!!!
			System.out.println("Invalid Message type. Received: '"+firstChar+"'");
		}
		
		
		
		
	}
	
	
	
	public typeEnum getType()
	{
		return type;
	}
	
	public String toString()
	{
		return typeToString(type)+": "+content;
	}
	
	/**
	 * Convert a 1-long string to a typeEnum
	 * @param s
	 * @return
	 */
	private static typeEnum stringToType(String s)
	{
		return charToType(s.charAt(0));
	}
	
	/**
	 * Convert a char string to a typeEnum
	 * @param s
	 * @return
	 */
	private static typeEnum charToType(char c)
	{
		switch(c)
		{
		case 'c':
			return Message.typeEnum.Chat;
		case 'e':
			return Message.typeEnum.EntityUpdated;
		default:
			//error!!!!
			System.out.println("Invalid Message type. Received: '"+c+"'");
			return Message.typeEnum.error;
		}
	}
	
	/**
	 * convert a typeEnum to it's string equivalent
	 * @param t
	 * @return
	 */
	private static String typeToString(typeEnum t)
	{
		switch(t)
		{
		case Chat:
			return "c";
		case EntityUpdated:
			return "e";
		default:
			//error!!!!
			System.out.println("Invalid Message type. Received: '"+t+"'");
			return null;
		}
	}
}
