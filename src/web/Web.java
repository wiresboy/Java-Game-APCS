package web;
/* Web.java			Brandon John		1/25/2015
 * Used to make internet connections much easier to handle.
 */

import java.io.*;
import java.net.*;
import java.util.Random;

import main.GamePanel;

public class Web {
	private static final String USER_AGENT = "Mozilla/5.0/fun";
	private static final String boundary =  "***&°°°&34#7&8*9**";//some string that will never appear in a file being uploaded
	private static int lastResponseCode=0;
	private static Random gen = new Random();
	
	
	/**
	 * Find the most appropriate IP address for accessing the appspot domain.
	 * @param appEngineURL used to set appEngineHost
	 */
	public static void init()
	{
		//eventually this will do something! I promise!
		//can't do until I have a decent amount of time to work on with the schools Internet restrictions.
	}
	
	
	/**
	 * Downloads file at URL specified
	 * @param URL URL to download
	 * @return path to file that was just downloaded
	 */
	public static String downloadFile(String fileURL) throws Exception
	{
		URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
        
        if (GamePanel.debug)
			System.out.println(System.getProperty("user.dir")+"\\.tmp");
        String saveDir = System.getProperty("user.dir")+"\\.tmp";
        if (GamePanel.debug)
			System.out.println(saveDir);
 
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType(); 
            int contentLength = httpConn.getContentLength();
 
            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                        fileURL.length());
            }
            
            if (fileName.equals(""))//was the filename set?
            	fileName = "\\tmp-"+gen.nextInt(10000000);//appened a 7 digit random number to hopefully prevent overlap issues.
            	
            if (GamePanel.debug)
            {
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);
            }
            
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;
             
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
 
            int bytesRead = -1;
            byte[] buffer = new byte[4096];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
 
            outputStream.close();
            inputStream.close();
 
            if (GamePanel.debug)
    			System.out.println("File downloaded and saved at: "+saveFilePath);
            httpConn.disconnect();
            
            return saveFilePath;
            
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            httpConn.disconnect();
            return null;
        }
	}
	
	
	/**
	 * Upload file via PUT. probably will not be used.
	 * @param URL to upload to
	 * @param fileLoc file to upload
	 * @return String response
	 */
	public static String put(String URL, String fileLoc) throws Exception
	{
		HttpURLConnection con = openConnection(URL, "POST");
		con.setRequestProperty("Content-Type", "text/csv");
		con.setDoOutput(true);
		
		OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		
		Reader reader = new FileReader(fileLoc);

		int data = reader.read();
		while(data != -1) {
			out.write(data);
			data = reader.read();
		}
		reader.close();
		
		out.close();
		
		StringBuffer response = bufferedRead(con);
		return response.toString();
	}
	
	/**
	 * Upload a file via the POST method.
	 * @param URL
	 * @param fileLoc
	 * @return String: contents of returned data(empty string if none)
	 */
	public static String postFile(String URL, String fileLoc) throws Exception
	{
		File f = new File(fileLoc);
		
		HttpURLConnection con = openConnection(URL,"POST");
		con.setUseCaches(false);
		con.setDoOutput(true);
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Cache-Control", "no-cache");
		con.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
		
		DataOutputStream request = new DataOutputStream(con.getOutputStream());
		request.writeBytes("--" + boundary + "\r\n");
		request.writeBytes("Content-Disposition: form-data; name=\"" + "file" + "\";filename=\"" + f.getName() + "\"\r\n");
		request.writeBytes("\r\n");
		
		
		Reader reader = new FileReader(f);

		int data = reader.read();
		while(data != -1) {
			request.writeByte(data);
			data = reader.read();
		}
		reader.close();
		
		
		request.writeBytes("\r\n--"+boundary+"--\r\n");//end content wrapper
		
		request.flush();//flush output buffer
		request.close();//and close
		
		return bufferedRead(con).toString();
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
			params_encoded += a[0]+"="+a[1]+"&";
		params_encoded = params_encoded.substring(0, params_encoded.length()-1);
		//System.out.println("Encoded POST parameters are: "+params_encoded);
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
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(params);
		wr.flush();
		wr.close();
		
		lastResponseCode=con.getResponseCode();
		
		//System.out.println("\nSending 'POST' request to URL : " + URL);
		//System.out.println("Post parameters : " + params);
		//System.out.println("Response Code : " + lastResponseCode);
		
		StringBuffer response = bufferedRead(con);
		
		return response.toString();
	}
	
	
	public static String get(String URL, String params) throws Exception
	{
		return get(URL+"?"+params);
	}
	
	
	public static String get(String URL) throws Exception
	{
		HttpURLConnection con = openConnection(URL, "GET");
		
		lastResponseCode=con.getResponseCode();
		
		//System.out.println("\nSending 'GET' request to URL : " + URL);
		//System.out.println("Response Code : " + lastResponseCode);
		
		StringBuffer response = bufferedRead(con);
		
		return response.toString();
	}
	
	
	/**
	 * Get response code of last connection
	 * @return the last response code (e.g. 200)
	 */
	public static int LastResponseCode()
	{
		return lastResponseCode;
	}
	
	
	/**
	 * Open a HttpURLConnection to the URL specified with method specified. If the URL is for appspot.com, this method substitutes
	 * Google's IP address in for the connection opening, and then overrides the Host parameter to whatever was set in the URL.
	 * @param url
	 * @param method
	 * @return HttpURLConnection that can be used.
	 * @throws Exception
	 */
	private static HttpURLConnection openConnection(String url, String method) throws Exception
	{

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod(method);//set PUT, GET or POST method
		con.setRequestProperty("User-Agent", USER_AGENT);//set (fake) User agent
		return con;
	}
	
	/**
	 * Read data response from a URL connection
	 * @param con connection to read from
	 * @return StringBuffer holding contents of response
	 * @throws IOException 
	 */
	private static StringBuffer bufferedRead(HttpURLConnection con) throws IOException
	{
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		//System.out.println("Response: "+response.toString());
		con.disconnect();//disconnect connection
		return response;
	}
	
}
