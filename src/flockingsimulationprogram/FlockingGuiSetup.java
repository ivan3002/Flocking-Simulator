package flockingsimulationprogram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;



import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


//import javax.swing.SwingConstants;


import drawing.Canvas;



public class FlockingGuiSetup {


		protected static final int WINDOW_X_SIZE = 1500;
		protected static final int WINDOW_Y_SIZE = 800;
		
	
		private static final int AGENTSPEEDSLIDER_MIN = 0;
		private static final int AGENTSPEEDSLIDER_MAX = 1000;
		private static final int COHESIONCOEFFICIENTSLIDER_MIN = 0; 
		private static final int COHESIONCOEFFICIENTSLIDER_MAX = 100;
		private static final int ALIGNMENTCOEFFICIENTSLIDER_MIN = 0;
		private static final int ALIGNMENTCOEFFICIENTSLIDER_MAX = 100;
		private static final int SEPARATIONCOEFFICIENTSLIDER_MIN = 0;
		private static final int SEPARATIONCOEFFICIENTSLIDER_MAX = 100;
		private static final int COHESIONRADIUSLIDER_MIN = 0;
		private static final int COHESIONRADIUSSLIDER_MAX = 500;
		private static final int ALIGNMENTRADIUSSLIDER_MIN = 0;
		private static final int ALIGNMENTRADIUSSLIDER_MAX = 500;
		private static final int SEPARATIONRADIUSSLIDER_MIN = 0;
		private static final int SEPARATIONRADIUSSLIDER_MAX = 500;
		

		
		//Instantiating buttons
		protected JButton addAgentButton = new JButton("Add Agent");
		protected JButton addObstacleButton = new JButton("Add Obstacle");
		protected JButton addPredatorButton = new JButton("Add Predator");
		protected JButton resetButton = new JButton("RESET");
		
		//Inital value of the slider is set by the 4th argument
		protected JSlider agentSpeedSlider = new JSlider(JSlider.VERTICAL, AGENTSPEEDSLIDER_MIN,AGENTSPEEDSLIDER_MAX,300); //sliders (orientation, min, max, init value)
		protected JSlider cohesionCoefficientSlider = new JSlider(JSlider.VERTICAL, COHESIONCOEFFICIENTSLIDER_MIN,COHESIONCOEFFICIENTSLIDER_MAX,80);
		protected JSlider alignmentCoefficientSlider = new JSlider(JSlider.VERTICAL, ALIGNMENTCOEFFICIENTSLIDER_MIN,ALIGNMENTCOEFFICIENTSLIDER_MAX,70);
		protected JSlider separationCoefficientSlider = new JSlider(JSlider.VERTICAL, SEPARATIONCOEFFICIENTSLIDER_MIN,SEPARATIONCOEFFICIENTSLIDER_MAX,70);
		protected JSlider cohesionRadiusSlider = new JSlider(JSlider.VERTICAL, COHESIONRADIUSLIDER_MIN,COHESIONRADIUSSLIDER_MAX,200);
		protected JSlider alignmentRadiusSlider = new JSlider(JSlider.VERTICAL, ALIGNMENTRADIUSSLIDER_MIN,ALIGNMENTRADIUSSLIDER_MAX,70);
		protected JSlider separationRadiusSlider = new JSlider(JSlider.VERTICAL, SEPARATIONRADIUSSLIDER_MIN,SEPARATIONRADIUSSLIDER_MAX,50);
		

		
		//instantiating the canvas on which to draw turtles
		protected Canvas canvas = new Canvas();

		
		//setting up GUI elements
		protected void setupGUI() {
			
			canvas.setBackground(Color.WHITE);
			//Instantiating GUI elements
			JFrame frame = new JFrame();
			frame.setTitle("Flocking Simulator");
			frame.setSize(WINDOW_X_SIZE, WINDOW_Y_SIZE);
			frame.setLayout(new BorderLayout());
			frame.setDefaultCloseOperation(3); //3 for exit on close
			
			
			JLabel agentSpeedLabel = new JLabel("Agent Speed"); //spacing to align
			JLabel cohesionCoefficientLabel = new JLabel("Cohesion Coefficient");
			JLabel alignmentCoefficientLabel = new JLabel("Alignment Coefficient");
			JLabel separationCoefficientLabel = new JLabel("Seperation Coefficient");
			JLabel cohesionRadiusLabel = new JLabel("Cohesion Radius");
			JLabel alignmentRadiusLabel = new JLabel("Alignment Radius");
			JLabel separationRadiusLabel = new JLabel("Separation Radius");
			
			agentSpeedSlider.setPaintLabels(true);
			agentSpeedSlider.setLabelTable(agentSpeedSlider.createStandardLabels(500, 0));//the setLabelTable method takes argument (increment, start)
			agentSpeedSlider.setMajorTickSpacing(100);
			
			cohesionCoefficientSlider.setPaintLabels(true);
			cohesionCoefficientSlider.setLabelTable(cohesionCoefficientSlider.createStandardLabels(50, 0));
			cohesionCoefficientSlider.setMajorTickSpacing(100);
			
			alignmentCoefficientSlider.setPaintLabels(true);
			alignmentCoefficientSlider.setLabelTable(alignmentCoefficientSlider.createStandardLabels(50, 0));
			alignmentCoefficientSlider.setMajorTickSpacing(100);
			
			separationCoefficientSlider.setPaintLabels(true);
			separationCoefficientSlider.setLabelTable(separationCoefficientSlider.createStandardLabels(50, 0));
			separationCoefficientSlider.setMajorTickSpacing(100);
			
			cohesionRadiusSlider.setPaintLabels(true);
			cohesionRadiusSlider.setLabelTable(cohesionRadiusSlider.createStandardLabels(250, 0));
			cohesionRadiusSlider.setMajorTickSpacing(100);
			
			alignmentRadiusSlider.setPaintLabels(true);
			alignmentRadiusSlider.setLabelTable(alignmentRadiusSlider.createStandardLabels(250, 0));
			alignmentRadiusSlider.setMajorTickSpacing(100);
			
			separationRadiusSlider.setPaintLabels(true);
			separationRadiusSlider.setLabelTable(separationRadiusSlider.createStandardLabels(250, 0));
			separationRadiusSlider.setMajorTickSpacing(100);
			
			

			
			JPanel leftPanel = new JPanel();
			leftPanel.setLayout(new GridLayout(7,2, 20, 20));  //(rows, columns, horizonal gap and veritcal gap);
			
			
			int sliderPadding = 10;
			int buttonPadding = 30;
		    leftPanel.setBorder(BorderFactory.createEmptyBorder(sliderPadding, sliderPadding, sliderPadding, sliderPadding));
		    

		    JPanel buttonsPanel = new JPanel();
		    buttonsPanel.setLayout(new GridLayout(1, 4, 10, 10)); // 1 row, 4 columns, with gaps
		    buttonsPanel.setBorder(BorderFactory.createEmptyBorder(buttonPadding, buttonPadding, buttonPadding, buttonPadding));
		    buttonsPanel.add(resetButton);
		    buttonsPanel.add(addAgentButton);
		    buttonsPanel.add(addObstacleButton);
		    buttonsPanel.add(addPredatorButton);
		   
		    
			
		    leftPanel.add(agentSpeedLabel);
		    leftPanel.add(agentSpeedSlider);
		    
		    leftPanel.add(cohesionCoefficientLabel);
		    leftPanel.add(cohesionCoefficientSlider);
		    
			leftPanel.add(separationCoefficientLabel);
			leftPanel.add(separationCoefficientSlider);
			
			leftPanel.add(alignmentCoefficientLabel);
			leftPanel.add(alignmentCoefficientSlider);
			
			leftPanel.add(cohesionRadiusLabel);
			leftPanel.add(cohesionRadiusSlider);
			
			leftPanel.add(separationRadiusLabel);
			leftPanel.add(separationRadiusSlider);
			
			leftPanel.add(alignmentRadiusLabel);
			leftPanel.add(alignmentRadiusSlider);
		
			frame.add(canvas, BorderLayout.CENTER);
			frame.add(leftPanel, BorderLayout.WEST);
			frame.add(buttonsPanel, BorderLayout.SOUTH);
			//frame.pack();
			frame.setVisible(true);
		}			
		
}
