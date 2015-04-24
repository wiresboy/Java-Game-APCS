package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Lucas Rezac
 *
 */
public class Resources{
	public static BufferedImage getTile(String name){
		System.out.println("Getting tile for "+name);
		return ImageManipulator.loadImage("tiles\\tiles\\"+name+".png");
	}
	public static BufferedImage getOverlay(String name){
		System.out.println("Getting overlay for "+name);
		return ImageManipulator.loadImage("tiles\\overlays\\"+name+".png");
	}
	public static ArrayList<String> getMap(String filename){
		String dir = System.getProperty("user.dir");
		File f = new File(dir+"\\mapdata\\"+filename+".txt");
		ArrayList<String> lines = new ArrayList<String>();
		Scanner scan = null;
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) { System.err.println("Error: file not found.");System.exit(0); }
		
		while(scan.hasNext())
			lines.add(scan.next());
		scan.close();
		return lines;
	}
}
