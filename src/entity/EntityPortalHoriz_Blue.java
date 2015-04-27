package entity;

import util.Resources;

public class EntityPortalHoriz_Blue extends EntityPortalHoriz_Red implements EntityPortal_Blue{
	private EntityPortal_Red otherportal;
	public EntityPortalHoriz_Blue(){
		image = Resources.getEntity("PortalHoriz_Blue");
	}
	public void setOtherPortal(EntityPortal_Red other){ otherportal = other; }
}
