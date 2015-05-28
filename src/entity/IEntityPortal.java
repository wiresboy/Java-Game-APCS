package entity;

import java.awt.Graphics2D;

import util.EnumSide;

public interface IEntityPortal extends IEntity{
	boolean isHorizontal();
	IEntityPortal getOtherPortal();
	void setOtherPortal(IEntityPortal p);
	void setX(int x);//needed for the Web stuff, otherwise shouldn't be used.
	void setY(int y);
	EnumSide getDir();
	void setDir(EnumSide dir);
	void setDirInt(int dir);//use only for web serializing
	int getDirInt();//use only for web serializing
	void draw(Graphics2D g);
	void drawOtherColor(Graphics2D g);
}
