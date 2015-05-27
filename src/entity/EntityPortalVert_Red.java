package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import util.EnumSide;
import util.Resources;
import util.Texture;

public class EntityPortalVert_Red extends Entity implements IEntityPortal_Red{
	private IEntityPortal_Blue otherportal;
	private EnumSide dir;
	protected Texture otherimage;
	public EntityPortalVert_Red(){
		if (GamePanel.debug)
			System.out.println("Creating new vertical red portal!");
		image = Resources.getEntity("PortalVert_Red");
		
		otherimage = image.replaceColors(new Color(image.getRGB(0,0)),green);
	}
	public void drawOtherColor(Graphics2D g){
		Texture temp = image;
		image = otherimage;
		super.draw(g);
		image = temp;
	}
	@Override
	public void update() {}
	public boolean isHorizontal(){return false;}
	public void setOtherPortal(IEntityPortal other) { otherportal = (IEntityPortal_Blue) other; }
	public IEntityPortal getOtherPortal(){return otherportal;}
	public void setDir(EnumSide dir){this.dir = dir;}
	public EnumSide getDir(){return dir;}
}
