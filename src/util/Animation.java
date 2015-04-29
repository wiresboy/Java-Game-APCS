package util;

import java.awt.image.*;
import java.util.ArrayList;

public class Animation {
	private ArrayList<BufferedImage> images;
	private int currImgIndx = 0;
	private int framesToSkip;
	private int lastFrame = 0, thisFrame = 0;
	public Animation(BufferedImage[] images,int framesToSkip){
		this.images = new ArrayList<BufferedImage>();
		for(BufferedImage i : images)
			this.images.add(i);
		this.framesToSkip = framesToSkip;
	}
	public BufferedImage getCurrent(){
		return images.get(currImgIndx);
	}
	public BufferedImage next(){
		if(thisFrame == lastFrame+framesToSkip){
			lastFrame = thisFrame;
			currImgIndx++;
			if(currImgIndx == images.size()){
				currImgIndx = 0;
				return images.get(images.size()-1);
			}
			return images.get(currImgIndx-1);
		}
		thisFrame++;
		return getCurrent();
	}
	public BufferedImage back(){
		if(currImgIndx == 0){
			currImgIndx = images.size()-1;
		}else{
			currImgIndx--;
		}
		thisFrame = 0;
		lastFrame = 0;
		return getCurrent();
	}
	public void reset(){
		lastFrame = 0;
		thisFrame = 0;
		currImgIndx = 0;
	}
}
