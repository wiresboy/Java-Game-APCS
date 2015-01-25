package web;
/* WebInterface.java		Brandon John		1/25/2015
 * Main class for connecting to internet
 * 
 */

public class WebInterface {
	private static final String baseURL = "http://scoutgear.x10.mx/apcs/";
	private static String username;
	private static String password;
	
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
		return null;
	}
	
}
