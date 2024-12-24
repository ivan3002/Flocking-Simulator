# Flocking-Simulator

Flocking Simulation - Report
1. Overview and Theoretical Background
Flocking behaviour is a type of collective animal behaviour exhibited by entities such as birds, fish, and insects when they move together in groups. This behaviour is seen in nature and can be simulated using simple rules that control the behaviours of individual agents. Flocking Behavior provides an insight into how simple local interactions can lead to complex global patterns and emergent behaviour in groups[1].
Flocking is typically modelled using three primary rules:
Cohesion: This rule ensures that agents are attracted to the average position of their neighbours, promoting group formation and maintenance. It simulates the tendency of individuals to stay close to their group, contributing to the overall cohesiveness of the flock. 
First, calculate the average position of the agents within a radius r. x̄ is the “centre of mass”. Then calculate the angle through which the agent must turn θc to face towards x̄.

Alignment: This rule aligns an agent’s direction with the average direction of its neighbours. It helps to synchronise the movement of the flock, allowing individuals to move together in a coordinated manner.
First, calculate the average direction dˉ of the agents within a radius r. Then calculate the angle through which the agent must turn θa to align with dˉ.

Separation: This rule ensures that agents maintain a minimum distance from their neighbours to avoid overcrowding and collisions. It prevents individuals from getting too close to each other, thus maintaining balanced spacing within the flock.
First, calculate the position of each neighbouring agent within a radius r. Then calculate the angle through which the agent must turn θs to move away from the closest neighbours.

The constant k_c, k_a and k_s here are used to vary the amount of cohesion, alignment and separation behaviour respectively with 1 meaning they always exhibit these behaviours and 0 meaning they never do.
When combined, these simple rules can produce complex movement patterns. In this implementation of the flocking simulation, these rules are used to govern the movement of individual agents (referred to as "DynamicTurtles" in the code) within a two-dimensional space. Each agent continuously adjusts its position and direction based on the positions and directions of its neighbours, resulting in a realistic simulation of flocking behaviour. More details on the implementation of this can be found in section 3.
2. Program Design
2.1 Object Oriented Programming
This flocking simulation program is designed using key object-oriented programming principles that collectively contribute to a robust and maintainable codebase for the flocking simulation. The use of class inheritance, polymorphism, and encapsulation allows for code reuse and extension, flexible behaviour based on context, and data hiding and integrity.
Inheritance: Inheritance is used to define a hierarchy of classes where the base class Turtle provides fundamental behaviours (e.g., movement, turning). DynamicTurtle and Predator extend Turtle to inherit these behaviours and add specific functionalities for agents and predators.
Polymorphism: Polymorphism is applied in the simulation where methods like drawSelf and avoidObstacle are overridden in subclasses to provide specific implementations. This allows for flexible behaviour based on the object type at runtime.
Encapsulation: Encapsulation is managed by using private and protected fields and providing public methods for accessing and modifying these fields. This ensures that the objects’ internal parameters are less likely to be altered unknowingly, which could affect the behaviour of the program.
2.2 Use of Data Structures - Lists
The List interface is extensively used to manage collections of agents, predators, and obstacles. Specifically, ArrayList implementations of the List interface are utilised due to their dynamic resizing capabilities. Synchronisation wrappers are used around ArrayList instances to create synchronised versions of the lists. This is crucial for ensuring thread safety when multiple threads access or modify these lists concurrently. The Collections.synchronizedList() method is used for this purpose.
2.2 Modularity Class Structure
The design ensures modularity by separating concerns into distinct classes and packages. Each class has a single responsibility, making it easier to extend and maintain the codebase. For example, DynamicTurtle is responsible for agent-specific behaviours, while Predator handles predator-specific behaviours. This modularity allows for easy addition of new features without affecting existing functionality.
Drawing Package
Canvas.java: Handles all the drawing operations for the simulation. Provides methods to draw lines, clear the canvas, and manage the graphical representation of the simulation. It serves as the primary interface for rendering the simulation.
Elements Package
DynamicTurtle.java: Inherits from Turtle. This class represents the agents in the simulation that exhibit flocking behaviour. Implements methods for flocking (flock), avoiding obstacles (avoidObstacle), and fleeing from predators (fleePredators).
Obstacle.java: An interface that defines the basic structure for obstacles in the simulation. Implemented by specific obstacle classes like Rectangle.
Predator.java: Inherits from Turtle. Represents predators in the simulation with behaviours to hunt DynamicTurtle agents. It includes methods for chasing agents (hunt) and avoiding obstacles (predatorAvoidObstacle).
Rectangle.java: Implements Obstacle. Represents rectangular obstacles in the simulation. Includes methods to draw and undraw the obstacle.
Turtle.java: The base class for all movable entities in the simulation. Provides fundamental methods for movement (move), turning (turn), and drawing (drawSelf, undraw). This class serves as the parent class for DynamicTurtle and Predator.
flockingsimulationprogram Package
FlockingGuiSetup.java: Handles the setup of the graphical user interface. Configures the window, sliders, buttons, and other UI elements that allow users to interact with the simulation.
FlockingProgram.java: The main class that runs the simulation. Contains the main loop that updates and renders the simulation, handles user input, and manages the interactions between agents, predators, and obstacles.
Geometry Package
CartesianCoordinate.java: Represents a point in Cartesian coordinates (x, y). Provides methods to get and set the coordinates and a toString method for representation.
LineSegment.java: Represents a line segment defined by a start point and an end point. Provides methods to calculate the length of the line segment and the distance from a point to the line segment.
Tools Package
Utils.java: Contains utility methods used throughout the simulation. These could include mathematical functions, helper methods for timing (pause), or other common functionalities.
2.3 The Design for Future Extensions
The design is prepared for future extensions in several ways:
New Types of Obstacles: Implement new classes that conform to the Obstacle interface.
Additional Agents: Extend the DynamicTurtle class to introduce new agent types with unique behaviours.
Enhanced Flocking Rules: Modify or add methods in the DynamicTurtle class to change flocking dynamics.
GUI Enhancements: Update the FlockingGuiSetup class to include new user interface elements for controlling additional parameters or features.
2.4 Additional Behaviours
Predator Avoidance: Predators influence the movement of agents by introducing a threat that agents must avoid. When a predator comes within a certain radius of an agent, the agent attempts to flee. This involves increasing the altering its direction to move away from the predator.
Obstacle Avoidance: Agents steer away from obstacles to prevent collisions. This involves calculating the distance between the agent and the obstacle and adjusting the agent's direction if the obstacle is too close. The agent turns away smoothly from the obstacle, ensuring it avoids collisions without abrupt changes in direction.
3. Program Implementation
3.1 GUI Setup
The components used for the Graphical User Interface(GUI) were[2]:
JFrame: The main window of the application. It serves as the container for all other GUI components.
JPanel: Panels are used to organize and group other components within the JFrame. In this project, several JPanels are used to hold sliders and buttons.
JSlider: Sliders provide a way to adjust parameters such as agent speed and flocking coefficients interactively.
JButton: Buttons allow users to add agents, obstacles, predators, and reset the simulation.
The layout management used for arranging components includes:
BorderLayout: Used to arrange the main sections of the GUI, such as the canvas in the center, sliders on the left, and buttons at the bottom.
GridLayout: Used within JPanels to organize sliders and buttons neatly, ensuring they are properly aligned and spaced.
3.2 Event Handling
Actions
Event handling is managed through ActionListeners and ChangeListeners:
ActionListeners for Buttons: Each button has an associated ActionListener that defines the action to be performed when the button is clicked, such as adding or removing agents, obstacles, or predators.
ChangeListeners for Sliders: Each slider has an associated ChangeListener that updates the corresponding parameter in the simulation when the slider value is changed, allowing for real-time adjustments.

3.3 Main Simulation Loop
Synchronisation
Synchronisation mechanisms are used to manage concurrent modifications to shared resources. This includes synchronised blocks and synchronised collections to ensure thread safety during updates.
Update Cycle
The main loop continuously updates and renders the simulation:
Undraw Agents: Remove the current drawing of agents from the canvas.
Draw Obstacles: Draw the static obstacles on the canvas.
Flee Predators: Check if agents need to flee from predators and mark them for removal if necessary.
Update and Draw Agents: Update the state of each agent based on the flocking parameters, their position, and speed, and redraw them on the canvas.
Update and Draw Predators: Update the state of each predator and redraw them on the canvas.
Pause: Introduce a small delay to control the simulation speed.
In the implementation of the flocking simulation, a separate list for removing agents - called “removeAgents” , is used to manage the deletion of agents safely within the main simulation loop when they are caught by a predator. This approach addresses the fact that directly removing agents from the main list (agents) while iterating over it can lead to concurrent modification exceptions. This occurs because modifying a list while iterating through it disrupts the iteration process, causing runtime errors and unpredictable behaviour. By using a separate removeAgents list, we can safely mark agents for removal without altering the primary list during iteration.
3.4 Key Methods
The flock() method implements the flocking behaviour of agents based on cohesion, alignment, and separation.
Cohesion: The program calculates the average position of all neighbouring agents within a specified radius (cohesionRadius). This is done by summing the x and y coordinates of these neighbours and dividing by the number of neighbours to find the centre of gravity. The vector from the current agent to this average position is converted to an angle in degrees using Math.atan2. The agent's direction is adjusted smoothly towards this average position by calculating the angle difference, normalising it to the range [-180, 180], and applying a turn rate and a cohesion coefficient (k_c).
Alignment: Alignment steers agents towards the average direction of their neighbours. The program calculates the average direction of all neighbouring agents within a specified radius (alignmentRadius). It sums their directions, divides by the number of neighbours, and adjusts the agent's direction smoothly towards this average direction. The angle difference is normalised to [-180, 180] and modified using a turn rate and an alignment coefficient (k_a).
Separation: Separation prevents collisions by steering agents away from close neighbours. The program calculates a repelling vector from each neighbour within a specified radius (separationRadius), inversely proportional to the square of the distance. These vectors are averaged, and the agent's direction is adjusted smoothly away from the average position. The angle difference is normalized to [-180, 180], and the turn is applied using a turn rate and a separation coefficient (k_s).

The avoidObstacle() method ensures that agents steer away from obstacles to prevent collisions. This involves calculating the distance from the agent to each obstacle and adjusting the agent's direction if an obstacle is too close.
The hunt() method allows predators to chase agents when they are within a certain radius. Predators increase their speed and adjust their direction to follow the agents. If no agents are within the hunt radius, the predator returns to its normal prowling speed.

4. Integration and Testing
Integration
Integration of different components was done incrementally, with each new feature tested independently before combining it with others. The main approach involved ensuring that agents, predators, and obstacles interacted correctly.
Testing Strategies
Debugger Tool: Used to check the values of variables and ensure conditions were met correctly.
Print Statements: Added throughout the code to verify the flow of execution and the correctness of logic.
Performance Testing: Performance testing involved gradually increasing the number of agents and obstacles to see if the simulation remained responsive. 
Program Behaviour TestsBehavioural observations were crucial for verifying that agents and predators exhibited realistic behaviours. This included checking flocking behaviour, obstacle avoidance, predator chasing, edge wrapping, and handling of parameter extremes.
Observations
With the initial number of agents and obstacles (100 agents, 3 obstacles and 1 predator), the simulation ran smoothly without any noticeable lag or delay. Even when increasing these numbers, to a point, the program remained smooth and responsive. When numbers become extreme (e.g. 300 agents, 20 obstacles and 10 predators) the user will notice some flashing as the number of lines that need to be rendered to the screen increases.

In terms of appearance and the success of the flocking behaviour, it can be attested that all 3 parameters work as intended and adjusting the parameters with the sliders alters the separation, cohesion and alignment factors accordingly. However a major quirk of the program is what happens when all agents have been caught by the predators. In this scenario it seems that the predators continue to draw themselves without undrawing from the screen before updating, resulting in multiple predators being placed on the screen as seen in the figure below. The fix that has been implemented currently is a prompt to the terminal for the user to hit the reset button to restart the simulation with initial parameters. Though outside the scope of the project, altering the canvas to keep track of the line segments and what element they belong to may in the future be an aid to the problem.
Conclusions
The performance testing demonstrated that the flocking simulation is capable of handling a moderate number of agents and obstacles while maintaining responsiveness and realistic behaviour. However, as the number of elements increases, it is clear that more code optimisations would become necessary to ensure a smooth and efficient simulation. The insights gained from performance testing provide a foundation for future improvements and enhancements to the simulation's scalability and efficiency.


References:

[1] "Flocking," Wikipedia. Available: https://en.wikipedia.org/wiki/Flocking. [Accessed: 26-May-2024]
[2]  Oracle, "JComponent (Java SE 11 & JDK 11)," Oracle Documentation. [Online]. Available: https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/javax/swing/JComponent.html. [Accessed: 31-May-2024].


