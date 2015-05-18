package util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public class Texture extends BufferedImage{

	public Texture(ColorModel arg0, WritableRaster arg1, boolean arg2,Hashtable<?, ?> arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	public Texture(int i1, int i2,int i3){
		super(i1,i2,i3);
	}
	public Texture(int i1, int i2, int i3, IndexColorModel arg){
		super(i1,i2,i3,arg);
	}
	public Texture(BufferedImage bi){
		super(bi.getColorModel(),bi.copyData(null),bi.getColorModel().isAlphaPremultiplied(),null);
	}
	public Texture replaceColors(Color c1, Color c2){
		for(int y = 0; y < getHeight(); y++){
			for(int x = 0; x < getWidth(); x++){
				Color color = new Color(this.getRGB(x, y));
				if(color.equals(c1)){
					this.setRGB(x,y,c2.getRGB());
				}
			}
		}
		return this;
	}

}
