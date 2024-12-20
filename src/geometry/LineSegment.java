package geometry;


public class LineSegment {
	private CartesianCoordinate startPoint;
	private CartesianCoordinate endPoint;
	
	public LineSegment(CartesianCoordinate startPoint, CartesianCoordinate endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		
	}
	
	public CartesianCoordinate getEndPoint() {
		return endPoint;
	}	
	public CartesianCoordinate getStartPoint() {
		return startPoint;
	}	
	public String toString(){
		return "Line is at " + getStartPoint().toString() + " to " + getEndPoint().toString();
	}
	public double length() {
		double diffX = endPoint.getX() - startPoint.getX();
		double diffY = endPoint.getY() - startPoint.getY();
		
		return Math.sqrt(diffY*diffY - diffX*diffX);
		//return diffY;
		
		
	}
	


	
}
	


