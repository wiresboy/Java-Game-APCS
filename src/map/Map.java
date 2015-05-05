package map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

import tile.Tile;
import tile.TileRegistry;
import tileentity.TileEntity;
import util.Resources;

public class Map{
	public static final int TILE_SIZE = 16;
    private Tile[][] map;
    private TileEntity[][] tileEntities;
    private int width,height;
    private String mapname;
    public int[] loadMap(String mapname){
    	ArrayList<String> list = Resources.getMap(mapname);
    	this.mapname = mapname;
    	return loadMap(list);
    }
    public int[] loadMap(ArrayList<String> list){
    	int[] playerLoc = new int[]{0,0};
		
		map = new Tile[list.size()][list.get(0).length()/2];
		tileEntities = new TileEntity[list.size()][list.get(0).length()/2];
		System.out.println("list.get(0).length()/2 = "+(list.get(0).length()/2)+";; s.length()-2 = "+(list.get(0).length()-2));
		int row = 0, column = 0;
		for(String s : list){
			column = 0;
			for(int i = 0; i < s.length()-1; i+=2,column++){
				String hex = s.substring(i,i+2);
				if(hex.equals("*1")){
					int x = tilesToPixels(column);
					int y = tilesToPixels(row);
					playerLoc = new int[]{x,y-TILE_SIZE};
				}else{
					Tile t = TileRegistry.getTile(hex);
					if(t != null){
						System.out.println("row = "+row+" column = "+column);
						map[row][column] = t;
						if(t.hasTileEntity()){
							TileEntity te = t.createNewTileEntity();
							tileEntities[row][column] = te;
							int x = Map.tilesToPixels(column);
							int y = Map.tilesToPixels(row);
							te.setLocation(x,y);
							te.setMap(this);
						}	
					}
				}
			}
			row++;
		}
		System.out.println("Loaded map : ");
		for(Tile[] tiles : map){
			System.out.println(Arrays.toString(tiles));
		}
		return playerLoc;
   }
   public TileEntity getTileEntity(int row, int column){
   		return tileEntities[row][column];
   }
   public Tile getTile(int row, int column){
	   if(row < 0 || row >= map.length)return null;
	   if(column < 0 || column >= map[0].length) return null;
	   //System.out.println("Row "+row+" Column "+column);
	   return map[row][column];
   }
   public boolean canSeeThroughTile(int row, int column)
   {
	   //TODO: Make this return true when it sees either an empty block OR a 'background' block. Really anything that can be walked through.
	   return (getTile(row,column)!=null);
   }
   public static int tilesToPixels(int tiles){
	   return tiles*TILE_SIZE;
   }
   public Dimension getPreferredSize(){
	   return new Dimension(map[0].length*32,map.length*32);
   }
   public static int pixelsToTiles(int pixels){
	   return (int)((double)pixels/(double)TILE_SIZE);
   }
   public static int absPixelsToTiles(int pixels){
	   return tilesToPixels(pixelsToTiles(pixels));
   }
   public boolean onMapPixel(int x, int y) // return true if the coordinates
   {
	   return onMapTile(pixelsToTiles(x), pixelsToTiles(y));
   }
   public boolean onMapTile(int x, int y)
   {//possible off by one errors on maximum... not sure how it is working.
	   return ((x>=0) && (y>=0) && (x<=map[0].length) && (y<=map.length));
   }
   public void drawBase(Graphics2D g){
	   // TODO Implement this
	   for(int row = 0; row < map.length; row++){
		   for(int column = 0; column < map[0].length; column++){
			   //int x = tilesToPixels(column);
			   //int y = tilesToPixels(row);
			   Tile t = getTile(row,column);
			   if(t != null){
				   //System.out.println("Drawing "+ t.getClass().getSimpleName());
				   t.draw(g, row, column, this);
			   }
		   }
	   }
   }
   public void draw(Graphics2D g){
	   drawBase(g);
	   drawOverlays(g);
   }
   public void drawOverlays(Graphics2D g){
	   for(int row = 0; row < map.length; row++){
		   for(int column = 0; column < map[0].length; column++){
			   int x = tilesToPixels(column);
			   int y = tilesToPixels(row);
			   Tile t = getTile(row,column);
			   if(t != null){
				   if(t.hasOverlay())
					  // System.out.println("Drawing overlay");
					   t.drawOverlay(g, x, y);
			   }
		   }
	   }
   }
	public String toString(){ 
		return "Map: " + mapname; 
	}
	public String mapName(){	
		return mapname;
	}
	public void reset(){
	   loadMap(mapname);
	}
}

/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.awt.image.BufferedImage;

import tile.AnimatedTile;
import tile.Tile;
import util.SpriteMap;

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
	public Map(String filename){
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
				int[] array = new int[256];
				int i;
				for(i = 0; i < values.size(); i++)
					array[i] = Integer.parseInt(values.get(i));
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
					case 74: // animated graph thing
						tile = new AnimatedTile(x,y,0,new BufferedImage[][] {new SpriteMap("tiles\\grapher.png",4,1).getSprites()});
						animatedTiles.add((AnimatedTile)tile);
					case 75: // animated red field thing
						tile = new AnimatedTile(x,y,0,new BufferedImage[][] {new SpriteMap("tiles\\red_field.png",4,1).getSprites()});
						animatedTiles.add((AnimatedTile)tile);
					}
					tiles[r][c] = tile;
				}else{
					tiles[r][c] = null;
				}
			}
		}
	}
	public void draw(Graphics2D g){
		
		/*for(Tile[] tList : tiles)
			for(Tile t : tList)
				if(t != null)
					t.draw(g);
		
		for(int y = 0; y < tiles.length; y++){
			for(int x = 0; x < tiles[0].length; x++){
				Tile t = getTile(x,y);
				if(t != null)
					t.draw(g);
				else{
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(x*16, y*16, 16, 16);
				}	
			}
		}
		 
	}
	public Tile getTile_Pixel(int pixelX, int pixelY){
		//TODO: make a pixelToTile converter
		return null;
	}
	public Tile getTile(int x, int y){
		return tiles[y][x];
	}
}*/
