package entity;

import java.awt.Graphics2D;

import util.Resources;

public class EntityPortalHoriz_Blue extends EntityPortalHoriz_Red implements IEntityPortal_Blue{
	private IEntityPortal_Red otherportal;
	public EntityPortalHoriz_Blue(){
		//System.out.println("Creating new horizontal blue portal!");
		image = Resources.getEntity("PortalHoriz_Blue");
	}
	
	public void setOtherPortal(IEntityPortal other){ otherportal = (IEntityPortal_Red) other; }
	public IEntityPortal getOtherPortal(){ return otherportal;}
}
