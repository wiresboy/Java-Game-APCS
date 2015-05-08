package entity;

import java.awt.Graphics2D;

import util.Resources;

public class EntityPortalHoriz_Red extends Entity implements IEntityPortal_Red,IEntityPortal{
	private IEntityPortal_Blue otherportal;
	private int dir;
	public EntityPortalHoriz_Red(){
		//System.out.println("Creating new horizontal red portal!");
		image = Resources.getEntity("PortalHoriz_Red");
	}
	@Override
	public void update() {}
	public boolean isHorizontal(){return true;}
	public void setOtherPortal(IEntityPortal other){ otherportal = (IEntityPortal_Blue) other; }
	public IEntityPortal getOtherPortal(){return otherportal;}
	public void setDir(int dir){this.dir = dir;}
	public int getDir(){return dir;}
}
