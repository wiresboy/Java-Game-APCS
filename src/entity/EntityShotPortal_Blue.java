package entity;

import main.GamePanel;
import map.Map;
import tile.Tile;
import util.EnumSide;
import util.Resources;

public class EntityShotPortal_Blue extends Entity{
	private int startx, starty;
	private EntityPlayer player;
	private double angle;//measured in radians
	public boolean isDead = false;
	public EntityShotPortal_Blue(int clickx, int clicky, int startx, int starty, EntityPlayer player){
		this.startx=startx;
		this.starty=starty;
		this.player = player;
		this.angle = Math.atan2(clicky-starty, clickx-startx);//multipy y val by -1 since increasing y is downward.
		if (GamePanel.debug)
			System.out.println("start = ("+startx+","+starty+"), click = ("+clickx+","+clicky+"), angle = "+angle+", which is "+Math.toDegrees(angle));
		image = Resources.getEntity("ShotPortal_Blue");
	}
	public void update(){}//we are not drawing this, so update can be ignored.
	
	public boolean go(){
		Object[] values = this.findNextIntersection(startx, starty, angle);
		Tile t = (Tile)values[0];
		EnumSide side = (EnumSide)values[1];
		int x = ((Integer)values[2]).intValue();
		int y  = ((Integer)values[3]).intValue();
		//x = Map.tilesToPixels(x);
		//y = Map.tilesToPixels(y);
		/*if (intersect!=null)
		{
			//System.out.println("Found a block collision, lets make a portal");
			int x = intersect[0];
			int y = intersect[1];
			int side = intersect[2];*/
			//Tile t = map.getTile(y, x);//row, column still confuses me... columns = x Yes.
			if (t!=null && t.isPortalable(side)){
				createNewPortal(t,x,y,side);
				return true;
			}else{
				if (GamePanel.debug)
					System.out.println("Not creating portal since it isn't tileable!");
				return false;
				
			
		}
	}
	
	public void createNewPortal(Tile t, int tilex, int tiley, EnumSide side){
		if (GamePanel.debug)
			System.out.println("ShotPortal_Blue: createNewPortal called!");
		tiley = Map.absPixelsToTiles(tiley);
		tilex = Map.absPixelsToTiles(tilex);
		switch(side){
		case LEFT:
			
			if(t != null && t.isPortalable(EnumSide.LEFT)){
				EntityPortalVert_Blue p = new EntityPortalVert_Blue();
				p.setY(tiley+16);
				p.setX(tilex);
				p.setMap(map);
				p.setDir(side);
				player.setBluePortal(p);
			}
			break;
		case RIGHT:
			
			if(t != null && t.isPortalable(EnumSide.RIGHT)){
				EntityPortalVert_Blue p = new EntityPortalVert_Blue();
				p.setY(tiley+16);
				p.setX(tilex+32);
				p.setMap(map);
				p.setDir(side);
				player.setBluePortal(p);
			}
			break;
		case BOTTOM:
			
			if(t != null && t.isPortalable(EnumSide.BOTTOM)){
				EntityPortalHoriz_Blue p = new EntityPortalHoriz_Blue();
				p.setY(tiley+16);
				p.setX(tilex+16);
				p.setMap(map);
				p.setDir(side);
				player.setBluePortal(p);
			}
			break;
		case TOP:
			
			if(t != null && t.isPortalable(EnumSide.TOP)){
				EntityPortalHoriz_Blue p = new EntityPortalHoriz_Blue();
				p.setY(tiley);
				p.setX(tilex);
				p.setMap(map);
				p.setDir(side);
				player.setBluePortal(p);
			}
			break;	
		default:
			if (GamePanel.debug)
				System.out.println("ShotPortal_Blue: there was an error!");
		}
	}
}
