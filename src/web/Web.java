package web;
/* Web.java			Brandon John		1/25/2015
 * Used to make internet connections much easier to handle.
 */

import java.io.*;
import java.net.*;

public class Web {
	private final String USER_AGENT = "Mozilla/5.0/fun";
	private static int lastResponseCode=0;
	/**
	 * Downloads file at URL specified
	 * @param URL URL to download
	 * @return path to file that was just downloaded
	 */
	public static String downloadFile(String URL)
	{
		return null;
	}
	
	/**
	 * get contents of url with POST data
	 * @param URL - url to download
	 * @param params - Array of name/data pairs for post data{{"name","data"},{"name2","data2"}}. Data doesn't need to be encoded
	 * @return String of data received
	 */
	public static String post(String URL, String[][] params)
	{
		
		//TODO: convert array into string
		return post(URL, "");
	}
	
	
	/**
	 * get contents of url with preformated POST data
	 * @param URL
	 * @param params encoded POST parameters
	 * @return String of data received. If response code is needed, call LastResponseCode()
	 */
	public static String post(String URL, String params)
	{
		
		return null;
	}
	
	
	
	/**
	 * Get response code of last connection
	 * @return the last response code (e.g. 200)
	 */
	public static int LastResponseCode()
	{
		return lastResponseCode;
	}
	
}
