package entity;

import map.Map;
import tile.Tile;
import util.Resources;

public class EntityShotPortal_Red extends EntityShotPortal_Blue {
	private EntityPlayer player;
	public EntityShotPortal_Red(int dirx, int diry, int startx, int starty, EntityPlayer player) {
		super(dirx, diry, startx, starty, player);
		this.player = player;
		image = Resources.getEntity("ShotPortal_Red");
	}
	public void createNewPortal(int tilex, int tiley, int side){
		switch(side){
		case Tile.LEFT:
			int x = Map.pixelsToTiles(tilex);
			int y = Map.pixelsToTiles(tiley+16);
			Tile t= map.getTile(y,x);
			if(t != null && t.isPortalable(Tile.LEFT)){
				EntityPortalVert_Red p = new EntityPortalVert_Red();
				p.setY(tiley);
				p.setX(tilex-2);
				p.setMap(map);
				player.setRedPortal(p);
			}
			break;
		case Tile.RIGHT:
			x = Map.pixelsToTiles(tilex);
			y = Map.pixelsToTiles(tiley+16);
			t = map.getTile(y, x);
			if(t != null && t.isPortalable(Tile.RIGHT)){
				EntityPortalVert_Red p = new EntityPortalVert_Red();
				p.setY(tiley);
				p.setX(tilex+16+2);
				p.setMap(map);
				player.setRedPortal(p);
			}
			break;
		case Tile.BOTTOM:
			x = Map.pixelsToTiles(tilex+16);
			y = Map.pixelsToTiles(tiley);
			t = map.getTile(y,x);
			if(t != null && t.isPortalable(Tile.BOTTOM)){
				EntityPortalHoriz_Red p = new EntityPortalHoriz_Red();
				p.setY(tiley+16+2);
				p.setX(tilex+16);
				p.setMap(map);
				player.setRedPortal(p);
			}
			break;
		case Tile.TOP:
			x = Map.pixelsToTiles(tilex+16);
			y = Map.pixelsToTiles(tiley);
			t = map.getTile(y, x);
			if(t != null && t.isPortalable(Tile.TOP)){
				EntityPortalHoriz_Red p = new EntityPortalHoriz_Red();
				p.setY(tiley-2);
				p.setX(tilex);
				p.setMap(map);
				player.setRedPortal(p);
			}
			break;	
		}
	}

}
