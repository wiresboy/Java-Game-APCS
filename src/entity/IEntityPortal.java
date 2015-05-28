package entity;

import java.awt.Graphics2D;

import web.Shareable;
import util.EnumSide;

public interface IEntityPortal extends IEntity{
	//void draw(Graphics2D g);
	//int getX();
	//int getY();
	boolean isHorizontal();
	IEntityPortal getOtherPortal();
	void setOtherPortal(IEntityPortal p);
	void setX(int x);//needed for the Web stuff, otherwise shouldn't be used.
	void setY(int y);
	EnumSide getDir();
	void setDir(EnumSide dir);
	void draw(Graphics2D g);
	void drawOtherColor(Graphics2D g);
}
