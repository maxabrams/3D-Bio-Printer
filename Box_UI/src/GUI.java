import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		
		
		JPanel settings= new JPanel();
		settings.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridy=0;
		c.weightx=.5;

		c.fill= GridBagConstraints.HORIZONTAL;
		final JButton lightingButton= new JButton("Light Settings");
		final JButton tempButton= new JButton("Temperature Settings");
		
		c.gridx=0;
		c.anchor=GridBagConstraints.EAST;
		settings.add(lightingButton, c);
		c.gridx=1;
		c.anchor=GridBagConstraints.WEST;
		settings.add(tempButton , c);
		
		final JPanel setterPane= new JPanel();
		final CardLayout cl= new CardLayout();
		setterPane.setLayout(cl);
		LightingPanel lightPanel= new LightingPanel();
		TemperaturePanel tempPanel= new TemperaturePanel();
		
		setterPane.add(lightPanel.getPanel(), "Lights");
		setterPane.add(tempPanel.getPanel(), "Temps");

		c.anchor=GridBagConstraints.CENTER;
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=2;
		c.weighty=1.0;
		c.weightx=1.0;
		settings.add(setterPane, c);
		
		
		 lightingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("hello");
		      	cl.first(setterPane);
//		      	lightingButton.setBackground(Color.ORANGE);
//		      	tempButton.setBackground(Color.WHITE);
			}
		    });

		 tempButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("bye");
		      	cl.last(setterPane);
//		    	lightingButton.setBackground(Color.WHITE);
//		      	tempButton.setBackground(Color.ORANGE);
			}
		    });
		
		/*
		 * Can use this to load an Icon ImageIcon icon =
		 * createImageIcon("images/icon.gif");
		 */
		
		StatusPanel statusPanel = new StatusPanel(dishList);
		mainPane.addTab(statusPanel.getPanelName(), statusPanel.getPanel());
		
		/*
		CameraPanel cameraPanel = new CameraPanel();
		mainPane.addTab(cameraPanel.getPanelName(), cameraPanel.getPanel());
		mainPane.setTabComponentAt(1, changeTab(cameraPanel.getPanelName()));  // tab index, jLabel*/

//		LightingPanel lightPanel = new LightingPanel();
//		settingPane.addTab(lightPanel.getPanelName(), lightPanel.getPanel());

//		TemperaturePanel temperaturePanel = new TemperaturePanel();
//		settingPane.addTab(temperaturePanel.getPanelName(),temperaaturePanel.getPanel());
		
		
		final ExportImagesPanel eip = new ExportImagesPanel(dishList);
		mainPane.addTab(eip.getPanelName(), eip.getPanel());
		
		mainPane.addTab("Settings \n\n\n\n\n", settings);
//		settingPane.getComponentAt(0).setName("statusTab");
//		System.out.println("tab"+settingPane.getTabCount());
//		settingPane.getTabComponentAt(0);
		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		    	 // every time tab changed, check for new file and usb names
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
		        eip.refreshFileNames();
		        eip.refreshUsbNames();
		        
		        
		        //check if any values have updated so can use as dashboard
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
