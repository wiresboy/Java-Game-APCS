package entity;

import main.GamePanel;
import util.EnumSide;
import util.Resources;

public class EntityPortalVert_Red extends Entity implements IEntityPortal_Red{
	private IEntityPortal_Blue otherportal;
	private EnumSide dir;
	public EntityPortalVert_Red(){
		if (GamePanel.debug)
			System.out.println("Creating new vertical red portal!");
		image = Resources.getEntity("PortalVert_Red");
	}
	@Override
	public void update() {}
	public boolean isHorizontal(){return false;}
	public void setOtherPortal(IEntityPortal other) { otherportal = (IEntityPortal_Blue) other; }
	public IEntityPortal getOtherPortal(){return otherportal;}
	public void setDir(EnumSide dir){this.dir = dir;}
	public EnumSide getDir(){return dir;}
}
