import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI {
	// Screen size
	private final static int SCREEN_WIDTH = 480;
	private final static int SCREEN_HEIGHT = 800;
	
	private static ArrayList<String> dishList= new ArrayList <String>();

	
	// Main runnable class to create GUI
	public static void main(String[] args) {
		JFrame frame = new JFrame("The Box"); // Make a frame tabbed pane
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set exit by
																// default when
																// close
		JTabbedPane mainPane = new JTabbedPane();
		/*
		 * Can use this to load an Icon ImageIcon icon =
		 * createImageIcon("images/icon.gif");
		 */
		StatusPanel statusPanel = new StatusPanel(dishList);
		mainPane.addTab(statusPanel.getPanelName(), statusPanel.getPanel());
		
		CameraPanel cameraPanel = new CameraPanel();
		mainPane.addTab(cameraPanel.getPanelName(), cameraPanel.getPanel());

		LightingPanel lightPanel = new LightingPanel();
		mainPane.addTab(lightPanel.getPanelName(), lightPanel.getPanel());

		TemperaturePanel temperaturePanel = new TemperaturePanel();
		mainPane.addTab(temperaturePanel.getPanelName(),
				temperaturePanel.getPanel());
		
		 ExportImagesPanel eip = new ExportImagesPanel(dishList);
		mainPane.addTab(eip.getPanelName(), eip.getPanel());
//		mainPane.addChangeListener(new ChangeListener(){
//			@Override
//			public void stateChanged(ChangeEvent arg0) {
//				
//				eip.refreshFileNames();
//			}			
//		});

		

		frame.add(mainPane); // Add the tabbed pane to the larger frame
		frame.setSize(SCREEN_HEIGHT, SCREEN_WIDTH); // Set to screen resolution
		frame.setVisible(true); // Set the frame to be visible

	}

}
