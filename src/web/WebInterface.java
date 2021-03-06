package web;
/* WebInterface.java		Brandon John		1/25/2015
 * Main class for communicating with server
 * 
 */

import java.util.ArrayList;

import entity.*;


public class WebInterface {
	private static final String baseURL = "http://1-02.java-game-apcs.appspot.com/";//"http://localhost:11080/";//"http://java-game-apcs.appspot.com/apcs/";//now old, needs to be updated for the Google url.
	private static final String playerURL = baseURL + "player.php";
	private static String username;//MyID.
	private static String theirUsername;//username of the other player. Doubles as theirID.
	private static String mapName;//name of map that is being hosted, doubles as GameID in v1, this may change later.
	

	
	/** 
	 * MUST call on startup
	 * initialize the web connection, make sure that everything is ready.
	 * @return true if successfully initialized, false if no Internet connection.
	 */
	public static boolean init()
	{
		mapName = "testmap.txt";
		Web.init();
		return true;
	}
	
	
	/**
	 * Initializes a game connection. This will eventually become more advanced.
	 * @param myID my id that the other player will enter to connect with me.
	 * @param theirID if of the other player, used to connect to them. Must be identical (except case) to their name.
	 * @param gameID id of which game is being played. Currently should be the same as the game file (without the extension).
	 */
	public static void v1Init(String myID, String theirID, String gameID)
	{
		username = myID;
		theirUsername = theirID;
		mapName = gameID;
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
		
		String toSend = DataTransmitter.pack2DStringArray(me.packData());
		try {
			String rawReceived = Web.post(playerURL, new String[][]{{"GameID",mapName},{"MyID",username},{"MyData", toSend},{"TheirID",theirUsername}});
			//Send my data, receive other players data.
			
			int HTTPResponseCode = Web.LastResponseCode();
			
			switch(HTTPResponseCode)
			{
			case HTTPResponseSuccess:
				it.unpackData(DataTransmitter.Unpack2DStringArray(rawReceived));//send unpacked data to the other player object for updating and rendering.
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
				throw new Exception("Unknown response code: "+HTTPResponseCode);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//STUFF NOT USED IN V1:
	
	@SuppressWarnings("unused")
	private static String password; //NOT USED FOR FIRST VERSION
	private static String dispName; //NOT USED FOR FIRST VERSION

	private static ArrayList<Shareable> toSend = new ArrayList<Shareable>();// NOT USED FOR FIRST VERSION
	@SuppressWarnings("unused")
	private static ArrayList<Shareable> toReceive = new ArrayList<Shareable>();// NOT USED FOR FIRST VERSION
	
	/**  NOT USED FOR FIRST VERSION
	 * Add an object to the list of objects to send to the other computer.
	 * Currenlty only sends player objects, all others are calculated separately on each computer.
	 * @param item
	 */
	public static void addItemToSend(Shareable item)
	{
		toSend.add(item);
	}
	
	/** NOT USED FOR FIRST VERSION
	 * Send a packet of data updating everything except the player data, which is sent more often.
	 * Don't use right now, since only Player info is transfered.
	 */
	public static void sendAllExceptPlayer()
	{
		
	}
	
	/** NOT USED FOR FIRST VERSION! ONLY USES USERNAME, IGNORES PASSWORD!
	 * Sign in. Usernames can be stolen, so be careful.
	 * @param username-user generated, doesn't matter. Used for joining another players game.
	 * @return "display name"
	 */
	public static String logIn(String user, String pass)
	{
		username = user;
		password = pass;// NOT USED FOR FIRST VERSION
		dispName = "U"+user;// NOT USED FOR FIRST VERSION
		return user;
	}
	
	
	/** NOT USED FOR FIRST VERSION
	 * Used to change the display name of the user. Will not change if the requested name already exists.
	 * @param newName
	 * @return new display name. Stays same as old name if the requested name is already used.
	 */
	public static String changeDisplayName(String newName)
	{
		return dispName;
	}

	/** NOT USED FOR FIRST VERSION
	 * Get all maps available.
	 * @return String[][]. Dim 0 is list of String[] arrays, each of which is formated as ["name","description","creator_dispname"]
	 */
	public static String[][] getMapList()
	{
		return new String[][] {{"Name","testmap.txt","ASDF"},{"Name2","Description2","ASDF"}};
	}
	
	
	/** NOT USED FOR FIRST VERSION
	 * downloads the specified map to a temporary folder, and returns the path to that folder as a string
	 * @param MapName
	 * @return path to map file, unless there was an error, in which case it returns -1
	 */
	public static String downloadMap(String MapName)
	{
		
		return null;
	}
	
	
	/** NOT USED FOR FIRST VERSION
	 * uploads a new map to the server
	 * @param MapName
	 * @param MapDescription
	 * @param MapFileLoc
	 * @return true for success, false for failure
	 */
	public static int uploadMap(String MapName, String MapDescription, String MapFileLoc)
	{
		return -1;
	}
	
	
	/** NOT USED FOR FIRST VERSION
	 * Join a game hosted by someone else
	 * @param PlayerName if PlayerName is empty, it is treated as a request for random player. Otherwise requests the player with that display name to be host
	 */
	public static void requestToJoinPlayer(String PlayerName)
	{
		
	}
	
	
	/** NOT USED FOR FIRST VERSION
	 * Request to host a game
	 * @param worldName name of world to host. must be a valid world name
	 * @return status: 1 = success; 0 = bad world name; -1 = connection/other issue
	 */
	public static int hostGame(String worldName)
	{
		return -1;
	}
	
	
	/** NOT USED FOR FIRST VERSION
	 * Check to see if someone has requested to join the game.
	 * @return display name of player, or null if no player has requested yet
	 */
	public static String preGameUpdateHost()
	{
		return null;
	}
	
	/** NOT USED FOR FIRST VERSION
	 * Have the host decide whether or not 
	 * @param accept 1=accept match; 0=deny match
	 * @return true if success, false if fail for whatever reason.
	 */
	public static boolean acceptMatch(int accept)
	{
		return false;
	}
	
	/** NOT USED FOR FIRST VERSION
	 * Check to see if host has accepted the request to join the game.
	 * if joining a story-lvl, and if player lvl<story lvl, it will be denied with a -2 code.
	 * @return -2:not allowed, -1:denial, 0:no status update, 1:accepted, game will begin
	 */
	public static int preGameUpdatePlayer()
	{
		return 0;
	}
	
}
