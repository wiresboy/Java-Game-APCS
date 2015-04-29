package entity;

import map.Map;

public interface IEntity{
	int getId();
	int getX();
	void setX(int i);
	int getY();
	void setY(int i);
	void setLocation(int x, int y);
	void setMap(Map m);
}