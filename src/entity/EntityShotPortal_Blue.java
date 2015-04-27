package entity;

import map.Map;
import tile.Tile;
import util.Resources;

public class EntityShotPortal_Blue extends Entity{
	private int dirx, diry;
	private EntityPlayer player;
	public boolean isDead = false;
	public EntityShotPortal_Blue(int dirx, int diry, EntityPlayer player){
		this.dirx = dirx;
		this.diry = diry;
		this.player = player;
		image = Resources.getEntity("ShotPortal_Blue");
	}
	public void update(){
	
	}
	public void go(){
		System.out.println("ShotPortal: go");
		Tile t;
		int x,y;
		do{
		setX(getX()+dirx);
		setY(getY()+diry);
		x = Map.pixelsToTiles(getX());
		y = Map.pixelsToTiles(getY());
		t = map.getTile(y, x);
		}while(t == null);
		if(t != null){
			int lx = Map.tilesToPixels(x)+4;
			int ty = Map.tilesToPixels(y)+4;
			int rx = lx+8;
			int by = ty+8;
			if(t.boundingBox(lx-4, ty-4,map) != null)
			if(dirx > 0 && diry < 0){
				if(getX() <= lx+4){
					//LEFT SIDE
					if(t.isPortalable(Tile.LEFT)){
						createNewPortal(getX(),getY(),Tile.LEFT);
					}
				}else{
					//BOTTOM SIDE
					if(t.isPortalable(Tile.BOTTOM)){
						createNewPortal(getX(),getY(),Tile.BOTTOM);
					}
				}
			}else if(dirx == 0){
				if(diry < 0){
					//BOTTOM SIDE
					if(t.isPortalable(Tile.BOTTOM)){
						createNewPortal(getX(),getY(),Tile.BOTTOM);
					}
				}else{
					//TOP SIDE
					if(t.isPortalable(Tile.TOP)){
						createNewPortal(getX(),getY(),Tile.TOP);
					}
				}
			}else if(diry == 0){
				if(dirx < 0){
					//LEFT SIDE
					if(t.isPortalable(Tile.LEFT)){
						createNewPortal(getX(),getY(),Tile.LEFT);
					}
				}else{
					//RIGHT SIDE
					if(t.isPortalable(Tile.RIGHT)){
						createNewPortal(getX(),getY(),Tile.RIGHT);
					}
				}
			}else if(dirx > 0 &&  diry > 0){
				if(getX() >= lx-4){
					//LEFT SIDE
					if(t.isPortalable(Tile.LEFT)){
						createNewPortal(getX(),getY(),Tile.LEFT);
					}
				}else{
					//TOP SIDE
					if(t.isPortalable(Tile.TOP)){
						createNewPortal(getX(),getY(),Tile.TOP);
					}
				}
			
			}else if(dirx < 0 && diry < 0){
				if(getX() >= rx+4){
					//RIGHT SIDE
					if(t.isPortalable(Tile.RIGHT)){
						createNewPortal(getX(),getY(),Tile.RIGHT);
					}
				}else{
					//BOTTOM SIDE
					if(t.isPortalable(Tile.BOTTOM)){
						createNewPortal(getX(),getY(),Tile.BOTTOM);
					}
				}
			}else if(dirx < 0 && diry > 0){
				if(getX() >= rx+4){
					//RIGHT SIDE
					if(t.isPortalable(Tile.RIGHT)){
						createNewPortal(getX(),getY(),Tile.RIGHT);
					}
				}else{
					//TOP SIDE
					if(t.isPortalable(Tile.TOP)){
						createNewPortal(getX(),getY(),Tile.TOP);
					}
				}
			}
		}
	}
	public void createNewPortal(int tilex, int tiley, int side){
		switch(side){
		case Tile.LEFT:
			int x = Map.pixelsToTiles(tilex);
			int y = Map.pixelsToTiles(tiley+16);
			Tile t= map.getTile(y,x);
			if(t != null && t.isPortalable(Tile.LEFT)){
				EntityPortalVert_Blue p = new EntityPortalVert_Blue();
				p.setY(tiley);
				p.setX(tilex-2);
				p.setMap(map);
				player.setBluePortal(p);
			}
			break;
		case Tile.RIGHT:
			x = Map.pixelsToTiles(tilex);
			y = Map.pixelsToTiles(tiley+16);
			t = map.getTile(y, x);
			if(t != null && t.isPortalable(Tile.RIGHT)){
				EntityPortalVert_Blue p = new EntityPortalVert_Blue();
				p.setY(tiley);
				p.setX(tilex+16+2);
				p.setMap(map);
				player.setBluePortal(p);
			}
			break;
		case Tile.BOTTOM:
			x = Map.pixelsToTiles(tilex+16);
			y = Map.pixelsToTiles(tiley);
			t = map.getTile(y,x);
			if(t != null && t.isPortalable(Tile.BOTTOM)){
				EntityPortalHoriz_Blue p = new EntityPortalHoriz_Blue();
				p.setY(tiley+16+2);
				p.setX(tilex+16);
				p.setMap(map);
				player.setBluePortal(p);
			}
			break;
		case Tile.TOP:
			x = Map.pixelsToTiles(tilex+16);
			y = Map.pixelsToTiles(tiley);
			t = map.getTile(y, x);
			if(t != null && t.isPortalable(Tile.TOP)){
				EntityPortalHoriz_Blue p = new EntityPortalHoriz_Blue();
				p.setY(tiley-2);
				p.setX(tilex);
				p.setMap(map);
				player.setBluePortal(p);
			}
			break;	
		}
	}
}
