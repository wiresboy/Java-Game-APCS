package tileentity;

import util.Resources;
import util.Texture;

/**
 * 
 * @author Lucas Rezac
 *
 */
public class TileEntityFloorButton_R extends TileEntity {
	private static final Texture off1 = Resources.getTile("TileFloorButton_R");
	private static final Texture off2 = off1.verticalFlip();
	public Texture getImageBasedOnState(){
		return isFlipped? off2 : off1;
	}
	private boolean isFlipped = false;
	public TileEntityFloorButton_R(){}
	public TileEntityFloorButton_R(boolean b){
		isFlipped = b;
	}
}
