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
	public void createNewPortal(Tile t,int tilex, int tiley, int side){
		System.out.println("ShotPortal_Red: createNewPortal called!");
		tiley = Map.absPixelsToTiles(tiley);
		tilex = Map.absPixelsToTiles(tilex);
		switch(side){
		case Tile.LEFT:
			
			if(t != null && t.isPortalable(Tile.LEFT)){
				EntityPortalVert_Red p = new EntityPortalVert_Red();
				p.setY(tiley+16);
				p.setX(tilex);
				p.setMap(map);
				p.setDir(side);
				player.setRedPortal(p);
			}
			break;
		case Tile.RIGHT:
			
			if(t != null && t.isPortalable(Tile.RIGHT)){
				EntityPortalVert_Red p = new EntityPortalVert_Red();
				p.setY(tiley+16);
				p.setX(tilex+32);
				p.setMap(map);
				p.setDir(side);
				player.setRedPortal(p);
			}
			break;
		case Tile.BOTTOM:
			
			if(t != null && t.isPortalable(Tile.BOTTOM)){
				EntityPortalHoriz_Red p = new EntityPortalHoriz_Red();
				p.setY(tiley+16);
				p.setX(tilex+16);
				p.setMap(map);
				p.setDir(side);
				player.setRedPortal(p);
			}
			break;
		case Tile.TOP:

			if(t != null && t.isPortalable(Tile.TOP)){
				EntityPortalHoriz_Red p = new EntityPortalHoriz_Red();
				p.setY(tiley);
				p.setX(tilex);
				p.setMap(map);
				p.setDir(side);
				player.setRedPortal(p);
			}
			break;	
		default:
			System.out.println("ShotPortal_Blue: there was an error!");
		}
	}

}
