import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LightingPanel {
	private JPanel lightPanel; // Panel to make modifications to
	private String lightName; // Name for panel
	private JButton setButton;
	private static final String PATH_TO_LED_SCRIPT = "/home/pi/py/strip.py";
	private static final int RED_PIN = 20; //GPIO Pin
	private static final int GREEN_PIN = 21; //GPIO Pin
	private static final int BLUE_PIN = 26; //GPIO Pin
	private final String SET_BUTTON_TEXT = "Set Lights";
	private JSlider redSlider;
	private JSlider blueSlider;
	private JSlider greenSlider;
	private JSpinner redBox;
	private JSpinner blueBox;
	private JSpinner greenBox;
	private final String RED_LABEL_TEXT = "Red:";
	private final String GREEN_LABEL_TEXT = "Green:";
	private final String BLUE_LABEL_TEXT = "Blue:";

	public LightingPanel() {
		// Initialize components
		lightPanel = new JPanel();
		lightPanel.setLayout(new GridLayout(4, 1)); // Add a layout manager to align components
		
		lightName = "Lighting Control"; // Assign name

		// Setup sliders
		redSlider = new JSlider(0,255);
		blueSlider = new JSlider(0,255);
		greenSlider = new JSlider(0,255);
		
		//Set paint components
		redSlider.setPaintTicks(true);
		blueSlider.setPaintTicks(true);
		greenSlider.setPaintTicks(true);
		
		redSlider.setMinorTickSpacing(5);
		blueSlider.setMinorTickSpacing(5);
		greenSlider.setMinorTickSpacing(5);
		
		redSlider.setMajorTickSpacing(10);
		blueSlider.setMajorTickSpacing(10);
		greenSlider.setMajorTickSpacing(10);
		
		// Setup spinners
		redBox = new JSpinner(new SpinnerNumberModel(redSlider.getValue(), 0, 255, 1)); //value, min, max, step
		blueBox = new JSpinner(new SpinnerNumberModel(blueSlider.getValue(), 0, 255, 1)); //Stick to slider value to avoid API changes 
		greenBox = new JSpinner(new SpinnerNumberModel(greenSlider.getValue(), 0, 255, 1));
		
		
		//Add update listeners to sliders
		redSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				redBox.setValue(redSlider.getValue());
				
			}
			
		});
		
		blueSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				blueBox.setValue(blueSlider.getValue());
				
			}
			
		});
		
		greenSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				greenBox.setValue(greenSlider.getValue());
				
			}
			
		});
		
		//Add listeners to box
		redBox.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				redSlider.setValue((int) (redBox.getValue()));
				
			}
			
		});
		
		blueBox.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				blueSlider.setValue((int) (blueBox.getValue()));
				
			}
			
		});
		
		greenBox.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				greenSlider.setValue((int) (greenBox.getValue()));
				
			}
			
		});
		
		//Add "set" button
		setButton = new JButton(SET_BUTTON_TEXT);
		setButton.addActionListener(new ActionListener() { // Add action listener to respond to button and SET LEDS

			@Override
			public void actionPerformed(ActionEvent e) {
				 try{
			            Process ledSet = Runtime.getRuntime().exec("sudo python " + PATH_TO_LED_SCRIPT + " " + RED_PIN  + " " + GREEN_PIN  + " " + BLUE_PIN + " " + String.valueOf(redBox.getValue()) +  " " + String.valueOf(greenBox.getValue()) + " " + String.valueOf(blueBox.getValue()));
			        }catch(IOException error){
			              System.out.println("Error! Could not set LED levels");
			        }
				

			}

		});

		//Make separate panels for easily adding /arranging to main panel
		JPanel redPanel = new JPanel(new BorderLayout());
		redPanel.add(new JLabel(RED_LABEL_TEXT), BorderLayout.NORTH);
		redPanel.add(redSlider, BorderLayout.CENTER);
		redPanel.add(redBox, BorderLayout.EAST);
		
		JPanel bluePanel = new JPanel(new BorderLayout());
		bluePanel.add(new JLabel(BLUE_LABEL_TEXT), BorderLayout.NORTH);
		bluePanel.add(blueSlider, BorderLayout.CENTER);
		bluePanel.add(blueBox, BorderLayout.EAST);
		
		JPanel greenPanel = new JPanel(new BorderLayout());
		greenPanel.add(new JLabel(GREEN_LABEL_TEXT), BorderLayout.NORTH);
		greenPanel.add(greenSlider, BorderLayout.CENTER);
		greenPanel.add(greenBox, BorderLayout.EAST);
				
		//Add individual panels to main panel 
		lightPanel.add(redPanel);
		lightPanel.add(greenPanel);
		lightPanel.add(bluePanel);
		lightPanel.add(setButton);
		
	}

	public JPanel getPanel() {
		return this.lightPanel;
	}

	public String getPanelName() {
		return lightName;
	}
}