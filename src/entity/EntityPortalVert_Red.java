package entity;

import util.Resources;

public class EntityPortalVert_Red extends Entity implements EntityPortal_Red{
	private EntityPortal_Blue otherportal;
	public EntityPortalVert_Red(){
		image = Resources.getEntity("PortalVert_Red");
	}
	@Override
	public void update() {}
	
	public void setOtherPortal(EntityPortal_Blue other) { otherportal = other; }

}
