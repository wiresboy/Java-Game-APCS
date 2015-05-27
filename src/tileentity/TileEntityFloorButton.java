package tileentity;

public abstract class TileEntityFloorButton extends TileEntity{
	public TileEntityFloorButton(){}
	public TileEntityFloorButton(boolean b){
		isFlipped = b;
	}
	private boolean isFlipped = false;
}
