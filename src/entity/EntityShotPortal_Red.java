package entity;

import main.GamePanel;
import map.Map;
import tile.Tile;
import util.EnumSide;
import util.Resources;

public class EntityShotPortal_Red extends EntityShotPortal_Blue {
	private EntityPlayer player;
	public EntityShotPortal_Red(int dirx, int diry, int startx, int starty, EntityPlayer player) {
		super(dirx, diry, startx, starty, player);
		this.player = player;
		image = Resources.getEntity("ShotPortal_Red");
	}
	public void createNewPortal(Tile t,int tilex, int tiley, EnumSide side){
		if (GamePanel.debug)
			System.out.println("ShotPortal_Red: createNewPortal called!");
		tiley = Map.absPixelsToTiles(tiley);
		tilex = Map.absPixelsToTiles(tilex);
		switch(side){
		case LEFT:
			
			if(t != null && t.isPortalable(EnumSide.LEFT)){
				EntityPortalVert_Red p = new EntityPortalVert_Red();
				p.setY(tiley+16);
				p.setX(tilex);
				p.setMap(map);
				p.setDir(side);
				player.setRedPortal(p);
			}
			break;
		case RIGHT:
			
			if(t != null && t.isPortalable(EnumSide.RIGHT)){
				EntityPortalVert_Red p = new EntityPortalVert_Red();
				p.setY(tiley+16);
				p.setX(tilex+32);
				p.setMap(map);
				p.setDir(side);
				player.setRedPortal(p);
			}
			break;
		case BOTTOM:
			
			if(t != null && t.isPortalable(EnumSide.BOTTOM)){
				EntityPortalHoriz_Red p = new EntityPortalHoriz_Red();
				p.setY(tiley+16);
				p.setX(tilex+16);
				p.setMap(map);
				p.setDir(side);
				player.setRedPortal(p);
			}
			break;
		case TOP:

			if(t != null && t.isPortalable(EnumSide.TOP)){
				EntityPortalHoriz_Red p = new EntityPortalHoriz_Red();
				p.setY(tiley);
				p.setX(tilex);
				p.setMap(map);
				p.setDir(side);
				player.setRedPortal(p);
			}
			break;	
		default:
			if (GamePanel.debug)
				System.out.println("ShotPortal_Red: there was an error!");
		}
	}

}
