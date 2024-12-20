package elements;

import drawing.Canvas;
import geometry.CartesianCoordinate;

public interface Obstacle {

	public abstract void drawObstacle();
	public abstract void undrawObstacle(Canvas myCanvas);
	public abstract CartesianCoordinate getCentrePoint();
	public abstract double getRadii();
	public abstract double getHeight();
	public abstract double getWidth();
	
}
