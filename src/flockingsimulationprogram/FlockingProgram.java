package flockingsimulationprogram;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import elements.DynamicTurtle;
import elements.Obstacle;
import elements.Predator;
import elements.Rectangle;
import tools.Utils;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class FlockingProgram extends FlockingGuiSetup{
	
	private static final int INITOBJECT1WIDTH = 120;
	private static final int INITOBJECT1HEIGHT = 80;
	private static final int INITOBJECT2WIDTH = 80;
	private static final int INITOBJECT2HEIGHT = 150;
	private static final int INITOBJECT3WIDTH = 150;
	private static final int INITOBJECT3HEIGHT = 120;
	
	//declaring lists to be used
	private List<DynamicTurtle> agents;
	private List<DynamicTurtle>removeAgents; //since I cannot remove agents when caught within the for-each loop I move them to
													//this list before removal
	private List<Obstacle>obstacles;
	private List<Predator>predators;
	
	//initial number of turtles
	private int initTurtleNum = 100;
	
	
	private void setupAgents() {
		int x; 
		int y;
		agents = Collections.synchronizedList(new ArrayList<>());
		removeAgents = Collections.synchronizedList(new ArrayList<>());
		for(int i = 0; i<initTurtleNum; ++i) { //change num
			x = (int) (Math.random() * WINDOW_X_SIZE);
			y = (int) (Math.random() * WINDOW_Y_SIZE);
			agents.add(new DynamicTurtle(canvas,x ,y));
			agents.get(i).turn((int)(Math.random()*360));
		}
		
		
	}
	
	private void placeInitObjects() {
		obstacles = Collections.synchronizedList(new ArrayList<>());
		obstacles.add(new Rectangle(canvas,100,100, INITOBJECT1WIDTH,INITOBJECT1HEIGHT));
		obstacles.add(new Rectangle(canvas,350,200, INITOBJECT2WIDTH,INITOBJECT2HEIGHT));
		obstacles.add(new Rectangle(canvas,550,300, INITOBJECT3WIDTH,INITOBJECT3HEIGHT));
		
	}
	
	private void placeInitPredators() {
		predators = Collections.synchronizedList(new ArrayList<>());
		predators.add(new Predator(canvas,100,100));
	}

	private void setupEventActions() {
		
		//add agent button
		addAgentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x; 
				int y;
				synchronized (agents) {
					x = (int) (Math.random() * WINDOW_X_SIZE);
					y = (int) (Math.random() * WINDOW_Y_SIZE);
					agents.add(new DynamicTurtle(canvas,x ,y));
					agents.get(agents.size()-1).turn((int)(Math.random()*360));
					agents.get(agents.size()-1).setSpeed(agentSpeedSlider.getValue());
				}
			}
		});
	
		
		//add obstacle button
		addObstacleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				int topleft_x; 
				int topleft_y;
				synchronized (obstacles) {
					topleft_x = (int) (Math.random() * 1200);
					topleft_y = (int) (Math.random() * 600);
					obstacles.add(new Rectangle(canvas,topleft_x ,topleft_y,50,50));
					//obstacles.get(obstacles.size()-1).drawObstacle();
				}
			}
		});
		
		//add predator button
		addPredatorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e3) {
				int x; 
				int y;
				synchronized (predators) {
					x = (int) (Math.random() * 600);
					y = (int) (Math.random() * 600);
					predators.add(new Predator(canvas,x, y));
					//obstacles.get(obstacles.size()-1).drawObstacle();
				}
			}
		});
		
		//RESET button action handling
		resetButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e3) {
				synchronized (canvas) {
					canvas.clear();
					placeInitObjects();
					setupAgents();
					placeInitPredators();
					
				}
			}
		});
		
		//agent speed slider
		agentSpeedSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent a1) {
				synchronized (agents) {
					for (DynamicTurtle agent : agents) {
						agent.setSpeed(agentSpeedSlider.getValue());
						//System.out.println(agentSpeed.getValue());
					}	
				}
			}
			
		});
		
		cohesionCoefficientSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent a2) {
				synchronized (agents) {
					for (DynamicTurtle agent : agents) {
						agent.setK_c((double)cohesionCoefficientSlider.getValue()/100);
						//System.out.println(agentSpeed.getValue());
					}	
				}
			}
			
		});
		separationCoefficientSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent a3) {
				synchronized (agents) {
					for (DynamicTurtle agent : agents) {
						agent.setK_s((double) separationCoefficientSlider.getValue()/100);
						//System.out.println(separationCoefficient.getValue());
					}	
				}
			}
			
		});
		
		alignmentCoefficientSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent a4) {
				synchronized (agents) {
					for (DynamicTurtle agent : agents) {
						agent.setK_a((double)alignmentCoefficientSlider.getValue()/100);
						//System.out.println(agentSpeed.getValue());
					}	
				}
			}
			
		});
		
		alignmentRadiusSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent a5) {
				synchronized (agents) {
					for (DynamicTurtle agent : agents) {
						agent.setAlignmentRadius(alignmentRadiusSlider.getValue());
						//System.out.println(agentSpeed.getValue());
					}	
				}
			}
			
		});
		
		cohesionRadiusSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent a6) {
				synchronized (agents) {
					for (DynamicTurtle agent : agents) {
						agent.setCohesionRadius(cohesionRadiusSlider.getValue());
						//System.out.println(agentSpeed.getValue());
					}	
				}
			}
			
		});
		
		separationRadiusSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent a7) {
				synchronized (agents) {
					for (DynamicTurtle agent : agents) {
						agent.setSeparationRadius(separationRadiusSlider.getValue());
						//System.out.println(agentSpeed.getValue());
					}	
				}
			}
			
		});
	}
		
	
	private void mainLoop() {
		
		setupGUI();
		placeInitObjects();
		setupAgents();
		placeInitPredators();
		setupEventActions();
		
		
		int deltaTime = 30; //refresh rate in ms
		boolean continueRunning;
		continueRunning = true;
		
		while (continueRunning) {
	      
            synchronized(canvas) {
	        	if(agents.size()> 0) {
		            synchronized (agents) {
		                // Undraw agents
		                for (DynamicTurtle agent : agents) {
		                    agent.undraw();
		                }
		            }
	
		            synchronized (obstacles) {
		                // Draw obstacles
		                for (Obstacle obstacle : obstacles) {
		                    obstacle.drawObstacle();
		                }
		            }
		            
		            
		            
		            synchronized (agents) {
		            	removeAgents.clear();
		            	for(DynamicTurtle agent: agents) {

		            		agent.fleePredators(predators, removeAgents);
		            		
		            	} 		 
		            }
		            
		            // remove agents marked for removal
	                synchronized (agents) {
	                    for (DynamicTurtle removeAgent : removeAgents) {
	                    	if (agents.contains(removeAgent)) {
	                            removeAgent.undraw();
	                            agents.remove(removeAgent);
	                        }
	                        
	                    }
	                }
		            
		           
	
		            synchronized (agents) {
		                // Update and draw agents
		                for (DynamicTurtle agent : agents) {
		                    agent.flock(agents);
		                    
		                    agent.avoidObstacle(obstacles);
		                    agent.update(deltaTime);
		                    agent.wrapPosition(canvas.getWidth(), canvas.getHeight());
		                    agent.drawSelf();
		                    

		                }
		            }
		            
		          
		                // Update and draw predators
		           
	            }else{
					System.out.println("RESET PROGRAM!"); //error usually occurs when no agents on screen 
					
				}
	        	
	        	synchronized (predators) {
	                // Undraw predators
	                for (Predator predator : predators) {
	                    predator.undrawPredator(canvas);
	                }
	        	}
	        	
	            synchronized(predators) {
	                for (Predator predator : predators) {
	                    predator.hunt(agents);
	                    predator.predatorAvoidObstacle(obstacles);
	                    predator.updatePredator(deltaTime);
	                    predator.wrapPosition(canvas.getWidth(), canvas.getHeight());         
	                    
	                }
	            }
	            
	            synchronized(predators) {
	            	for (Predator predator :predators) {
	            		predator.drawPredator(); 
	            	}
	            }
		          

		            // Pause for the delta time
		            Utils.pause(deltaTime);
	        	}
	        	
			}
	        
	        
	      }
	
	

	public static void main(String[] args) {
		
		System.out.println("Running Program...");
		FlockingProgram flockSim = new FlockingProgram();
		flockSim.mainLoop();
		
		
	}

}
