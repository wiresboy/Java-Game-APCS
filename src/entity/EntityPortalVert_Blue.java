package entity;

import util.Resources;

public class EntityPortalVert_Blue extends EntityPortalVert_Red implements EntityPortal_Blue{
	private EntityPortal_Red otherportal;
	public EntityPortalVert_Blue(){
		image = Resources.getEntity("PortalVert_Blue");
	}
	
	public void setOtherPortal(EntityPortal_Red other){ otherportal = other; }

}
