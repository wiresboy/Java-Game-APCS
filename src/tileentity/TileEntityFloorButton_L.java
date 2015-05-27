package tileentity;

import util.Resources;
import util.Texture;

/**
 * 
 * @author Lucas Rezac
 *
 */
public class TileEntityFloorButton_L extends TileEntity {
	private static final Texture off1 = Resources.getTile("TileFloorButton_L");
	private static final Texture off2 = off1.verticalFlip();
	public Texture getImageBasedOnState(){
		return isFlipped? off2 : off1;
	}
	private boolean isFlipped = false;
	public TileEntityFloorButton_L(){}
	public TileEntityFloorButton_L(boolean b){
		isFlipped = b;
	}
}
