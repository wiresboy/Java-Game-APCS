package util;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import main.GamePanel;
/**
 * 
 * @author Lucas Rezac
 *
 */
public class Resources{
	public static Texture getTile(String name){
		if (GamePanel.debug)
			System.out.println("Getting tile for "+name);
		return new Texture(ImageManipulator.loadImage("tiles\\tiles\\"+name+".png"));
	}
	public static Texture getOverlay(String name){
		if (GamePanel.debug)
			System.out.println("Getting overlay for "+name);
		return new Texture(ImageManipulator.loadImage("tiles\\overlays\\"+name+".png"));
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
	public static Texture getPlayer(String name){
		if (GamePanel.debug)
			System.out.println("Getting player image for "+name);
		return new Texture(ImageManipulator.loadImage("player\\"+name+".png"));
	}
	public static Texture getEntity(String name){
		if (GamePanel.debug)
			System.out.println("Getting entity image for "+name);
		return new Texture(ImageManipulator.loadImage("entities\\"+name+".png"));
	}
	public static Texture getError(){ 
		return new Texture(ImageManipulator.loadImage("error.png"));
	}
}
