package tile;

public class WhiteTile extends Tile {

	@Override
	public String getId(){ return "2F"; }

	@Override
	public boolean hasTileEntity() { return false; }

	@Override
	public boolean hasOverlay() {
		return false;
	}

	@Override
	public boolean isPortalable(int side) {
		String name = this.getClass().getSimpleName();
		int i = name.indexOf("_");
		if(i != -1){
			String end = name.substring(i+1);
			if(end.indexOf('L') != -1 && side == Tile.LEFT 
				|| end.indexOf('R') != -1 && side == Tile.RIGHT
				|| end.indexOf('T') != -1 && side == Tile.TOP
				|| end.indexOf('B') != -1 && side == Tile.BOTTOM)
				return true;
			return false;
		}else return true; //wow three reserved words in a row forming a legit statement. That's the most I've ever managed so far.
	}

}