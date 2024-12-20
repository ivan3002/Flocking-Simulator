package elements;
//import tools.Utils;

import drawing.Canvas;
import geometry.CartesianCoordinate;


//This is the base functionality for placing elements on screen


public class Turtle {
	private Canvas myCanvas;
	private boolean penIsDown;
	private int currentBearing = 0;
	private CartesianCoordinate currentPoint = new CartesianCoordinate(400,300);
	private boolean offScreen;
	private double radius = 5;
	private int steps = (int) (2 * Math.PI * radius/5);
	

	//private CartesianCoordinate endPoint = new CartesianCoordinate(0,0); // need to use new here to actually create an instance
	

	//can make setters for start points

	public Turtle(Canvas myCanvas) {
		this.myCanvas = myCanvas;
	}

	public void setStartPoint(CartesianCoordinate startPoint) {
		this.currentPoint = startPoint;
	}

	/**
	 * The turtle is moved in its current direction for the given number of pixels. 
	 * If the pen is down when the robot moves, a line will be drawn on the floor.
	 * 
	 * @param i The number of pixels to move.
	 */
	public void move(double i) {
		double x = currentPoint.getX();
		double y = currentPoint.getY();
		
		double newX = x + Math.cos(Math.toRadians(currentBearing))*i; 
		double newY = y + Math.sin(Math.toRadians(currentBearing))*i;
		CartesianCoordinate newPoint = new CartesianCoordinate(newX, newY);
		if (penIsDown == true) {
			myCanvas.drawLineBetweenPoints(currentPoint, newPoint);
		} 
		currentPoint = newPoint;
		//startPoint.toString();
		//newPoint.toString();
		

	}

	/**
	 * Rotates the turtle clockwise by the specified angle in degrees.
	 * 
	 * @param i The number of degrees to turn.
	 */
	public void turn(int d) {
		
		currentBearing = currentBearing + d;
		if ( currentBearing > 360) {
			currentBearing -= 360;
		} else if (currentBearing < 0) {
			currentBearing += 360;
		}
	}
	

	/**
	 * Moves the pen off the canvas so that the turtle's route isn't drawn for any subsequent movements.
	 */
	public void putPenUp() {
		penIsDown = false;
		
	}
	

	/**
	 * Lowers the pen onto the canvas so that the turtle's route is drawn.
	 */
	public void putPenDown() {
		penIsDown = true;
	}
	
	public void drawSelf() {
	    putPenDown();
	    
	    double angleStep = 360.0 / steps;

	    for (int i = 0; i < steps; i++) {
	        move(5); // Move a small step
	        turn((int)angleStep); // Turn a small angle
	    }

	    putPenUp();
	}
	
	
	public void undraw() {
		
		for(int j=0; j< steps; j++) {
			myCanvas.removeMostRecentLine();
		}
		
		//myCanvas.removeMostRecentLine();
		//myCanvas.removeMostRecentLine();
		
		
	}
	public CartesianCoordinate getPosition() {
		return currentPoint;
	}
	
	public double getPositionX() {
		double x1 = currentPoint.getX();
		return x1;
				
	}
	
	public double getPositionY() {
		double y1 = currentPoint.getY();
		return y1;
	}
	
	public int getDirection() {
		//System.out.println(currentBearing);
		return currentBearing;
	}

 	public void wrapPosition(int maxX, int maxY) {
 		double tx = getPositionX();
 		double ty = getPositionY();
 		
 		
 		if (tx>maxX) {
 			currentPoint.setX(0);
 			offScreen = true;
 			//myCanvas.removeMostRecentLine();
 		
 			
 		} else if (tx<0){
 			currentPoint.setX(maxX);
 			offScreen = true;
 			//myCanvas.removeMostRecentLine();
 		}
 		if (ty>maxY) {
 			currentPoint.setY(0);
 			offScreen = true;
 			//myCanvas.removeMostRecentLine();
 			
 		}else if (ty<0) {
 			currentPoint.setY(maxY);
 			offScreen = true;
 			//myCanvas.removeMostRecentLine();
 			
 		} else {
 			offScreen = false;
 		}
 		
 			
 	}
 	
 	public boolean isOffScreen() {
 		return offScreen;
 	}
 	
 
 	
}


