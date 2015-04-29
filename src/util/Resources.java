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
		String location = dir+"\\mapdata\\"+filename+".txt";
		if(System.getProperty("os.name").indexOf("Mac") != -1){
			while(location.indexOf("\\") != -1) location = location.replace("\\","/");
		}
		File f = new File(location);
		ArrayList<String> lines = new ArrayList<String>();
		Scanner scan = null;
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) { System.err.println("Error: file not found "+filename);System.exit(0); }
		
		while(scan.hasNext())
			lines.add(scan.next());
		scan.close();
		return lines;
	}
	public static BufferedImage getPlayer(String name){
		System.out.println("Getting player image for "+name);
		return ImageManipulator.loadImage("player\\"+name+".png");
	}
	public static BufferedImage getEntity(String name){
		System.out.println("Getting entity image for "+name);
		return ImageManipulator.loadImage("entities\\"+name+".png");
	}
	public static BufferedImage getError(){ 
		return ImageManipulator.loadImage("error.png");
	}
}
