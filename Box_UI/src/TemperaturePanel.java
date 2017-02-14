
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TemperaturePanel {
	private final static String STOP_BUTTON_TEXT = "Stop"; 
	private final static String START_BUTTON_TEXT = "Run"; 
	private final static String TEMP_PANEL_NAME = "Temperature Control";
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
	
	public TemperaturePanel(){
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout()); //Add a layout manager to align buttons as we resize
		output = new JLabel();
		//Start button
		startExperiment = new JButton(START_BUTTON_TEXT);
		startExperiment.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) { //Add Action listener to respond to button
				updateStatus("Running");
				stop(); //Make sure no existing thread
				controlThread = new TempControl(output);
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
		targetField = new JTextField(3);
		targetField.setText(""+target);
		targetField.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  if(controlThread!=null){
		    		  controlThread.updateTarget(Double.parseDouble(targetField.getText()));
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
		

		
		mainPanel.add(statusLabel);
		mainPanel.add(startExperiment);
		mainPanel.add(stopExperiment);
		mainPanel.add(targetField);
		mainPanel.add(thresholdField);
		mainPanel.add(output);
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
	
	

}
