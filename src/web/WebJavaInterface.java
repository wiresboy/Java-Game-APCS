package web;

import entity.EntityPlayer;

public class WebJavaInterface{
	private static final String baseURL = "http://java-game-apcs.appspot.com/";//"http://localhost:11080/";//"http://java-game-apcs.appspot.com/apcs/";//now old, needs to be updated for the Google url.
	private static final String playerURL = baseURL + "game/update/";
	private static String username;//MyID.
	private static String theirUsername;//username of the other player. Doubles as theirID.
	private static String mapName;//name of map that is being hosted, doubles as GameID in v1, this may change later.

	
	/** 
	 * MUST call on startup, only does basic stuff. Doesn't connect to server.
	 * initialize the web connection, make sure that everything is ready.
	 * @return true if successfully initialized, false if no Internet connection.
	 */
	public static boolean init()
	{
		mapName = "testmap";
		Web.init();
		//TODO: Make sure server is responding?
		return true;
	}
	
	
	/**
	 * Initializes a game connection. Must be called to initialize socket with the server.
	 * @param myID my id that the other player will enter to connect with me.
	 * @param theirID if of the other player, used to connect to them. Must be identical (except case) to their name.
	 * @param gameID id of which game is being played. Currently should be the same as the game file (without the extension).
	 */
	public static void v1Init(String myID, String theirID, String gameID)
	{
		username = myID;
		theirUsername = theirID;
		mapName = gameID;
		//connect to server and get an instance number
		
	}
	
	
	private static final int HTTPResponseSuccess = 200;
	private static final int HTTPResponseMissingParams = 400;
	private static final int HTTPResponseFileNotFound = 404;
	private static final int HTTPResponseMapUnknown = 412;
	private static final int HTTPResponseOtherPlayerNotConnected = 418;
	
	/**
	 * Updates player status and gets other player's status
	 * @param me player being controlled by this computer
	 * @param it object of other player, which will be updated with unpackData 
	 * @return player- other player
	 */
	public static void updatePlayerStatus(EntityPlayer me, EntityPlayer it)
	{
		//TODO: Also get other data from server on match status like if it is still running, etc. How will this be returned? Game status?
		
		String toSend = DataTransmitter.pack2DStringArray(me.packData());
		
		try {
			String rawReceived = Web.post(playerURL, new String[][]{{"game",mapName},{"me",username},{"meData", toSend},{"it",theirUsername}});
			//Send my data, receive other players data.
			
			int HTTPResponseCode = Web.LastResponseCode();
			
			switch(HTTPResponseCode)
			{
			case HTTPResponseSuccess:
				String[] ReceivedParts = rawReceived.split(":");
				it.unpackData(DataTransmitter.Unpack2DStringArray(ReceivedParts[0]));//send unpacked data to the other player object for updating and rendering.
				break;
			case HTTPResponseFileNotFound:
				throw new Exception("404 File not found: \n"+rawReceived);
			case HTTPResponseMapUnknown:
				throw new Exception("412 Map unknown ("+mapName+"): \n"+rawReceived);
			case HTTPResponseOtherPlayerNotConnected:
				throw new Exception("418 Other player ("+theirUsername+") isn't connected: \n"+rawReceived);
			case HTTPResponseMissingParams:
				throw new Exception("400 Apparently we had a malformed request that was missing some parameters: \n"+rawReceived);
			default:
				throw new Exception("Unknown response code: "+HTTPResponseCode+"\n"+rawReceived);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateGameStatus()
	{
		//todo: update game status, somehow merging between me and them.
	}

}
