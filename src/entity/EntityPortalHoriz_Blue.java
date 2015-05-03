package entity;

import java.awt.Graphics2D;

import util.Resources;

public class EntityPortalHoriz_Blue extends EntityPortalHoriz_Red implements EntityPortal_Blue{
	private EntityPortal_Red otherportal;
	public EntityPortalHoriz_Blue(){
		System.out.println("Creating new horizontal blue portal!");
		image = Resources.getEntity("PortalHoriz_Blue");
	}
	
	public void setOtherPortal(EntityPortal_Red other){ otherportal = other; }
}
