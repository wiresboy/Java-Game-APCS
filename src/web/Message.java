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
		
		switch(stringToType(rawReceived))
		{
		case Chat:
			type = Message.typeEnum.Chat;
			content = rawReceived.substring(1);//char 1 to the end
			break;
		case EntityUpdated:
			type = Message.typeEnum.EntityUpdated;
			content = rawReceived.substring(1);//char 1 to the end
			break;
		case error:
		default:
			break;//error!
		}
	}
	
	/**
	 * Construct a new Message object to be sent from a b64 encoded content string and a typeEnum. 
	 * First character will indicate the Type, and the rest will be stored as the content.
	 * @param rawReceived
	 */
	Message(typeEnum t, String c)
	{
		this.type = t;
		this.content = c;
	}
	
	
	public typeEnum getType()
	{
		return type;
	}
	
	public String getStringContentFromB64()
	{
		return Base64Coder.decodeString(content);
	}
	
	public byte[] getBytesContentFromB64()
	{
		return Base64Coder.decode(content);
	}
	
	/**
	 * Use toString to generate the string to send to the server.
	 */
	public String toString()
	{
		return typeToString(type)+content;
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
	
	public static Message MessageFactoryStringToB64(typeEnum t, String real)
	{
		return new Message(t, Base64Coder.encodeString(real));
	}
	
}
