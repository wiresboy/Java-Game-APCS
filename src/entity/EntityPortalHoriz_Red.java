package entity;

import util.Resources;

public class EntityPortalHoriz_Red extends Entity implements EntityPortal_Red{
	private EntityPortal_Blue otherportal;
	public EntityPortalHoriz_Red(){
		image = Resources.getEntity("PortalHoriz_Red");
	}
	@Override
	public void update() {}
	
	public void setOtherPortal(EntityPortal_Blue other){ otherportal = other; }

}
