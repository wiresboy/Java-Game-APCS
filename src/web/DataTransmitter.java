package web;

import java.util.Base64;
import java.util.Base64.*;


public class DataTransmitter {
	
	private static Encoder b64encoder = Base64.getEncoder();
	private static Decoder b64decoder = Base64.getDecoder();
	
	
	public static String pack2DStringArray(String[][] data)
	{
		return encode(toCSV(data));
	}
	public static String[][] Unpack2DStringArray(String data)
	{
		return fromCSV(decode(data));
	}
	
	
	private static String toCSV(String[][] data)
	{
		return null;
	}
	private static String[][] fromCSV(String data)
	{
		return null;
	}
	
	
	private static String encode(String a)
	{
		return byteArrayToString(b64encoder.encode(stringToByteArray(a)));
	}
	private static String decode(String a)
	{
		return byteArrayToString(b64decoder.decode(stringToByteArray(a)));
	}
	
	
	private static String byteArrayToString(byte[] array)
	{
		return new String(array);
	}
	private static byte[] stringToByteArray(String s)
	{
		return s.getBytes();
	}
}
