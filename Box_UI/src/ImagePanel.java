import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class ImagePanel {
	private JPanel imagePanel; //Panel to make modifications to 
	private String panelName; //Name for panel 
	private JButton statusButton;
	private JButton newExperimentButton;
	private final String STATUS_BUTTON_TEXT = "Images1";
	private final String NEW_EXPERIMENT_BUTTON_TEXT = "Imgaes2";
	
	public ImagePanel(){
		//Initialize components
		imagePanel = new JPanel();
		
		//Initialize Group Layout object
		GroupLayout layout = new GroupLayout(imagePanel);
		imagePanel.setLayout(layout); //Add a layout manager to align elements
		
		//specify automatic gap insertion
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		
		panelName = "Image"; //Assign name
		
		//Setup buttons
		statusButton = new JButton(STATUS_BUTTON_TEXT);
		statusButton.addActionListener(new ActionListener(){ //Add action listener to respond to button

			public void actionPerformed(ActionEvent e) {
				System.out.println("Checking system status!"); //Example action
				
			}
			
		});
		
		//Setup text labels
		JLabel capture = new JLabel("Capture(Number of Images)");
		JLabel duration= new JLabel("Total time of Experiment (hours |minutes)");
		JLabel title = new JLabel("Image Capture");
		
		capture.setFont(new Font("Arial",Font.PLAIN, 24));
		duration.setFont(new Font("Arial",Font.PLAIN, 24));
		title.setFont(new Font("Arial",Font.PLAIN, 30));
		
		//Setup number selectors
		SpinnerModel captureModel= new SpinnerNumberModel(0,0,60,1);
		JSpinner captureSpinner= new JSpinner(captureModel);
		
		SpinnerModel minuteModel= new SpinnerNumberModel(0,0,60,1);
		JSpinner minuteSpinner= new JSpinner(minuteModel);
		
		SpinnerModel hourModel= new SpinnerNumberModel(0,0,60,1);
		JSpinner hourSpinner= new JSpinner(hourModel);
		
		captureSpinner.setFont(new Font("Arial",Font.PLAIN, 36));
		minuteSpinner.setFont(new Font("Arial",Font.PLAIN, 36));
		hourSpinner.setFont(new Font("Arial",Font.PLAIN, 36));
		
		
		//Add components
		//3x3 matrix
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(capture)
						.addComponent(duration)
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(title)
						.addComponent(captureSpinner, 100,100,100 )
						.addComponent(hourSpinner, 100,100,100 )
						
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(minuteSpinner, 100,100,100 )
						)
		
		);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(title)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(capture)
						.addComponent(captureSpinner, 100,100,100)
					)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(duration)
						.addComponent(hourSpinner, 100,100,100 )
						.addComponent(minuteSpinner, 100,100,100 )
					)		
				);
		
		
//		imagePanel.add(statusButton);
//		imagePanel.add(numSpinner);
	}
	
	/*
	 * Accessor for Image Panel's JPanel
	 * @returns JPanel
	 */
	public JPanel getPanel(){
		return this.imagePanel;
	}
	
	/*
	 * Accessor for Image Panel's name
	 * @returns String
	 */
	public String getPanelName(){
		return this.panelName;
	}
}
