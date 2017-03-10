import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bot {
	private Robot bot;
	private int xMin;
	private int yMin;
	private int xMax;
	private int yMax;
	private int x;
	private int y;
	private PlayBoundry gamePanel;
	private ArrayList<Point> points;
	
	public Bot(int x, int y, int xMax, int yMax, PlayBoundry panel) throws AWTException{
		xMin = x;
		yMin = y;
		this.xMax = xMax;
		this.yMax = yMax;
		this.x = x;
		this.y = y;
		gamePanel = panel;
		bot = new Robot();
	}
	
	public void start(){
	   while(true){
		Point target = searchScreen();
		if(target != null)
		move(target.x+xMin, target.y+yMin);
	    //bot.mousePress(InputEvent.BUTTON1_MASK);
	    //bot.mouseRelease(InputEvent.BUTTON1_MASK);
		
	   }
	}
	
	private void move(int x, int y){
		System.out.println("Here");
		while(this.x != x && this.y != y){
			if(this.x != x && Math.random()<.2){
				//Move x
				System.out.println("Move x");
				if(this.x < x){
					this.x = this.x + 1;	
				}else{
					this.x = this.x - 1;
				}
				try {
					
					Thread.sleep((long) (8.0*Math.random()+1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	
			if(this.y != y && Math.random()<.2){
				//Move y
				System.out.println("Move y");
				if(this.y < y){
					this.y = this.y + 1;
				}else{
					this.y = this.y -1;
				}
				
				bot.mouseMove(this.x, this.y);
				
				try {
					Thread.sleep((long) (8.0*Math.random()+1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private Point searchScreen(){
		BufferedImage cap = bot.createScreenCapture(new Rectangle(xMin, yMin, xMax, yMax));
		File outputfile = new File("saved.png");
		try {
			ImageIO.write(cap, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[] pixels = new int[cap.getWidth()*cap.getHeight()];
		cap.getRGB(0,0, cap.getWidth(), cap.getHeight(), pixels, 0, cap.getWidth());
		int pixColor;
		int blue;
		int green;
		int red;
		Point tmpPoint;
		points = new ArrayList<Point>();
		for(int xLoc = 0; xLoc < cap.getWidth(); xLoc++){
			for(int yLoc = 0; yLoc < cap.getHeight(); yLoc++){
				pixColor = pixels[cap.getWidth() * yLoc + xLoc ];
				blue = pixColor & 0xff;
				green = (pixColor & 0xff00) >> 8;
				red = (pixColor & 0xff0000) >> 16;
				//System.out.println(red + " " + green + " " + blue);
				if( red >= 70 && red < 90  && blue < 10 && green < 35 && green > 9){
					//int x = i/get
					//gamePanel.paintImmediately(x, y, w, h);
					
					tmpPoint = new Point(xLoc, yLoc);
					gamePanel.addPoint(tmpPoint);
					points.add(tmpPoint);
				}
			}
		}
		gamePanel.refresh();
		//int[] pixels = ((DataBufferInt)cap.getRaster().getDataBuffer()).getData();
		if(points.size()!= 0){
			return points.get((int) (Math.random() * points.size()));
		}
		return null;//don't move
	}
	
}