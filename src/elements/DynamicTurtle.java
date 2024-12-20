package elements;

import drawing.Canvas;
import geometry.CartesianCoordinate;
import java.util.List;

public class DynamicTurtle extends Turtle {
 
	//initial values
	private double speed = 300;
	private int cohesionRadius = 200;
	private int alignmentRadius = 50;
	private int separationRadius = 50;
	double k_c = 0.8;
	double k_a = 0.7;
	double k_s = 0.7;
	
	
	//method overloading
		public DynamicTurtle(Canvas canvas, double xPosition, double yPosition) {
			super(canvas);
			putPenUp();
			CartesianCoordinate start = new CartesianCoordinate(xPosition, yPosition);
			setStartPoint(start);
			drawSelf();
		}
		
	
	//setters for the parameters
	public void setCohesionRadius(int cohesionRadius) {
		this.cohesionRadius = cohesionRadius;
	}


	public void setAlignmentRadius(int alignmentRadius) {
		this.alignmentRadius = alignmentRadius;
	}



	public void setSeparationRadius(int separationRadius) {
		this.separationRadius = separationRadius;
	}



	public void setK_c(double k_c) {
		this.k_c = k_c;
	}



	public void setK_a(double k_a) {
		this.k_a = k_a;
	}



	public void setK_s(double k_s) {
		this.k_s = k_s;
	}


	
	public DynamicTurtle(Canvas myCanvas) {
		super(myCanvas);
		putPenUp();
		//move(-250);
		//drawSelf();
	}
	
	public double getSpeed() {
		return speed;	
		
	}
	
	public void setSpeed(double d) {
		this.speed = d;
	}
	
	
	
	
	//overall flocking algorithms
	public void flock(List<DynamicTurtle> agents) {
		
		this.alignment(agents);
		this.cohesion(agents);
		this.separation(agents);
	}
	

	//cohesion algorithm
	private void cohesion(List<DynamicTurtle> agents) {
	    double sumX = 0;
	    double sumY = 0;
	    int count = 0;

	    for (DynamicTurtle otherAgent : agents) {
	        if (otherAgent != this) {
	            double distance = Math.sqrt(Math.pow(this.getPositionX() - otherAgent.getPositionX(), 2) +
	                    Math.pow(this.getPositionY() - otherAgent.getPositionY(), 2));

	            if (distance < cohesionRadius) {
	                sumX += otherAgent.getPositionX();
	                sumY += otherAgent.getPositionY();
	                count++;
	            }
	        }
	    }

	    if (count > 0) {
	        double averageX = sumX / count;
	        double averageY = sumY / count;
	        double dx = averageX - this.getPositionX();
	        double dy = averageY - this.getPositionY();

	        double angleInRadians = Math.atan2(dy, dx);
	        double angleToCog = Math.toDegrees(angleInRadians);

	        double currentDirection = this.getDirection();
	        double angleDifference = angleToCog - currentDirection;

	        // bounds for angles
	        while (angleDifference > 180) angleDifference -= 360;
	        while (angleDifference < -180) angleDifference += 360;

	        // Adjust the agent's direction smoothly towards the center of gravity
	        double turnRate = 0.3; // Adjust this value to control turning speed
	        double turnAngle = angleDifference * turnRate;

	        this.turn((int) (turnAngle * k_c));
	    }
	}

	//alignment algorithm
	private void alignment(List<DynamicTurtle> agents) {
		
		int sumDirections = 0;
		int count = 0;
		//int radius = 20;
		
		for (DynamicTurtle agent : agents) {
			sumDirections = 0;
			if (agent != this) {
				//System.out.println(Math.pow(Math.pow(agent.getPositionX() - agent2.getPositionX(), 2) + Math.pow(agent.getPositionY() - agent2.getPositionY(), 2),0.5));
				if(Math.pow(Math.pow(this.getPositionX() - agent.getPositionX(), 2) + Math.pow(this.getPositionY() - agent.getPositionY(), 2),0.5) < alignmentRadius )  {
					sumDirections += agent.getDirection();
					count++;
				}
			}
		}
		if (count>0) {
			 int averageDirection = sumDirections / count;

            double turnAngle = (averageDirection - this.getDirection());
            double turnSmooth = 0.5;
            turnAngle = turnAngle * turnSmooth * k_a;
            
            this.turn((int) turnAngle);
            
            
            //System.out.println("turning");
		}
		
		
	}
	
	//separation algorithm
	private void separation(List<DynamicTurtle> agents) {
	    double avoidX = 0;
	    double avoidY = 0;
	    int count = 0;

	    for (DynamicTurtle otherAgent : agents) {
	        if (otherAgent != this) {
	            double dx = this.getPositionX() - otherAgent.getPositionX();
	            double dy = this.getPositionY() - otherAgent.getPositionY();
	            double distance = Math.sqrt(dx * dx + dy * dy);

	            if (distance < separationRadius && distance > 0) {
	                
	                avoidX += dx / (distance * distance);
	                avoidY += dy / (distance * distance);
	                count++;
	            }
	        }
	    }

	    if (count > 0) {
	        avoidX /= count;
	        avoidY /= count;

	        // Calculate the angle away from the average position
	        double angleInRadians = Math.atan2(avoidY, avoidX);
	        double angleAway = Math.toDegrees(angleInRadians);
	        double currentDirection = this.getDirection();

	        double angleDifference = angleAway - currentDirection;

	        //bounding angles
	        while (angleDifference > 180) angleDifference -= 360;
	        while (angleDifference < -180) angleDifference += 360;

	        
	        double turnRate = 0.3; 
	        double turnAngle = angleDifference * turnRate;

	        this.turn((int) (turnAngle * k_s));
	    }
	}

	
	//algorithm for avoiding obstacles
	public void avoidObstacle(List<Obstacle> obstacles) {
	    double avoidX = 0;
	    double avoidY = 0;
	    int avoidCount = 0;
	    
	    // setting avoidance radius - substracting here because radius calculated will be bigger than
		//width/2 and height/2 since it is the hypoteneuse of the triange
	    double avoidanceRadius = -20; 
	    	
	    for (Obstacle obstacle : obstacles) {
	        CartesianCoordinate centre = obstacle.getCentrePoint();
	        double dx = this.getPositionX() - centre.getX();
	        double dy = this.getPositionY() - centre.getY();
	        double distance = Math.sqrt(dx * dx + dy * dy);

	        if (distance < obstacle.getRadii() + avoidanceRadius) {
	            
	            avoidX += dx / distance;
	            avoidY += dy / distance;
	            avoidCount++;
	        }
	    }

	    if (avoidCount > 0) {
	        // average the avoidance coordinates
	        avoidX /= avoidCount;
	        avoidY /= avoidCount;

	        // calculate the angle away from the obstacle in degrees
	        double angleInRadians = Math.atan2(avoidY, avoidX);
	        double angleAwayObstacle = Math.toDegrees(angleInRadians);
	        double currentDirection = this.getDirection();

	        // calculate the difference between the current direction and the avoidance direction
	        double angleDifference = angleAwayObstacle - currentDirection;

	        // bounding the angle
	        while (angleDifference > 180) angleDifference -= 360;
	        while (angleDifference < -180) angleDifference += 360;

	        // turning smoothly away from the obstacle
	        double turnRate = 0.3; 
	        double turnAngle = angleDifference * turnRate;

	        this.turn((int) turnAngle);
	    }
	}
	
	
	public void fleePredators(List<Predator>predators, List<DynamicTurtle>removeAgents) {
		
		double fleeRadius = 40; // radius within which the turtle will flee
		double eaten = 10;
		
	    for (Predator predator : predators) {
	        double distance = Math.sqrt(Math.pow(this.getPositionX() - predator.getPositionX(), 2) +
	                                    Math.pow(this.getPositionY() - predator.getPositionY(), 2));

	        if (distance < fleeRadius && distance>eaten) {
	            
	        	this.turn(90); //turn some arbitary value to try and escape
	        }else if(distance<=eaten) {
	        	
	        	// if predator is too close, undraw and remove the turtle
	        	removeAgents.add(this);	
	        }
	    }
		
	}





 //update method
	public void update(int t) {
		
		putPenUp();
		double distance = (speed)*t*0.001;
		move(distance);
		
	}

}
