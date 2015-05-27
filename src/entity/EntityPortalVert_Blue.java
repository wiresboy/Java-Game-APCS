package entity;

import java.awt.Color;

import main.GamePanel;
import util.Resources;

public class EntityPortalVert_Blue extends EntityPortalVert_Red implements IEntityPortal_Blue{
	private IEntityPortal_Red otherportal;
	public EntityPortalVert_Blue(){
		if (GamePanel.debug)
			System.out.println("Creating new vertical blue portal!");
		image = Resources.getEntity("PortalVert_Blue");
		otherimage = image.replaceColors(new Color(image.getRGB(0,0)),purple);
	}
	
	public void setOtherPortal(IEntityPortal other){ otherportal = (IEntityPortal_Red) other; }
	public IEntityPortal getOtherPortal(){return otherportal;}
}
