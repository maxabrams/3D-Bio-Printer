import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LightingPanel extends JPanel {
	private JPanel lightPanel; // Panel to make modifications to
	private String lightName; // Name for panel
	private JButton advancedButton;
	private static final String PATH_TO_LED_SCRIPT = "/home/pi/py/strip.py";
	private static final int RED_PIN = 16; //GPIO Pin
	private static final int GREEN_PIN = 20; //GPIO Pin
	private static final int BLUE_PIN = 21; //GPIO Pin
	private final String SET_BUTTON_TEXT = "Advanced Settings";
	private JSlider redSlider;
	private JSlider blueSlider;
	private JSlider greenSlider;
	private JSpinner redBox;
	private JSpinner blueBox;
	private JSpinner greenBox;
	private final String RED_LABEL_TEXT = "Red:   ";
	private final String GREEN_LABEL_TEXT = "Green:";
	private final String BLUE_LABEL_TEXT = "Blue:  ";

	public LightingPanel() {
		
		try{
			Process ledSet = Runtime.getRuntime().exec("sudo pigpiod");
		}catch(IOException error){
			System.out.println("Error! Could not start LED Service");
		}
		// Initialize components
		lightPanel = new JPanel();
//		lightPanel.setLayout(new GridLayout(4, 1)); // Add a layout manager to align components

		// Initialize Grid bag layout		
		lightPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
	
		
		lightName = "Lighting"; // Assign name

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
		
		//box for color preview based on RGB values set with sliders
		final JButton previewButton = new JButton("Current Setting");
		previewButton.setPreferredSize(new Dimension(40,40));
		previewButton.setBackground(new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));
		previewButton.setOpaque(true);
//		previewButton.setEnabled(false);
		previewButton.setName("Preview");
		
		// blank space to center the instructions
//		JLabel blank= new JLabel("    ");
//		c.gridx = 0;
//		c.gridy = 2;
//		c.ipadx=170;
//		c.ipady=30;
//		lightPanel.add(blank, c);

		JLabel advancedOptions=new JLabel("Advanced");
		c.gridx=0;
		c.gridy=2;
		advancedOptions.setFont(new Font("Arial", Font.PLAIN, 15));

		lightPanel.add(advancedOptions,c);
		
		
		JLabel inst= new JLabel("Select desired lighting color");
		inst.setFont(new Font("Arial", Font.BOLD, 20));
//		c.weightx = 1.0;
//		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		c.insets= new Insets(5,5,5,100);
		lightPanel.add(inst, c);
		// Preset colors
		JPanel colorPresets= new JPanel();
		colorPresets.setLayout(new GridBagLayout());
		
		ArrayList<Color> colors= new ArrayList <Color>(Arrays.asList(new Color(255,0,0),new Color(0,255,0),new Color(0,0,255), new Color(255,255,0) , new Color(255,255,255) ,new Color(0,0,0) ));
		int count=0;
		for(final Color col:colors){
			// label each color with word
			final JButton Preset = new JButton();
			Preset.setPreferredSize(new Dimension(60,60));
			Preset.setOpaque(true);
			Preset.setBackground(col);
			Preset.setFocusable(false);
			Preset.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			
			Preset.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent a) {
					redSlider.setValue(col.getRed());
					blueSlider.setValue(col.getBlue());
					greenSlider.setValue(col.getGreen());
					previewButton.setBackground(new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));
					setLights();
				}
			});

			if(count>2){
				c.gridx=count%3;
				c.gridy=1;
			}
			else{
				c.gridx=count;
				c.gridy=0;
			}
			c.ipady=70;
			c.ipadx=70;
			c.insets= new Insets(10,10,10,10);
			c.gridwidth=1;
			count++;
			colorPresets.add(Preset,c);
		}
		c.insets= new Insets(0,0,-10,0);

		c.gridx=1;
		c.gridy=1;
		c.weighty=1.0;
		c.gridwidth=2;
		c.ipady=60;
		c.fill= GridBagConstraints.VERTICAL;
		c.anchor=GridBagConstraints.SOUTH;
//		colorPresets.setBackground(Color.BLUE);
		lightPanel.add(colorPresets, c);
		
	
		
		//Add update listeners to sliders
		redSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				redBox.setValue(redSlider.getValue());
				previewButton.setBackground(new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));
				setLights();
			}
			
		});
		
		blueSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				blueBox.setValue(blueSlider.getValue());
				previewButton.setBackground(new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));
				setLights();
			}
			
		});
		
		greenSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				greenBox.setValue(greenSlider.getValue());
				previewButton.setBackground(new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));	
				setLights();
			}
			
		});
		
		//Add listeners to box
		redBox.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				redSlider.setValue((int) (redBox.getValue()));
				previewButton.setBackground(new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));
				setLights();
			}
			
		});
		
		blueBox.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				blueSlider.setValue((int) (blueBox.getValue()));
				previewButton.setBackground(new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));	
				setLights();
			}
			
		});
		
		greenBox.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				greenSlider.setValue((int) (greenBox.getValue()));
				previewButton.setBackground(new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));	
				setLights();
			}
			
		});
		///////////////////////////////////////
		
		//Add "advanced" button
		advancedButton = new JButton(SET_BUTTON_TEXT);
		advancedButton.addActionListener(new ActionListener() { // Add action listener to respond to button and SET LEDS

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(
					    null,
					    "Would you like green eggs and ham?",
					    "An Inane Question",
					    JOptionPane.YES_NO_OPTION);				
//			            Process ledSet = Runtime.getRuntime().exec("sudo python " + PATH_TO_LED_SCRIPT + " " + RED_PIN  + " " + GREEN_PIN  + " " + BLUE_PIN + " " + String.valueOf(redBox.getValue()) +  " " + String.valueOf(greenBox.getValue()) + " " + String.valueOf(blueBox.getValue()));
//			        }catch(IOException error){
//			              System.out.println("Error! Could not set LED levels");
//			        }
								 
				
			}

		});
		
		/////////////////////////////////////////////////
		
		Font boldFont = new Font("Ariel", Font.BOLD, 16);

		//Make separate panels for easily adding /arranging to main panel
		JPanel redPanel = new JPanel(new BorderLayout());
		JLabel rl= new JLabel(RED_LABEL_TEXT);
		rl.setForeground(new Color(255,0,0));
		rl.setFont(boldFont);
		redPanel.add(rl, BorderLayout.WEST);
		redPanel.add(redSlider, BorderLayout.CENTER);
		redPanel.add(redBox, BorderLayout.EAST);
		
		JPanel greenPanel = new JPanel(new BorderLayout());
		JLabel gl= new JLabel(GREEN_LABEL_TEXT);
		gl.setForeground(new Color(100,255,100));
		gl.setFont(boldFont);
		greenPanel.add(gl, BorderLayout.WEST);
		greenPanel.add(greenSlider, BorderLayout.CENTER);
		greenPanel.add(greenBox, BorderLayout.EAST);
		
		
		JPanel bluePanel = new JPanel(new BorderLayout());
		JLabel bl= new JLabel(BLUE_LABEL_TEXT);
		bl.setForeground(new Color(0,0,255));
		bl.setFont(boldFont);
		bluePanel.add(bl, BorderLayout.WEST);
		bluePanel.add(blueSlider, BorderLayout.CENTER);
		bluePanel.add(blueBox, BorderLayout.EAST);
	
				
		//Add individual panels to main panel 
		c.gridwidth = count;

		c.gridx=0;
		c.ipadx=500;
		c.ipady=40;
		c.gridy=2;
//		lightPanel.add(advancedButton,c);
//		lightPanel.add(redPanel,c);

		c.gridy=3;
//		lightPanel.add(greenPanel, c);
		
		c.gridy=4;
//		lightPanel.add(bluePanel, c);
		
//		c.gridy=5;
//		c.ipady=20;
//		lightPanel.add(setButton, c);
		
		//add preview Button
		c.gridy=5;
		c.ipady=10;
		c.ipadx=100;
		c.insets= new Insets(0,0,20,0 );
		lightPanel.add(previewButton,c);
	}
//	public void paintComponent(Graphics g){
//		System.out.println("hello");
//		super.paintComponent(g);
//		//Draw a Box to show color
//		g.drawRect(100, 300, 50, 50);
//		g.setColor(Color.BLACK);
//		g.fillRect(25, 55, 200, 100);
//		g.drawString("hello", 40, 40);
//	}
	public JPanel getPanel() {
		return this.lightPanel;
	}

	public String getPanelName() {
		return lightName;
	}
	
	public Color getLightColor(){
		return new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue());

	}
	public void setLights(){
		 try{
	            Process ledSet = Runtime.getRuntime().exec("sudo python " + PATH_TO_LED_SCRIPT + " " + RED_PIN  + " " + GREEN_PIN  + " " + BLUE_PIN + " " + String.valueOf(redBox.getValue()) +  " " + String.valueOf(greenBox.getValue()) + " " + String.valueOf(blueBox.getValue()));
	        }catch(IOException error){
	              System.out.println("Error! Could not set LED levels");
	        }
	}
}