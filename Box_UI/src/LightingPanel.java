import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class LightingPanel {
	private JPanel cameraPanel; //Panel to make modifications to 
	private String cameraName; //Name for panel 
	private JSlider captureButton;
	private final String CAMERA_BUTTON_TEXT = "Capture Image";
	
	public LightingPanel(){
		//Initialize components
		cameraPanel = new JPanel();
		cameraPanel.setLayout(new GridLayout(1,2)); //Add a layout manager to align buttons as we resize
		cameraName = "Lighting Control"; //Assign name
		//Setup and add buttons
		captureButton = new JSlider(0,100);
		captureButton.setPaintTicks(true);
		captureButton.setMajorTickSpacing(10);
		captureButton.setMinorTickSpacing(5);
		captureButton.setPaintLabels(true);
		
		
		cameraPanel.add(captureButton);
	}
	
	public JPanel getPanel(){
		return this.cameraPanel;
	}

	public String getPanelName() {
		return cameraName;
	}
}
