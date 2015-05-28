package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import map.Map;
import tile.Tile;
import util.EnumSide;
import util.Resources;
import util.Texture;

public class EntityPortalHoriz_Red extends Entity implements IEntityPortal_Red{
	private IEntityPortal_Blue otherportal;
	private EnumSide dir;
	protected Texture otherimage;
	public EntityPortalHoriz_Red(){
		if (GamePanel.debug)
			System.out.println("Creating new horizontal red portal!");
		
		image = Resources.getEntity("PortalHoriz_Red");
		otherimage = Resources.getEntity("PortaljoeHoriz_Red").replaceColors(new Color(image.getRGB(0,0)),green );
	}
	public void draw(Graphics2D g){
		super.draw(g);
	}
	public void drawOtherColor(Graphics2D g){
		Texture temp = image;
		image = otherimage;
		super.draw(g);
		image = temp;
	}
	@Override
	public void update() {
		int x = getX();
		int y = getY();
		Tile t= map.getTile(Map.pixelsToTiles(y), Map.pixelsToTiles(x));
		if(t != null){
			y +=16;
			t= map.getTile(Map.pixelsToTiles(y), Map.pixelsToTiles(x));
			if(t != null){
				y-=18;
			}
		}
		setY(y);
	}
	public boolean isHorizontal(){return true;}
	public void setOtherPortal(IEntityPortal other){ otherportal = (IEntityPortal_Blue) other; }
	public IEntityPortal getOtherPortal(){return otherportal;}
	public void setDir(EnumSide dir){this.dir = dir;}
	public EnumSide getDir(){return dir;}
}
