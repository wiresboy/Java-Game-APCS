package entity;

import java.awt.Graphics2D;

import main.GamePanel;

import util.Resources;

public class EntityPortalVert_Red extends Entity implements EntityPortal_Red{
	private EntityPortal_Blue otherportal;
	public EntityPortalVert_Red(){
		if (GamePanel.debug)
			System.out.println("Creating new vertical red portal!");
		image = Resources.getEntity("PortalVert_Red");
	}
	@Override
	public void update() {}
	
	public void setOtherPortal(EntityPortal_Blue other) { otherportal = other; }

}
