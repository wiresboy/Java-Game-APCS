package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.awt.image.BufferedImage;

import tile.AnimatedTile;
import tile.Tile;

public class Map {
	private int[][] mapdata;
	private Tile[][] tiles;
	private ArrayList<AnimatedTile> animatedTiles;
	public static final BufferedImage[] greenTiles = new SpriteMap("tiles\\green.png",3,4).getSprites(),
			blackTiles = new SpriteMap("tiles\\black.png",3,4).getSprites(),
			whiteTiles = new SpriteMap("tiles\\white.png",3,4).getSprites(),
			pipes = new SpriteMap("tiles\\pipes.png",3,3).getSprites(),
			nonSolids = new SpriteMap("tiles\\unsolid.png",3,3).getSprites(),
			platforms = new SpriteMap("tiles\\platform.png",3,4).getSprites(),
			misc = new SpriteMap("tiles\\misc.png",2,3).getSprites();
	public static final BufferedImage error = misc[0];
	private int mapOffset = 0;//offset of map in pixels
	private int screenWidth;//pixels
	private int screenWidthBlocks;//width in blocks
	private final int mapWidth = 256*16;//pixels
	
	public Map(String filename, int _screenWidth){
		screenWidth = _screenWidth;
		animatedTiles = new ArrayList<AnimatedTile>();
		tiles = new Tile[32][256];
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
		
		loadTiles();
		// DONE!
	}
	
	private void loadTiles(){
		for(int r = 0; r < mapdata.length; r++){
			for(int c = 0; c < mapdata[0].length; c++){
				int x = c*16;
				int y = r*16;
				Tile tile = new Tile(x,y,error);
				int id = mapdata[r][c];
				if(id != 0){
					switch(id){
					// tiles 1 - 12 = white tiles
					case 1:  // ┌
					case 2:  // ─ top
					case 3:  // ┐
					case 4:  // computer
					case 5:  // │ left
					case 6:  // blank
					case 7:  // │ right
					case 8:  // large vent
					case 9:  // └
					case 10: // ─ bottom
					case 11: // ┘
					case 12: // small vent
						tile = new Tile(x,y,whiteTiles[id-1]);
						break;
					// tiles 13 - 24 = green tiles
					case 13:  // ┌
					case 14:  // ─ top
					case 15:  // ┐
					case 16:  // computer
					case 17:  // │ left
					case 18:  // blank
					case 19:  // │ right
					case 20:  // large vent
					case 21:  // └
					case 22: // ─ bottom
					case 23: // ┘
					case 24: // small vent
						tile = new Tile(x,y,greenTiles[id-13]);
						break;
					// tiles 25 - 36 = black tiles
					case 25: // ┌
					case 26: // ─ top
					case 27: // ┐
					case 28: // computer
					case 29: // │ left
					case 30: // blank
					case 31: // │ right
					case 32: // large vent
					case 33: // └
					case 34: // ─ bottom
					case 35: // ┘
					case 36: // small vent
						tile = new Tile(x,y,blackTiles[id-25]);
						break;
					// tiles 37 - 48 = platform tiles
					case 37: // white left
					case 38: // white middle
					case 39: // white right
					case 40: // tall piston top
					case 41: // metal left
					case 42: // metal middle
					case 43: // metal right
					case 44: // tall piston middle
					case 45: // short piston
					case 46: // null
					case 47: // null
					case 48: // tall piston base
						if(id != 46 && id != 47)
							tile = new Tile(x,y,platforms[id-37]);
						break;
						// tiles 49 - 57 = unsolid tiles
					case 49: // ceiling lamp
					case 50: // null
					case 51: // null
					case 52: // null
					case 53: // null
					case 54: // null
					case 55: // null
					case 56: // null
					case 57: // null
						if(id == 49)
							tile = new Tile(x,y,nonSolids[id-49]);
						break;
						// tiles 58 - 63 = misc tiles
					case 58: // error tile
					case 59: // wip tile
					case 60: // invisible tile
					case 61: // spikes
					case 62: // null
					case 63: // null
						if(id < 62)
							tile = new Tile(x,y,misc[id-58]);
						break;
						// tiles 64 - 72 = pipes
					case 64: // pipes 1
					case 65: // pipes 2
					case 66: // null
					case 67: // null
					case 68: // null
					case 69: // null
					case 70: // null
					case 71: // null
					case 72: // null
						if(id < 66)
							tile = new Tile(x,y,pipes[id-65]);
						break;
					case 73: // animated scrolling computer screen
						tile = new AnimatedTile(x,y,0,new BufferedImage[][] {new SpriteMap("tiles\\animated\\computer.png",4,1).getSprites()});
						animatedTiles.add((AnimatedTile)tile);
						break;
					case 74: // animated graph thing
						tile = new AnimatedTile(x,y,0,new BufferedImage[][] {new SpriteMap("tiles\\grapher.png",4,1).getSprites()});
						animatedTiles.add((AnimatedTile)tile);
						break;
					case 75: // animated red field thing
						tile = new AnimatedTile(x,y,0,new BufferedImage[][] {new SpriteMap("tiles\\red_field.png",4,1).getSprites()});
						animatedTiles.add((AnimatedTile)tile);
						break;
					default: //just in case none of those caught it:
						tile = new Tile(x,y,whiteTiles[5]);
						break;
					}
					tiles[r][c] = tile;
				}
			}
		}
	}

	/**
	 * Draws the map, but only the tiles which will be shown on the screen.
	 * @param g graphics object to use
	 */
	public void draw(Graphics2D g){
		int rowMin = (mapOffset/16);//where can we start drawing the images?
		int rowMax = rowMin+screenWidthBlocks+1;//add 1 so that the blocks on the very right are still drawn
		rowMax = Math.min(rowMax,tiles.length);//don't accidentally try to access blocks that don't exist, so limit rowMax to the map's width.
		
		for(int r = rowMin; r < rowMax; r++){
			for(int c = 0; c < tiles[0].length; c++){
				tiles[r][c].draw(g, mapOffset);
			}
		}	
		
	}
}
