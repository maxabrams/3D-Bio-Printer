import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TemperaturePanel {
	private final static String STOP_BUTTON_TEXT = "Stop"; 
	private final static String START_BUTTON_TEXT = "Run"; 
	private final static String TEMP_PANEL_NAME = "Temperature";
	private JButton startExperiment;
	private JButton stopExperiment;
	private JLabel statusLabel = new JLabel("Status: ");
	private JLabel statusField = new JLabel("Initialized");
	private TempControl controlThread;
	private JTextField targetField;
	private JTextField thresholdField;
	private int target = 20;
	private int threshold = 2;
	private JPanel mainPanel;
	private JLabel output;
	private JLabel tmpLabel = new JLabel("Target Temp:");
	private boolean targetFocus;
	
	public TemperaturePanel(){
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout()); //Add a layout manager to align buttons as we resize
		output = new JLabel();
		//Start button
		startExperiment = new JButton(START_BUTTON_TEXT);
		startExperiment.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) { //Add Action listener to respond to button
				updateStatus("Running");
				stop(); //Make sure no existing thread
				controlThread = new TempControl(statusLabel, output);
				controlThread.updateThreshold(Double.parseDouble(thresholdField.getText()));
				controlThread.updateTarget(Double.parseDouble(targetField.getText()));
				new Thread(controlThread).start();
			}
		});
		
		//Stop button
		stopExperiment = new JButton(STOP_BUTTON_TEXT);
		stopExperiment.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) { //Add Action listener to respond to button
				updateStatus("Stopped");
				stop();
			}
		});
		
		//Temp Input
		targetFocus = false;
		targetField = new JTextField(3);
		targetField.setText(""+target);
		targetField.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				if(targetFocus == false){ //TODO: improve click options
					targetFocus = true;
					new Keyboard("0123456789", targetField, 4, 3, mainPanel);
				}else{
					targetFocus = false;
					
					//Send enter key for update
					/*
					try { 
					    Robot robot = new Robot(); 
					    robot.keyPress(KeyEvent.VK_ENTER); 
					} catch (AWTException ee) { 
						ee.printStackTrace(); 
					} */
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				
			}});
		
		targetField.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  if(controlThread!=null){
		    		  controlThread.updateTarget(Double.parseDouble(targetField.getText())); //If enter key is required, can make this a function
		    	  }
		        }
		      });
		
		//Threshold input (deleted once testing is done)
		thresholdField = new JTextField(3);
		thresholdField.setText(""+threshold);
		thresholdField.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  if(controlThread!=null){
		    		  controlThread.updateThreshold(Double.parseDouble(thresholdField.getText()));
		    	  }
		      }
		      });
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;	
		c.anchor= GridBagConstraints.EAST;
		mainPanel.add(tmpLabel,c);
		
		c.gridy = 0;	
		c.gridx = 1;		
		c.anchor= GridBagConstraints.WEST;
		mainPanel.add(targetField,c);
		
		JLabel blank= new JLabel("  ");
		c.gridy = 0;	
		c.gridx = 2;	
		c.ipadx= 40;
		mainPanel.add(blank,c);

		
		c.gridy = 0;	
		c.gridx = 3;
		mainPanel.add(startExperiment,c);

		
		c.gridy = 1;	
		c.gridx = 0;
		c.anchor= GridBagConstraints.EAST;
		//status: error, running?
		mainPanel.add(statusLabel,c);
		
		c.gridy = 1;	
		c.gridx = 1;		
		c.anchor= GridBagConstraints.WEST;
		//initialized, running, stop
		mainPanel.add(statusField,c);
		
		
		c.gridy = 1;	
		c.gridx = 3;
		mainPanel.add(stopExperiment,c);

		c.gridy = 2;	
		c.gridx = 0;
		//mainPanel.add(thresholdField); DO NOT USE THRESHOLD FIELD AFTER BETA TESTING. Set to specified value
		JLabel outLabel= new JLabel("Current temperature: ");
		c.anchor= GridBagConstraints.EAST;
		mainPanel.add(outLabel,c);
		
		c.gridy = 2;	
		c.gridx = 1;
		c.anchor= GridBagConstraints.WEST;
		mainPanel.add(output,c);
	}
	
	public JPanel getPanel(){
		return mainPanel;
	}
	
	public String getPanelName(){
		return TEMP_PANEL_NAME;
	}
	
	private void updateStatus(String newStatus){
		statusField.setText(newStatus);
	}
	
	private void stop(){
		if(controlThread != null){
			controlThread.shutdown();
			controlThread = null;
		}
	}
	
	public void paint(Graphics g){
		//Draw a Box to show color
		g.drawRect(100, 100, 40, 40);
		g.setColor(Color.BLACK);
	}

}
