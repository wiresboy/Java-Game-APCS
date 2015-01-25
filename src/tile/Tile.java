package tile;

import java.awt.image.*;
import main.Sprite;

public class Tile extends Sprite{
	private boolean isSolid;
	public Tile(int x, int y, BufferedImage image) {
		super(x, y, image);
		isSolid = true;
	}
	public void setIsCollidable(boolean b){
		isSolid = b;
	}
	public boolean isCollidable(){
		return isSolid;
	}
	public void update(){}
	//only animated tiles can be powered
	public void power(){}
	public String toString(){
		String s = super.toString();
		return "Tile"+s.substring(6);
	}
}
