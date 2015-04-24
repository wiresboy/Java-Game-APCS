package tile;
/**
 * 
 * @author Lucas Rezac
 *
 */
public class TileRegistry{
	private static Tile[] tiles = new Tile[256];
	public static void registerTile(Tile t){
		//stores it to the int value of the tile's hexadecimal id code
		tiles[Integer.parseInt(t.getId(),16)] = t;
	}
	public static Tile getTile(String tileId){
		int i = Integer.parseInt(tileId,16);
		if(i < 0 || i > 255){
			return null;
		}
		return tiles[i];
	}
}
