<<<<<<< HEAD
package src;
=======
>>>>>>> 6a2fca55634cdbde1f9443f1b5cb6829e498272e

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePanel {
	private JPanel homePanel; //Panel to make modifications to 
	private String panelName; //Name for panel 
	private JButton statusButton;
	private JButton newExperimentButton;
	private final String STATUS_BUTTON_TEXT = "<html><u>Current System Status</u></html>";
	private final String NEW_EXPERIMENT_BUTTON_TEXT = "Begin New Experiment";
	
	public HomePanel(){
		//Initialize components
		homePanel = new JPanel();
		homePanel.setLayout(new GridLayout(1,2)); //Add a layout manager to align buttons as we resize
		panelName = "Home"; //Assign name
		//Setup and add buttons
		statusButton = new JButton(STATUS_BUTTON_TEXT);
		statusButton.addActionListener(new ActionListener(){ //Add action listener to respond to button

			public void actionPerformed(ActionEvent e) {
				System.out.println("Checking system status!"); //Example action
				
			}
			
		});
		newExperimentButton = new JButton(NEW_EXPERIMENT_BUTTON_TEXT);
		newExperimentButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) { //Add Action listener to respond to button
				System.out.println("Starting new experiment!"); //Example action
				
			}
			
		});
		homePanel.add(statusButton);
		homePanel.add(newExperimentButton);
	}
	
	/*
	 * Accessor for Home Panel's JPanel
	 * @returns JPanel
	 */
	public JPanel getPanel(){
		return this.homePanel;
	}
	
	/*
	 * Accessor for Home Panel's name
	 * @returns String
	 */
	public String getPanelName(){
		return this.panelName;
	}
}
