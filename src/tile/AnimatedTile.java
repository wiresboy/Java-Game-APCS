package tile;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import util.Animation;

public class AnimatedTile extends Tile{
	private ArrayList<Animation> animations;
	private int currentAnimation = 0;
	public boolean isPowered = false;
	
	public AnimatedTile(int x, int y, int framesToSkip,BufferedImage[]...animations){
		super(x,y,animations[0][0]);
		this.animations = new ArrayList<Animation>();
		for(BufferedImage[] bImgList : animations){
			this.animations.add(new Animation(bImgList,framesToSkip));
		}
	}
	public void draw(Graphics2D g){
		g.drawImage(getCurrentAnimation().next(),null,getX(),getY());
	}
	public ArrayList<Animation> getImages(){
		return animations;
	}
	public Animation getCurrentAnimation(){
		return animations.get(currentAnimation);
	}
	public void setAnimation(int animationIndex){
		if(animationIndex < animations.size())
			currentAnimation = animationIndex;
	}
	public BufferedImage getImage(){
		return animations.get(currentAnimation).getCurrent();
	}
	public void update(){
		isPowered = false;
	}
	public void power(){
		isPowered = true;
	}
	public String toString(){
		return "Animated"+super.toString();
	}
}
