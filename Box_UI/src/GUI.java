import java.awt.*;
import java.text.ParseException;
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
import javax.swing.plaf.synth.SynthLookAndFeel;

public class GUI {
	// Screen size
	private final static int SCREEN_WIDTH = 480;
	private final static int SCREEN_HEIGHT = 800;
	
	private static ArrayList<String> dishList= new ArrayList <String>();

	
	// Main runnable class to create GUI
	public static void main(String[] args) throws ParseException, UnsupportedLookAndFeelException {
//		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		  SynthLookAndFeel laf = new SynthLookAndFeel();
		  laf.load(GUI.class.getResourceAsStream("laf.xml"), GUI.class);
		  UIManager.setLookAndFeel(laf);

		  
		JFrame frame = new JFrame("The Box"); // Make a frame tabbed pane
		JTabbedPane mainPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
		/*
		 * Can use this to load an Icon ImageIcon icon =
		 * createImageIcon("images/icon.gif");
		 */
		
		//mainPane.setTabPlacement(, );
		StatusPanel statusPanel = new StatusPanel(dishList);
		mainPane.addTab(statusPanel.getPanelName(), statusPanel.getPanel());
		
		/*
		CameraPanel cameraPanel = new CameraPanel();
		mainPane.addTab(cameraPanel.getPanelName(), cameraPanel.getPanel());
		mainPane.setTabComponentAt(1, changeTab(cameraPanel.getPanelName()));  // tab index, jLabel*/

		LightingPanel lightPanel = new LightingPanel();
		mainPane.addTab(lightPanel.getPanelName(), lightPanel.getPanel());

		TemperaturePanel temperaturePanel = new TemperaturePanel();
		mainPane.addTab(temperaturePanel.getPanelName(),temperaturePanel.getPanel());
		
		final ExportImagesPanel eip = new ExportImagesPanel(dishList);
		mainPane.addTab(eip.getPanelName(), eip.getPanel());
		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
		        eip.refreshFileNames();
		        eip.refreshUsbNames();
		      }
		    };
		mainPane.addChangeListener(changeListener);
		
	
		frame.add(mainPane); // Add the tabbed pane to the larger frame
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setSize(new Dimension(SCREEN_HEIGHT, SCREEN_WIDTH)); // Set to screen resolution
		frame.setResizable(false);
		frame.setVisible(true); // Set the frame to be visible

	}
	

}
