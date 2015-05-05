package util;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.PreSetup;
import map.Map;

public class DrawTileIDHexTable extends Applet{
	BufferedImage canvas;
	Image draw;
	Map map;
	@Override
	public void start(){
		PreSetup.init();
		canvas = ImageManipulator.loadImage("hexchart_blank.png");
		Graphics2D g = (Graphics2D)canvas.createGraphics();
		int hex = 0;
		Map map = new Map();
		ArrayList<String> list = new ArrayList<String>();
		list.add("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		for(int row = 0; row < 16; row++){
			String str = "FF";
			for(int col = 0; col < 16; col++){
				String hexStr = Integer.toHexString(hex);
				hex++;
				if(hexStr.length()==1){
					hexStr = "0"+hexStr;
				}
				hexStr = hexStr.toUpperCase();
				str+=hexStr;
			}
			list.add(str);
		}
		//System.out.println(list.toString());
		map.loadMap(list);
		map.drawBase(g);
		String dir = ImageManipulator.dir;
		File output;
		if(true/*System.getProperty("os.name").indexOf("MAC") != -1*/){
			while(dir.indexOf("\\") != -1) dir = dir.replace("\\","/");
			output = new File(dir+"/textures/hexchart_output.png");
			System.out.println(output.getAbsolutePath());
		}else{
			output = new File(dir+"\\textures\\hexchart_output.png");
			System.out.println(output.getAbsolutePath());
		}
		if(!output.exists())
			try {
				output.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			ImageIO.write(canvas, "PNG", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		draw = canvas.getScaledInstance(canvas.getWidth()*4, canvas.getHeight()*4, Image.SCALE_DEFAULT);
	}
	@Override
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(draw, 0, 0, null);
	}
}
