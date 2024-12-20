package elements;

import drawing.Canvas;
import geometry.CartesianCoordinate;

public class Rectangle extends Turtle implements Obstacle {
	private int width;
	private int height;
	private int topLeftX;
	private int topLeftY;
	
	//takes the coordinates of the top left corner of the square as well as width and height
	public Rectangle(Canvas myCanvas,int tl_x, int tl_y,int width,int height) { 
		super(myCanvas);
		putPenUp();
		CartesianCoordinate topLeftCorner = new CartesianCoordinate(tl_x, tl_y);
		setStartPoint(topLeftCorner);
		this.width = width;
		this.height = height;
		this.topLeftX = tl_x;
		this.topLeftY = tl_y;
		
		
	}
	

	
	public void drawObstacle() {
		
		putPenDown();
		for(int i=0; i<2; ++i) {
			
			move(width);
			turn(90);
			move(height);
			turn(90);
			
		}
		putPenUp();
	}

	@Override
	public void undrawObstacle(Canvas myCanvas) {
		
		myCanvas.removeMostRecentLine();
		myCanvas.removeMostRecentLine();
		myCanvas.removeMostRecentLine();
		myCanvas.removeMostRecentLine();	
		
	
	}
	
	public CartesianCoordinate getCentrePoint(){
		int x = this.topLeftX + (this.width/2);
		int y = this.topLeftY + (this.height/2);
		
		CartesianCoordinate centre = new CartesianCoordinate(x,y);
		
		return centre;
	}
	
	public double getRadii() {
		return Math.sqrt(Math.pow(this.width/2,2) + Math.pow(this.height, 2));
	}



	@Override
	public double getHeight() {
		
		return height;
	}



	@Override
	public double getWidth() {
		
		return width;
	}

}
