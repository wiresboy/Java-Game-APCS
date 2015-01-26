package web;
/* Web.java			Brandon John		1/25/2015
 * Used to make internet connections much easier to handle.
 */

import java.io.*;
import java.net.*;

public class Web {
	private static final String USER_AGENT = "Mozilla/5.0/fun";
	private static int lastResponseCode=0;
	
	
	public static boolean init()
	{
		return true;
	}
	
	
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
	public static String post(String URL, String[][] params) throws Exception
	{
		String params_encoded= "";
		for (String[] a:params)
			params_encoded += a[0]+"="+a[1]+",";
		System.out.println("Encoded POST parameters are: "+params_encoded);
		return post(URL, params_encoded);
	}
	
	
	/**
	 * get contents of url with preformated POST data
	 * @param URL
	 * @param params encoded POST parameters
	 * @return String of data received. If response code is needed, call LastResponseCode()
	 */
	public static String post(String URL, String params) throws Exception
	{
		HttpURLConnection con = openConnection(URL, "POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
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
	
	
	private static HttpURLConnection openConnection(String url, String method) throws Exception
	{

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod(method);
		con.setRequestProperty("User-Agent", USER_AGENT);
		return con;
	}
	
}
