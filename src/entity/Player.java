package entity;

import main.Sprite;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.ArrayList;

import util.*;

public class Player extends Sprite{
	public int x, y;
	public boolean isAttacking = false;
	int imageIndex;
	Sprite sword = null;
	//BufferedImage nullImage;
	int timeout, startTimeout;
	private static ArrayList<BufferedImage> images;
	private static ArrayList<BufferedImage> swordImages;
	private static ArrayList<BufferedImage> attackImages;
	private BufferedImage currImage;
	private final int down = 0, left = 1, up = 2, right = 3;
	private int indexAdd;
	private Direction direction;
	public Player(int x,int y){
		super(x,y,null);
		this.x = x;
		this.y = y;
		indexAdd = 0;
		images = new ArrayList<BufferedImage>();
		BufferedImage start = null;
		//try {
			start = ImageManipulator.loadImage("link.png");
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		for(int yy = 0; yy<48; yy+=16){
			for(int xx = 0; xx<64; xx+=16){
				images.add(ImageManipulator.scale(ImageManipulator.cropImage(start, xx,yy,16,16),32));
			}
		}
		swordImages = new ArrayList<BufferedImage>();
		start = null;
		//try {
			start = ImageManipulator.loadImage("sword_brown.png");
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		for(int xx = 0; xx<64; xx+=16){
				swordImages.add(ImageManipulator.scale(ImageManipulator.cropImage(start, xx,0,16,16),32));
		}
		attackImages = new ArrayList<BufferedImage>();
		start = null;
		//try {
			start = ImageManipulator.loadImage("link_attacking.png");
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		for(int xx = 0; xx<64;xx+=16){
			attackImages.add(ImageManipulator.scale(ImageManipulator.cropImage(start,xx,0,16,16),32));
		}
		sword = new Sprite(0,0,null);
		currImage = images.get(1);
		setImage(currImage);
	}
	public void moveLeft(){
		if(!isAttacking){
		updateIndex();
		setImage(images.get(imageIndex = left+indexAdd));
		direction = Direction.LEFT;
		x-=2;
		}
	}
	public void moveRight(){
		if(!isAttacking){
		updateIndex();
		setImage(images.get(imageIndex = right+indexAdd));
		direction = Direction.RIGHT;
		x+=2;
		}
	}
	public void moveUp(){
		if(!isAttacking){
		updateIndex();
		setImage(images.get(imageIndex = up+indexAdd));
		direction = Direction.UP;
		y-=2;
		}
	}
	public void moveDown(){
		if(!isAttacking){
		updateIndex();
		setImage(images.get(imageIndex = down+indexAdd));
		direction = Direction.DOWN;
		y+=2;
		}
	}
	public void updateIndex(){
		if(indexAdd == 0)
			indexAdd=4;
		else
			indexAdd = 0;
	}
	public int imageIndex(){
		return imageIndex;
	}

	public void draw(Graphics2D g){
		sword.draw(g);
		g.drawImage(currImage,x,y,null);

	}
	public void setImage(BufferedImage image){
		currImage = image;
	}
	public void swingSword(){
		if(sword.getImage() == null){
			isAttacking = true;
			System.out.println("Sword swung!");
			setImage(attackImages.get(Direction.eval(direction)));
			timeout = (int)System.currentTimeMillis() + 400;
			if(direction == Direction.DOWN){
				sword.setX(x);
				sword.setY(y+24);
				sword.setImage(swordImages.get(0));
			}else if(direction == Direction.LEFT){
				sword.setX(x-24);
				sword.setY(y);
				sword.setImage(swordImages.get(1));
			}else if(direction == Direction.UP){
				sword.setX(x);
				sword.setY(y-24);
				sword.setImage(swordImages.get(2));
			}else if(direction == Direction.RIGHT){
				sword.setX(x+24);
				sword.setY(y);
				sword.setImage(swordImages.get(3));
			}
		}
	}
	public void update(){
		if(sword.getImage() != null){
			System.out.println("timeout:"+timeout+" System time:"+(int)System.currentTimeMillis());
			if(timeout <= (int)System.currentTimeMillis()){
				sword.setImage(null);
				System.out.println("Sword gone!");
				timeout = 0;
				isAttacking = false;
				setImage(images.get(Direction.eval(direction)+indexAdd));
			}
		}
	}
}
