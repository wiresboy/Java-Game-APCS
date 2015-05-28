package tileentity;

public abstract class TileEntityFloorButton extends TileEntity{
	public TileEntityFloorButton(){}
	public TileEntityFloorButton(boolean b){
		isFlipped = b;
	}
	public boolean isFlipped = false;
}
