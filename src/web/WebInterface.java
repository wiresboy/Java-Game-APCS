package web;
/* WebInterface.java		Brandon John		1/25/2015
 * Main class for communicating with server
 * 
 */

import entity.*;


public class WebInterface {
	private static final String baseURL = "http://scoutgear.x10.mx/apcs/";
	private static String username;
	private static String password;
	private static String dispName;
	
	/**
	 * @return true if successfully initialized, false if no internet connection.
	 */
	public static boolean init()
	{
		
		return false;
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
	 * Used to change the display name of the user
	 * @param newName
	 * @return new display name. This is returned incase the display name had illegal chars that were removed.
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
		return p;//should return OTHER players data.
	}
	
	/**
	 * Get maps available
	 * @return String[][]. Dim 0 is list of String[] arrays, each of which is formated as ["name","description"]
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
	
	
}
