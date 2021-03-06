package src;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Thermostat {

	private final static int SCREEN_WIDTH = 400;
	private final static int SCREEN_HEIGHT = 400;
	private final static String STOP_BUTTON_TEXT = "Stop"; 
	private final static String START_BUTTON_TEXT = "Run"; 
	private JButton startExperiment;
	private JButton stopExperiment;
	private JLabel statusLabel = new JLabel("Status: Initialized");
	private int status; //STATUS 0 = INIT, 1 = RUNNING, 2 = STOPPED
	private TempControl controlThread;
	private JTextField targetField;
	private JTextField thresholdField;
	private int target = 20;
	private int threshold = 2;
	public Thermostat(){
		status = 0;
		JFrame frame = new JFrame("Thermostat"); //Make a frame tabbed pane
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set exit by default when close 
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout()); //Add a layout manager to align buttons as we resize
		startExperiment = new JButton(START_BUTTON_TEXT);
		startExperiment.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) { //Add Action listener to respond to button
				updateStatus("Running");
				stop(); //Make sure no existing thread
				controlThread = new TempControl();
				controlThread.updateThreshold(Double.parseDouble(thresholdField.getText()));
				controlThread.updateTarget(Double.parseDouble(targetField.getText()));
				new Thread(controlThread).start();
				
			}
			
		});
		
		stopExperiment = new JButton(STOP_BUTTON_TEXT);
		stopExperiment.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) { //Add Action listener to respond to button
				updateStatus("Stopped");
				stop();
				
			}
			
		});
		
		targetField = new JTextField(3);
		targetField.setText(""+target);
		targetField.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  if(controlThread!=null){
		    		  controlThread.updateTarget(Double.parseDouble(targetField.getText()));
		    	  }
		        }
		      });
		thresholdField = new JTextField(3);
		thresholdField.setText(""+threshold);
		thresholdField.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  if(controlThread!=null){
		    		  controlThread.updateThreshold(Double.parseDouble(thresholdField.getText()));
		    	  }
		      }
		      });
		

		
		mainPanel.add(statusLabel);
		mainPanel.add(startExperiment);
		mainPanel.add(stopExperiment);
		mainPanel.add(targetField);
		mainPanel.add(thresholdField);
		frame.add(mainPanel);
		frame.setSize(SCREEN_HEIGHT, SCREEN_WIDTH); //Set to screen resolution
	    frame.setVisible(true);
		
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

}
