package util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageManipulator {
	public static BufferedImage cropImage(BufferedImage src, int x, int y, int width, int height) {
	      BufferedImage dest = src.getSubimage(x, y, width, height);
	      return dest; 
	}
	public static BufferedImage loadImage(String name){
		try{
		return ImageIO.read(new File(System.getProperty("user.dir")+"\\textures\\"+name));
		}catch(IOException e){
			System.err.println("ERROR: image "+name+" not found.");
			return null;
		}
	}
	public static BufferedImage scale(BufferedImage i, int max){
		Image image = (Image)i;
		if(image != null){
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			double dWidth = 0;
			double dHeight = 0;
			if (width == height) {
				dWidth = max;
				dHeight = max;
			} 
			else if (width > height) {
				dWidth = max;
				dHeight = ((double) height / (double) width) * max;
			}
			else {
				dHeight = max;
				dWidth = ((double) width / (double) height) * max;
			}
		    
			image = image.getScaledInstance((int) dWidth, (int) dHeight, Image.SCALE_SMOOTH);
			BufferedImage bImage = toBufferedImage(image);
			return bImage;
		}else
			return null;
	}

	public static BufferedImage toBufferedImage(Image img){
		if (img instanceof BufferedImage){
			return (BufferedImage) img;
		}

		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		return bimage;
	}
	 
	public static BufferedImage horizontalFlip(BufferedImage img) {  
		int w = img.getWidth();   
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(w, h, img.getColorModel().getTransparency());
		Graphics2D g = dimg.createGraphics();
		g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null); 
		g.dispose(); 
		return dimg;   
	}
	 
	public static BufferedImage verticalFlip(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(w, h, img.getColorModel().getTransparency());
		Graphics2D g = dimg.createGraphics();
		g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null); 
		g.dispose();
		return dimg;
	} 
	
	public static BufferedImage resize(int newWidth, int newHeight, BufferedImage img){
		BufferedImage newImg = toBufferedImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT));
		return newImg;
	}
	
	public static BufferedImage getRotatedImage(BufferedImage src, int angle) {
		BufferedImage bufferedImage  = src;
		AffineTransform tx = new AffineTransform();
		tx.rotate(0.5*Math.PI, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
		bufferedImage = op.filter(bufferedImage, null);
		return bufferedImage;
	}
}
