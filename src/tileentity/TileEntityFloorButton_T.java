package tileentity;

import util.Resources;
import util.Texture;

public class TileEntityFloorButton_T extends TileEntity {
	private static final Texture off1 = Resources.getTile("TileFloorButton_T");
	private static final Texture off2 = off1.horizontalFlip();
	public Texture getImageBasedOnState(){
		return isFlipped? off2 : off1;
	}
	private boolean isFlipped = false;
	public TileEntityFloorButton_T(){}
	public TileEntityFloorButton_T(boolean b){
		isFlipped = b;
	}
}
