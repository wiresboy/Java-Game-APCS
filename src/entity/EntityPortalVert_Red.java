package entity;

import java.awt.Graphics2D;

import util.Resources;

public class EntityPortalVert_Red extends Entity implements IEntityPortal_Red,IEntityPortal{
	private IEntityPortal_Blue otherportal;
	public EntityPortalVert_Red(){
		System.out.println("Creating new vertical red portal!");
		image = Resources.getEntity("PortalVert_Red");
	}
	@Override
	public void update() {}
	public boolean isHorizontal(){return false;}
	public void setOtherPortal(IEntityPortal other) { otherportal = (IEntityPortal_Blue) other; }
	public IEntityPortal getOtherPortal(){return otherportal;}
}
