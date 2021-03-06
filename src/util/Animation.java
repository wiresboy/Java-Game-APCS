package util;

import java.awt.image.*;
import java.util.ArrayList;

public class Animation {
	private ArrayList<Texture> images;
	private int currImgIndx = 0;
	private int framesToSkip;
	private int lastFrame = 0, thisFrame = 0;
	public Animation(BufferedImage[] images,int framesToSkip){
		this.images = new ArrayList<Texture>();
		for(BufferedImage i : images)
			this.images.add(new Texture(i));
		this.framesToSkip = framesToSkip;
	}
	public Animation(Texture[] images,int framesToSkip){
		this.images = new ArrayList<Texture>();
		for(Texture i : images)
			this.images.add(i);
		this.framesToSkip = framesToSkip;
	}
	
	public Texture getCurrent(){
		return images.get(currImgIndx);
	}
	public void setData(int[] data){
		this.currImgIndx = data[0];
		this.framesToSkip = data[1];
		this.lastFrame = data[2];
		this.thisFrame = data[3];
	}
	public String[] getData(){
		return new String[]{Integer.toString(currImgIndx),Integer.toString(framesToSkip),Integer.toString(lastFrame),Integer.toString(thisFrame)};
	}
	public Texture next(){
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
	public Texture back(){
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
