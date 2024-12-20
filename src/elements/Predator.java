package elements;

import java.util.List;

import drawing.Canvas;
import geometry.CartesianCoordinate;

public class Predator extends Turtle {
		private double predatorSpeed = 40;
	    private double huntingRadius = 150;

	public Predator(Canvas myCanvas, double xPosition, double yPosition) {
		super(myCanvas);
		
		putPenUp();
        CartesianCoordinate start = new CartesianCoordinate(xPosition, yPosition);
        setStartPoint(start);
       
	}
	
	public void hunt(List<DynamicTurtle>turtles) {
		double prowlingSpeed = 40;
		double huntingSpeed = 130;
		double closestDistance = Double.MAX_VALUE;
        DynamicTurtle targetTurtle = null;

        for (DynamicTurtle turtle : turtles) {
            double distance = Math.sqrt(Math.pow(this.getPositionX() - turtle.getPositionX(), 2) +
                                        Math.pow(this.getPositionY() - turtle.getPositionY(), 2));
            if (distance < huntingRadius && distance < closestDistance) {
                closestDistance = distance;
                targetTurtle = turtle;
            }
        }

        if (targetTurtle != null) {
            // Calculate direction towards the target turtle
            double dx = targetTurtle.getPositionX() - this.getPositionX();
            double dy = targetTurtle.getPositionY() - this.getPositionY();
            double angleInRadians = Math.atan2(dy, dx);
            double angleToTarget = Math.toDegrees(angleInRadians);
            double currentDirection = this.getDirection();
            double angleDifference = angleToTarget - currentDirection;

            // Normalize the angle difference to the range [-180, 180]
            while (angleDifference > 180) angleDifference -= 360;
            while (angleDifference < -180) angleDifference += 360;

            // Adjust the predator's direction smoothly towards the target turtle
            double turnRate = 0.7; // Adjust this value to control turning speed
            double turnAngle = angleDifference * turnRate;

            this.turn((int) turnAngle);
            this.setSpeed(huntingSpeed);
        } else {
            // If no turtles are within the hunting radius, use normal speed
            this.setSpeed(prowlingSpeed);
        }
	}
	
	public void setSpeed(double speed) {
        this.predatorSpeed = speed;
    }
	
	
	public void drawPredator() {
		putPenDown();
		turn(165);
		move(60);
	
		turn(105);
		move(31.058);
		
		turn(105);
		move(60);
		
		turn(-15);
		putPenUp();
	}
	
	public void undrawPredator(Canvas canvas) {
		canvas.removeMostRecentLine();
		canvas.removeMostRecentLine();
		canvas.removeMostRecentLine();
		
	}
	
	public void updatePredator(int t) {
			
			putPenUp();
			double distance = (predatorSpeed)*t*0.001;
			move(distance);
			
		}
	
	public void predatorAvoidObstacle(List<Obstacle> obstacles) {
	    double avoidX = 0;
	    double avoidY = 0;
	    int avoidCount = 0;
	    double avoidanceRadius = -30; // Set your avoidance radius

	    for (Obstacle obstacle : obstacles) {
	        CartesianCoordinate centre = obstacle.getCentrePoint();
	        double dx = this.getPositionX() - centre.getX();
	        double dy = this.getPositionY() - centre.getY();
	        double distance = Math.sqrt(dx * dx + dy * dy);

	        if (distance < obstacle.getRadii() + avoidanceRadius) {
	            // Calculate the avoidance vector (inverted direction)
	            avoidX += dx / distance;
	            avoidY += dy / distance;
	            avoidCount++;
	        }
	    }

	    if (avoidCount > 0) {
	        // Average the avoidance vectors
	        avoidX /= avoidCount;
	        avoidY /= avoidCount;

	        // Calculate the angle away from the obstacle in degrees
	        double angleInRadians = Math.atan2(avoidY, avoidX);
	        double angleAwayObstacle = Math.toDegrees(angleInRadians);
	        double currentDirection = this.getDirection();

	        // Calculate the difference between the current direction and the avoidance direction
	        double angleDifference = angleAwayObstacle - currentDirection;

	        // normalise the angle difference to the range [-180, 180]
	        while (angleDifference > 180) angleDifference -= 360;
	        while (angleDifference < -180) angleDifference += 360;

	        // Adjust the agent's direction smoothly away from the obstacle
	        double turnRate = 0.3; // Adjust this value to control turning speed
	        double turnAngle = angleDifference * turnRate;

	        // Turn the agent based on the calculated turn angle
	        this.turn((int) turnAngle);
	    }
	}

}
