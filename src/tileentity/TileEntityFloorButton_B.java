package tileentity;

import tileentity.TileEntity;
import util.Resources;
import util.Texture;
/**
 * 
 * @author Lucas Rezac
 *
 */
public class TileEntityFloorButton_B extends TileEntity {
	private static final Texture off1 = Resources.getTile("TileFloorButton_B");
	private static final Texture off2 = off1.horizontalFlip();
	public Texture getImageBasedOnState(){
		return isFlipped? off2 : off1;
	}
	private boolean isFlipped = false;
	public TileEntityFloorButton_B(){}
	public TileEntityFloorButton_B(boolean b){
		isFlipped = b;
	}
}
