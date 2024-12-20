package geometry;

public class CartesianCoordinate {
	private double xPosition;
	private double yPosition;
	
	public CartesianCoordinate(double xPosition, double yPosition) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		}
	
	public double getX() {
		return xPosition;
	}
	public double getY() {
		return yPosition;
	}
	public void setX(double x) {
		xPosition = x;
	}
	public void setY(double y) {
		yPosition = y;
	}
	
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}
}
