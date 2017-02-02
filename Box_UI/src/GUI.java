import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class GUI {
	//Screen size
	private final static int SCREEN_WIDTH = 600;
	private final static int SCREEN_HEIGHT = 800;
	
	//Main runnable class to create GUI
	public static void main(String[] args) {
		JFrame frame = new JFrame("The Box"); //Make a frame tabbed pane
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set exit by default when close 
		JTabbedPane mainPane = new JTabbedPane();
		/*
		 * Can use this to load an Icon
		 * ImageIcon icon = createImageIcon("images/icon.gif");
		 */
		HomePanel homePanel = new HomePanel(); //Create a new Home Panel
		CameraPanel cameraPanel = new CameraPanel();
		CameraPanel cameraPanel1 = new CameraPanel();
		LightingPanel cameraPanel2 = new LightingPanel();
		mainPane.addTab(homePanel.getPanelName(), homePanel.getPanel());
		mainPane.addTab(cameraPanel.getPanelName(),cameraPanel.getPanel());
		mainPane.addTab("Experiment Settings",cameraPanel1.getPanel());
		mainPane.addTab("Lighting Control",cameraPanel2.getPanel());
		
		ImagePanel imagePanel=new ImagePanel(); //Create a new Image Panel
		mainPane.addTab(imagePanel.getPanelName(), imagePanel.getPanel());
		
		StatusPanel statusPanel = new StatusPanel();
		mainPane.addTab(statusPanel.getPanelName(), statusPanel.getPanel());

		
		frame.add(mainPane); //Add the tabbed pane to the larger frame
		frame.setSize(SCREEN_HEIGHT, SCREEN_WIDTH); //Set to screen resolution
	    frame.setVisible(true); //Set the frame to be visible 
	    

	}

}
