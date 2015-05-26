package entity;

import java.awt.Graphics2D;

import util.EnumSide;

public interface IEntityPortal extends IEntity{
	//void draw(Graphics2D g);
	//int getX();
	//int getY();
	boolean isHorizontal();
	IEntityPortal getOtherPortal();
	void setOtherPortal(IEntityPortal p);
	EnumSide getDir();
	void setDir(EnumSide dir);
}
