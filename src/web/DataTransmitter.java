package web;

/*import java.util.Base64;//unused since we have Base64Coder now.
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;*/
import web.Base64Coder;//my modification uses our -_ instead of +/
//TODO: Find a replacement for Base64: http://www.source-code.biz/base64coder/java/Base64Coder.java.txt ?


public class DataTransmitter {
	
	//private static Encoder b64encoder = Base64.getEncoder();
	//private static Decoder b64decoder = Base64.getDecoder();

	private static String columnDelimiter = ".";
	private static String columnDelimiterRegex = "\\.";
	private static String rowDelimiter = "~";
	private static String rowDelimiterRegex = "\\~";
	
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
			if (line != null)
			{
				for (String item : line)
				{
					CSV += encode(item) + columnDelimiter;//add item and column terminator
				}
				CSV = CSV.substring(0, CSV.length()-1);//remove final column terminator.
				CSV+=rowDelimiter;//add line break
			}
		}
		//TODO: Will we need to return the line break from the end of the string?
		CSV = CSV.substring(0, CSV.length()-1);
		
		return CSV;
	}
	/**
	 * Decodes our custom CSV encoding. fromCSV(toCSV(data)) will return an exact copy of data.
	 * @param data
	 * @return 
	 */
	private static String[][] fromCSV(String data)
	{
		String[][] output = null;
		String[] rows = data.split(rowDelimiterRegex); //split data into it's rows
		
		output = new String[rows.length][];
		
		/*
		System.out.println("rows1:");
		printArray(rows);
		System.out.println("\nrows length:"+rows.length);//*/
		
		for (int rowIndex = 0; rowIndex<rows.length; rowIndex++)
		{
			output[rowIndex] = rows[rowIndex].split(columnDelimiterRegex);//split row into columns and append to list.
			for (int colIndex=0; colIndex < output[rowIndex].length; colIndex++)
				output[rowIndex][colIndex] = decode(output[rowIndex][colIndex]);
		}
		
		return output;
	}
	
	
	/**
	 * Encodes a string with b64 encodeing. The two extra characters are the "-" and "_", instead of the "+" and "/"
	 * @param a string to encode in b64
	 * @return
	 */
	private static String encode(String a)
	{
		return Base64Coder.encodeString(a);
		//return byteArrayToString(b64encoder.encode(stringToByteArray(a))).replace("+", "-").replace("/", "_");
	}
	/**
	 * Decodes a string encoded with b64- but with "-" and "_", instead of "+" and "/"
	 * @param a the string to encode
	 * @return
	 */
	private static String decode(String a)
	{
		return Base64Coder.decodeString(a);
		//return byteArrayToString(b64decoder.decode(stringToByteArray(a.replace("-", "+").replace("_", "/"))));
	}
	
	
	/*//now unneeded
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
		String toPrint = "";
		for (String b:a)
			toPrint+= b+" | ";
		System.out.println(toPrint.substring(0, toPrint.length()-3));
	}
	*/
}

