package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Map {
	private int[][] mapdata;
	public Map(String filename){
		loadMap(filename);
	}
	private void loadMap(String filename){
		String dir = System.getProperty("user.dir");
		File f = new File(dir+"\\mapdata\\"+filename+".txt");
		ArrayList<String> lines = new ArrayList<String>();
		Scanner scan = null;
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) { System.err.println("Error: file not found.");System.exit(0); }
		
		while(scan.hasNext())
			lines.add(scan.next());
		
		// every map is 32 tiles tall, 256 tiles wide, although that doesn't mean you have to use every
		// single one of them in a level.
		mapdata = new int[32][256];
		
		for(int r = 0; r < 32; r++){
			try{
				String line = lines.get(r);
				ArrayList<String> values = new ArrayList<String>();
				scan = new Scanner(line);
				scan.useDelimiter(",");
				while(scan.hasNext())
					values.add(scan.next());
				Integer[] temp = (Integer[])values.toArray();
				int[] array = new int[256];
				int i;
				for(i = 0; i < temp.length; i++)
					array[i] = temp[i];
				if(i < array.length)
					for(; i < array.length; i++)
						array[i] = 0;
				mapdata[r] = array;
			}catch(IndexOutOfBoundsException e){
				int[] array = new int[256];
				for(int i = 0; i < array.length; i++)
					array[i] = 0;
				mapdata[r] = array;
			}// end try/catch
		}// end for
		
		scan.close();
		
		// DONE!
	}
	public void draw(Graphics2D g){
		for(int r = 0; r < mapdata.length; r++){
			for(int c = 0; c < mapdata[0].length; c++){
				int x = c*16;
				int y = r*16;
				BufferedImage img;
				int id = mapdata[r][c];
				if(id != 0){
					switch(id){
						
					}
				}
			}
		}
	}
}
