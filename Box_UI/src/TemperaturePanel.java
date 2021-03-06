import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class TemperaturePanel {
	private final static String STOP_BUTTON_TEXT = "Temp Control Off"; 
	private final static String START_BUTTON_TEXT = "Temp Control On"; 
	private final static String TEMP_PANEL_NAME = "Temperature";
	private JButton startExperiment;
	private JButton stopExperiment;
	private JLabel statusLabel = new JLabel("Status: Initialized");
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
		startExperiment.setName("ExpButton");
		//startExperiment.setEnabled(true);
		//startExperiment.setBorder( BorderFactory.createRaisedBevelBorder());
		startExperiment.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) { //Add Action listener to respond to button
				updateStatus("Running");
				stop(); //Make sure no existing thread
				controlThread = new TempControl(statusLabel, output);
				controlThread.updateThreshold(Double.parseDouble(thresholdField.getText()));
				controlThread.updateTarget(Double.parseDouble(targetField.getText()));
				new Thread(controlThread).start();
				//startExperiment.setEnabled(false);
				//startExperiment.setBorder( BorderFactory.createLoweredBevelBorder());
				//stopExperiment.setBorder( BorderFactory.createRaisedBevelBorder());
				//stopExperiment.setEnabled(true);
			}
		});
		
		//Stop button
		stopExperiment = new JButton(STOP_BUTTON_TEXT);
		stopExperiment.setName("ExpButton");
		//stopExperiment.setEnabled(false);
		//stopExperiment.setBorder( BorderFactory.createLoweredBevelBorder());

		stopExperiment.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) { //Add Action listener to respond to button
				//updateStatus("Stopped");
				stop();
				//startExperiment.setEnabled(true);
				//stopExperiment.setEnabled(false);
				
				//startExperiment.setBorder( BorderFactory.createRaisedBevelBorder());
				//stopExperiment.setBorder( BorderFactory.createLoweredBevelBorder());
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
		c.weightx=1;
		c.fill= GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.ipady=80;
//		c.insets=new Insets(0,100,0,0);
		c.anchor= GridBagConstraints.CENTER;
		tmpLabel.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(tmpLabel,c);
		
		c.ipady=0;
		c.ipadx=100;
		c.gridy = 2;	
		c.gridx = 0;		
		c.anchor= GridBagConstraints.CENTER;
		targetField.setFont(new Font("Ariel", Font.BOLD, 45 ));
		targetField.setHorizontalAlignment(JTextField.CENTER);
		targetField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		mainPanel.add(targetField,c);
		
		JLabel blank= new JLabel("   ");
		c.ipady=30;
		c.anchor=GridBagConstraints.NORTH;
		c.gridx=1;
		c.gridy=3;
		c.ipadx=40;
		mainPanel.add(blank,c);
		
		c.fill= GridBagConstraints.HORIZONTAL;

		c.gridy = 4;	
		c.gridx = 0;
		c.ipadx=12;
		c.ipady=25;
		startExperiment.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		mainPanel.add(startExperiment,c);

		c.ipadx=500;
		c.gridy = 0;	
		c.gridx = 0;
		c.ipady=0;
		c.gridwidth=4;
		statusLabel.setBackground(new Color(127,183,190));
		statusLabel.setFont(new Font("Ariel", Font.PLAIN, 25));
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
		c.anchor= GridBagConstraints.NORTH;
		mainPanel.add(statusLabel,c);
		
		c.anchor=GridBagConstraints.CENTER;
		c.gridwidth=1;
		c.fill=GridBagConstraints.NONE;
		c.gridy = 4;	
		c.gridx = 2;
		c.ipadx=30;
		c.ipady=30;
		stopExperiment.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		mainPanel.add(stopExperiment,c);

		c.ipadx=0;
		c.ipady=0;
		c.gridy = 1;	
		c.gridx = 2;
		//mainPanel.add(thresholdField); DO NOT USE THRESHOLD FIELD AFTER BETA TESTING. Set to specified value
		JLabel outLabel= new JLabel("Current temperature: ");
		c.anchor= GridBagConstraints.CENTER;
		mainPanel.add(outLabel,c);
		
		c.gridy = 2;	
		c.gridx = 2;
		c.anchor= GridBagConstraints.CENTER;
		output.setFont(new Font("Ariel", Font.BOLD, 45 ));
		output.setText("" );
		mainPanel.add(output,c);
		stopExperiment.setBorder( BorderFactory.createLoweredBevelBorder());

	}
	
	public String getTempOutput(){
		return output.getText();
	}

	public JPanel getPanel(){
		return mainPanel;
	}
	
	public String getPanelName(){
		return TEMP_PANEL_NAME;
	}
	
	private void updateStatus(String newStatus){
		statusLabel.setText("Status: " + newStatus);
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
