package entity;

import main.GamePanel;
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
		if (GamePanel.debug)
			System.out.println("ShotPortal_Red: createNewPortal called!");
		switch(side){
		case Tile.LEFT:
			
			if(t != null && t.isPortalable(Tile.LEFT)){
				EntityPortalVert_Red p = new EntityPortalVert_Red();
				p.setY(tiley);
				p.setX(tilex);
				p.setMap(map);
				player.setRedPortal(p);
			}
			break;
		case Tile.RIGHT:
			
			if(t != null && t.isPortalable(Tile.RIGHT)){
				EntityPortalVert_Red p = new EntityPortalVert_Red();
				p.setY(tiley);
				p.setX(tilex+16);
				p.setMap(map);
				player.setRedPortal(p);
			}
			break;
		case Tile.BOTTOM:
			
			if(t != null && t.isPortalable(Tile.BOTTOM)){
				EntityPortalHoriz_Red p = new EntityPortalHoriz_Red();
				p.setY(tiley+16);
				p.setX(tilex+16);
				p.setMap(map);
				player.setRedPortal(p);
			}
			break;
		case Tile.TOP:

			if(t != null && t.isPortalable(Tile.TOP)){
				EntityPortalHoriz_Red p = new EntityPortalHoriz_Red();
				p.setY(tiley);
				p.setX(tilex);
				p.setMap(map);
				player.setRedPortal(p);
			}
			break;	
		default:
			if (GamePanel.debug)
				System.out.println("ShotPortal_Red: there was an error!");
		}
	}

}
