import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CameraPanel {
	private JPanel cameraPanel; //Panel to make modifications to 
	private String cameraName; //Name for panel 
	private JButton captureButton;
	private final String CAMERA_BUTTON_TEXT = "Capture Image";
	
	public CameraPanel(){
		//Initialize components
		cameraPanel = new JPanel();
		cameraPanel.setLayout(new GridLayout(1,2)); //Add a layout manager to align buttons as we resize
		cameraName = "Camera Test"; //Assign name
		//Setup and add buttons
		captureButton = new JButton(CAMERA_BUTTON_TEXT);
		captureButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			} //Add action listener to respond to button
			
		});
		
		cameraPanel.add(captureButton);
	}
	
	public JPanel getPanel(){
		return this.cameraPanel;
	}

	public String getPanelName() {
		return cameraName;
	}
}