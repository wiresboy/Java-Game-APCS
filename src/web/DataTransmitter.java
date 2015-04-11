package web;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;



public class DataTransmitter {
	
	private static Encoder b64encoder = Base64.getEncoder();
	private static Decoder b64decoder = Base64.getDecoder();

	private static String columnDelimiter = "*";
	private static String columnDelimiterRegex = "\\*";
	private static String rowDelimiter = ".";
	private static String rowDelimiterRegex = "\\.";
	
	public static String pack2DStringArray(String[][] data)
	{
		return toCSV(data);
	}
	public static String[][] Unpack2DStringArray(String data)
	{
		return fromCSV(data);
	}
	
	/**
	 * Makes a CSV (like?) encoding of a 2D array. b64 encodes each parameter so that the data doesn't accidentally include delimiter characters.
	 * Delimiters: Row = "*" instead of line break (uses row&column delimiter constants, these may change)
	 * 			   Column = "." instead of comma
	 * This method allows for transmission of a 2D string array over an HTTP request easily.
	 * @param data data to CSV encode, array of String[] objects, each of which represents a key-value(s) pair.
	 * @return
	 */
	private static String toCSV(String[][] data)
	{
		String CSV = "";
		for (String[] line : data)
		{
			for (String item : line)
			{
				CSV += encode(item) + columnDelimiter;//add item and column terminator
			}
			CSV = CSV.substring(0, CSV.length()-2);
			CSV+=rowDelimiter;//add line break
		}
		//TODO: Will we need to retrun the line break from the end of the string?
		CSV = CSV.substring(0, CSV.length()-2);
		
		return CSV;
	}
	/**
	 * Decodes our custom CSV encoding. fromCSV(toCSV(data)) will return an exact copy of data.
	 * @param data
	 * @return 
	 */
	private static String[][] fromCSV(String data)
	{
		ArrayList<String[]> decodedList = new ArrayList<String[]>();
		String[] rows = data.split(columnDelimiterRegex); //split data into it's rows

		System.out.println("\n\nrows1:");
		printArray(rows);
		System.out.println("\nrows length:"+rows.length);
		
		for (String row : rows)
		{
			System.out.println("\n"+row+" split:");
			printArray(row.split(rowDelimiterRegex));//needs \ to escape . being a wildcard.
			decodedList.add(row.split(rowDelimiterRegex));//split row into columns and append to list.
			//TODO: Decode each element of the array!
		}
		
		return (String[][]) decodedList.toArray();
	}
	
	
	/**
	 * Encodes a string with b64 encodeing. The two extra characters are the "-" and "_", instead of the "+" and "/"
	 * @param a string to encode in b64
	 * @return
	 */
	private static String encode(String a)
	{
		return byteArrayToString(b64encoder.encode(stringToByteArray(a))).replace("+", "-").replace("/", "_");
		
		
	}
	/**
	 * Decodes a string encoded with b64- but with "-" and "_", instead of "+" and "/"
	 * @param a the string to encode
	 * @return
	 */
	private static String decode(String a)
	{
		return byteArrayToString(b64decoder.decode(stringToByteArray(a.replace("-", "+").replace("_", "/"))));
	}
	
	
	private static String byteArrayToString(byte[] array)
	{
		return new String(array);
	}
	private static byte[] stringToByteArray(String s)
	{
		return s.getBytes();
	}
	
	
	private static void printArray(String[] a)
	{
		for (String b:a)
			System.out.println(b+", ");
	}
}
