package tile;

import util.ImageManipulator;
import util.Resources;

public abstract class WallLight extends TileOverlay{
	public WallLight(){
		String className = this.getClass().getSimpleName();
		//this will be either T or B, and will get the overlay as such
		@SuppressWarnings("unused")
		char c = className.charAt(className.indexOf("_")+2);
		overlay = Resources.getOverlay("WallLight"); 
		String label = className.substring(className.indexOf("_")+1);
		// TODO finish textures and find values for these
		if(label.equals("LB")){
			offsetX = -4; offsetY = 0;
			overlay = ImageManipulator.verticalFlip(overlay);
		}else if(label.equals("RB")){
			overlay = ImageManipulator.verticalFlip(overlay);
			offsetX = 4; offsetY = 0;
		}else if(label.equals("LT")){
			offsetX = -4; offsetY = -16;
		}else if(label.equals("RT")){
			offsetX = 4; offsetY = -16;
		}
	}
	@Override
	public final boolean hasTileEntity(){ return false; }
}
