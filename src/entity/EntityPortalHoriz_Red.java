package entity;

import java.awt.Graphics2D;

import main.GamePanel;

import util.Resources;

public class EntityPortalHoriz_Red extends Entity implements EntityPortal_Red{
	private EntityPortal_Blue otherportal;
	public EntityPortalHoriz_Red(){
		if (GamePanel.debug)
			System.out.println("Creating new horizontal red portal!");
		image = Resources.getEntity("PortalHoriz_Red");
	}
	@Override
	public void update() {}
	
	public void setOtherPortal(EntityPortal_Blue other){ otherportal = other; }

}
