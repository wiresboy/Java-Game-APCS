package entity;

import java.awt.Graphics2D;

public interface IEntityPortal {
	void draw(Graphics2D g);
	int getX();
	int getY();
	boolean isHorizontal();
	IEntityPortal getOtherPortal();
	void setOtherPortal(IEntityPortal p);
}
