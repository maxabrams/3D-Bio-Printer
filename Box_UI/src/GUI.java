import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI {
	// Screen size
	private final static int SCREEN_WIDTH = 480;
	private final static int SCREEN_HEIGHT = 800;
	
	private static ArrayList<String> dishList= new ArrayList <String>();

	
	// Main runnable class to create GUI
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame("The Box"); // Make a frame tabbed pane
		JTabbedPane mainPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
		/*
		 * Can use this to load an Icon ImageIcon icon =
		 * createImageIcon("images/icon.gif");
		 */
		
		//mainPane.setTabPlacement(, );
		StatusPanel statusPanel = new StatusPanel(dishList);
		mainPane.addTab(statusPanel.getPanelName(), statusPanel.getPanel());
		mainPane.setTabComponentAt(0, changeTab(statusPanel.getPanelName()));  // tab index, jLabel
		
		/*
		CameraPanel cameraPanel = new CameraPanel();
		mainPane.addTab(cameraPanel.getPanelName(), cameraPanel.getPanel());
		mainPane.setTabComponentAt(1, changeTab(cameraPanel.getPanelName()));  // tab index, jLabel*/

		LightingPanel lightPanel = new LightingPanel();
		mainPane.addTab(lightPanel.getPanelName(), lightPanel.getPanel());
		mainPane.setTabComponentAt(1, changeTab(lightPanel.getPanelName()));  // tab index, jLabel

		TemperaturePanel temperaturePanel = new TemperaturePanel();
		mainPane.addTab(temperaturePanel.getPanelName(),temperaturePanel.getPanel());
		mainPane.setTabComponentAt(2, changeTab(temperaturePanel.getPanelName()));  // tab index, jLabel
		
		ExportImagesPanel eip = new ExportImagesPanel(dishList);
		mainPane.addTab(eip.getPanelName(), eip.getPanel());
		mainPane.setTabComponentAt(3, changeTab(eip.getPanelName()));  // tab index, jLabel
	
		frame.add(mainPane); // Add the tabbed pane to the larger frame
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setSize(new Dimension(SCREEN_HEIGHT, SCREEN_WIDTH)); // Set to screen resolution
		frame.setResizable(false);
		frame.setVisible(true); // Set the frame to be visible

	}
	
	private static JLabel changeTab(String tabname){
		 JLabel lab = new JLabel(tabname);
		 lab.setPreferredSize(new Dimension(100, 100));
		 return lab;		
	}

}
