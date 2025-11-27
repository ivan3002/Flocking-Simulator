
Flocking Simulation 

===========================

This project is a flocking simulation program written in Java, version 11. The program simulates the behavior of agents (DynamicTurtles) that exhibit flocking behavior, avoiding obstacles and predators within a graphical user interface (GUI).

Source Files
============

The following Java source files are included in this project:

1. FlockingProgram.java
2. FlockingGuiSetup.java
3. elements/DynamicTurtle.java
4. elements/Turtle.java
5. elements/Predator.java
6. elements/Rectangle.java
7. elements/Obstacle.java
8. drawing/Canvas.java
9. geometry/CartesianCoordinate.java
10. geometry/LineSegment.java
11. tools/Utils.java


Entry Point
===========

The main method is within the FlockingProgram Class

To run the program, use the following command:

java flockingsimulationprogram.FlockingProgram

Description of Source Files
===========================

1. FlockingProgram.java: This is the main class that runs the simulation. It sets up the GUI, initializes agents, obstacles, and predators, and contains the main simulation loop.

2. FlockingGuiSetup.java: This class handles the setup of the graphical user interface (GUI). It configures the window, sliders, buttons, and other UI elements.

3. elements/DynamicTurtle.java: This class represents the agents in the simulation that exhibit flocking behavior. It includes methods for flocking (flock), avoiding obstacles (avoidObstacle), and fleeing from predators (fleePredators).

4. elements/Turtle.java: The base class for all movable entities in the simulation. It provides fundamental methods for movement (move), turning (turn), and drawing (drawSelf, undraw). This class serves as the parent class for DynamicTurtle and Predator.

5. elements/Predator.java: This class represents predators in the simulation with behaviors to hunt DynamicTurtle agents. It includes methods for chasing agents (hunt) and avoiding obstacles (predatorAvoidObstacle).

6. elements/Rectangle.java: This class implements the Obstacle interface and represents rectangular obstacles in the simulation. It includes methods to draw and undraw the obstacle.

7. elements/Obstacle.java: This interface defines the basic structure for obstacles in the simulation. It is implemented by specific obstacle classes like Rectangle.

8. drawing/Canvas.java: This class handles all the drawing operations for the simulation. It provides methods to draw lines, clear the canvas, and manage the graphical representation of the simulation. IT HAS NOT BEEN ALTERED

9. geometry/CartesianCoordinate.java: This class represents a point in Cartesian coordinates (x, y). It provides methods to get and set the coordinates and a toString method for representation.

10. geometry/LineSegment.java: This class represents a line segment defined by a start point and an end point. It provides methods to calculate the length of the line segment and the distance from a point to the line segment.

11. tools/Utils.java: This class contains utility methods that are used throughout the simulation, such as methods for pausing execution (pause).


