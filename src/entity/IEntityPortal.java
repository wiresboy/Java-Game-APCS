package entity;

import java.awt.Graphics2D;

import web.Shareable;

public interface IEntityPortal {
	void draw(Graphics2D g);
	int getX();
	int getY();
	boolean isHorizontal();
	IEntityPortal getOtherPortal();
	void setOtherPortal(IEntityPortal p);
	int getDir();
	void setDir(int dir);
	void setX(int x);//needed for the Web stuff, otherwise shouldn't be used.
	void setY(int y);
}
