package entity;

import main.Sprite;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.ArrayList;

import util.*;

public class Player extends Sprite{
	public int x, y;
	private Map map;
	private static ArrayList<BufferedImage> images;
	private BufferedImage currImage;
	private final int down = 0, left = 1, up = 2, right = 3;
	//private Direction direction;
	//manages physics in the y direction
	private int gravity = 0;
	//manages physics in the x direction
	private int velocity = 0;
	public Player(int x,int y){
		super(x,y,null);
		this.x = x;
		this.y = y;
	}
	public void setMap(Map map){
		this.map = map;
	}
	public void draw(Graphics2D g){
		g.drawImage(currImage,x,y,null);
	}
	public void setImage(BufferedImage image){
		currImage = image;
	}
	public void update(){
		Tile t =
	}
}
