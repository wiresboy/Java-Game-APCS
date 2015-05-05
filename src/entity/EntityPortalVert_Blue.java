package entity;

import java.awt.Graphics2D;

import util.Resources;

public class EntityPortalVert_Blue extends EntityPortalVert_Red implements IEntityPortal_Blue{
	private IEntityPortal_Red otherportal;
	public EntityPortalVert_Blue(){
		System.out.println("Creating new vertical blue portal!");
		image = Resources.getEntity("PortalVert_Blue");
	}
	
	public void setOtherPortal(IEntityPortal other){ otherportal = (IEntityPortal_Red) other; }
	public IEntityPortal getOtherPortal(){return otherportal;}
}
