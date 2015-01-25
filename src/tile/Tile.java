package tile;

import java.awt.image.*;
import main.Sprite;

public class Tile extends Sprite{
	public Tile(int x, int y, BufferedImage image) {
		super(x, y, image);
	}
	public void update(){}
	//only animated tiles can be powered
	public void power(){}
	public String toString(){
		String s = super.toString();
		return "Tile"+s.substring(6);
	}
}
