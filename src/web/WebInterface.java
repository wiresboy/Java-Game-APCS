package web;
/* WebInterface.java		Brandon John		1/25/2015
 * Main class for communicating with server
 * 
 */

import java.util.ArrayList;

import entity.*;


public class WebInterface {
	private static final String baseURL = "http://scoutgear.x10.mx/apcs/";
	private static String username;
	private static String password;
	private static String dispName;
	private static String mapName;//name of map that is being hosted

	private static ArrayList<Shareable> toSend = new ArrayList<Shareable>();
	private static ArrayList<Shareable> toReceive = new ArrayList<Shareable>();
	
	/**
	 * initialize the web connection, make sure that everything is ready.
	 * 
	 * @return true if successfully initialized, false if no Internet connection.
	 */
	public static boolean init()
	{
		
		return false;
	}
	
	public static void addItemToSend(Shareable item)
	{
		toSend.add(item);
	}
	
	
	/**
	 * 
	 * @param username probably email address
	 * @param password duh
	 * @return "display name", unless bad credentials, then null 
	 */
	public static String logIn(String user, String pass)
	{
		username = user;
		password = pass;
		dispName = "Test";
		return null;
	}
	
	/**
	 * Used to change the display name of the user. Will not change if the requested name already exists.
	 * @param newName
	 * @return new display name. Stays same as old name if the requested name is already used.
	 */
	public static String changeDisplayName(String newName)
	{
		//TODO: Implement changeDisplayName
		return dispName;
	}
	
	/**
	 * Updates player status and gets other player's status
	 * @param p player being controlled by this computer
	 * @return player- other player
	 */
	public static Player updatePlayerStatus(Player p)
	{
		//TODO: figure out what data
		//TODO: Also get other data from server on match status like if it is still running, etc. How will this be returned?
		
		return p;//should return OTHER players data.
	}
	
	/**
	 * Get all maps available.
	 * @return String[][]. Dim 0 is list of String[] arrays, each of which is formated as ["name","description","creator_dispname"]
	 */
	public static String[][] getMapList()
	{
		return new String[][] {{"Name","Description"},{"Name2","Description2"}};
	}
	
	
	/**
	 * downloads the specified map to a temporary folder, and returns the path to that folder as a string
	 * @param MapName
	 * @return path to map file, unless there was an error, in which case it returns -1
	 */
	public static String downloadMap(String MapName)
	{
		return null;
	}
	
	
	/**
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
	
	
	/**
	 * Join a game hosted by someone else
	 * @param PlayerName if PlayerName is empty, it is treated as a request for random player. Otherwise requests the player with that display name to be host
	 */
	public static void requestToJoinPlayer(String PlayerName)
	{
		
	}
	
	
	/**
	 * Request to host a game
	 * @param worldName name of world to host. must be a valid world name
	 * @return status: 1 = success; 0 = bad world name; -1 = connection/other issue
	 */
	public static int hostGame(String worldName)
	{
		return -1;
	}
	
	
	/**
	 * Check to see if someone has requested to join the game.
	 * @return display name of player, or null if no player has requested yet
	 */
	public static String preGameUpdateHost()
	{
		return null;
	}
	
	/**
	 * Have the host decide whether or not 
	 * @param accept 1=accept match; 0=deny match
	 * @return true if success, false if fail for whatever reason.
	 */
	public static boolean acceptMatch(int accept)
	{
		return false;
	}
	
	
	/**
	 * Check to see if host has accepted the request to join the game.
	 * if joining a story-lvl, and if player lvl<story lvl, it will be denied with a -2 code.
	 * @return -2:not allowed, -1:denial, 0:no status update, 1:accepted, game will begin
	 */
	public static int preGameUpdatePlayer()
	{
		return 0;
	}
	
	
}
