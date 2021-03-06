import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.synth.SynthLookAndFeel;

public class GUI {
	// Screen size
	private final static int SCREEN_WIDTH = 480;
	private final static int SCREEN_HEIGHT = 800;
	private static Color orangeSorbet= new Color(244,195,149);
	private static Color darkOrange= new Color(255,151,108);
	private static ArrayList<String> dishList = new ArrayList<String>();
	private static int FAN_PIN = 26;
	private static final String PATH_TO_RELAY_ON = "/home/pi/py/relay_on.py"; // Path
																				// for
																				// fans
																				// script

	// Main runnable class to create GUI
	public static void main(String[] args) throws ParseException,
			UnsupportedLookAndFeelException {

		SynthLookAndFeel laf = new SynthLookAndFeel();
		laf.load(GUI.class.getResourceAsStream("laf.xml"), GUI.class);
		UIManager.setLookAndFeel(laf);

		JFrame frame = new JFrame("The Box"); // Make a frame tabbed pane
		JTabbedPane mainPane = new JTabbedPane(JTabbedPane.LEFT,
				JTabbedPane.WRAP_TAB_LAYOUT);

		// Panel for Settings Tab with light and temperature subtabs
		JPanel settings = new JPanel();
		settings.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(0, 10, 0, 10);

		c.gridy = 0;
		c.weightx = .5;

		c.fill = GridBagConstraints.HORIZONTAL;
		final JButton lightingButton = new JButton("Light Settings");
		final JButton tempButton = new JButton("Temperature Settings");
		lightingButton.setFocusable(false);
		tempButton.setFocusable(false);
		lightingButton.setBackground(orangeSorbet);
		tempButton.setBackground(Color.WHITE);
		tempButton.setForeground(darkOrange);
		tempButton.setFont(new Font("Ariel", Font.BOLD, 26));
		
		Border etched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		lightingButton.setBorder(etched);
		tempButton.setBorder(etched);

		c.gridx = 0;
		c.anchor = GridBagConstraints.EAST;
		settings.add(tempButton, c);
		c.gridx = 1;
		c.anchor = GridBagConstraints.WEST;
		settings.add(lightingButton, c);

		final JPanel setterPane = new JPanel();
		final CardLayout cl = new CardLayout();
		setterPane.setLayout(cl);
		final LightingPanel lightPanel = new LightingPanel();
		final TemperaturePanel tempPanel = new TemperaturePanel();

		setterPane.add(tempPanel.getPanel(), "Temps");
		setterPane.add(lightPanel.getPanel(), "Lights");

		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weighty = 1.0;
		c.weightx = 1.0;
		c.insets = new Insets(0, 20, 0, 20);
		settings.add(setterPane, c);

		//show lighting panel when click lighting button
		lightingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("hello");
				cl.last(setterPane);
				tempButton.setBackground(orangeSorbet);
				tempButton.setForeground(Color.BLACK);
				tempButton.setFont(new Font("Ariel", Font.PLAIN, 24));
				
				
				lightingButton.setBackground(Color.WHITE);
				lightingButton.setForeground(darkOrange);
				lightingButton.setFont(new Font("Ariel", Font.BOLD, 24));
				// lightingButton.setBackground(Color.ORANGE);
				// tempButton.setBackground(Color.WHITE);
			}
		});

		//show temp panel when click temp button
		tempButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("bye");
				cl.first(setterPane);
				tempButton.setBackground(Color.WHITE);
				lightingButton.setBackground(orangeSorbet);
				
				lightingButton.setForeground(Color.BLACK);
				lightingButton.setFont(new Font("Ariel", Font.PLAIN, 24));
				
				
				tempButton.setBackground(Color.WHITE);
				tempButton.setForeground(darkOrange);
				tempButton.setFont(new Font("Ariel", Font.BOLD, 24));
				// lightingButton.setBackground(Color.WHITE);
				// tempButton.setBackground(Color.ORANGE);
			}
		});

		/*
		 * Can use this to load an Icon ImageIcon icon =
		 * createImageIcon("images/icon.gif");
		 */

		StatusPanel statusPanel = new StatusPanel(dishList);

		mainPane.addTab(statusPanel.getPanelName(), statusPanel.getPanel());

		/*
		 * CameraPanel cameraPanel = new CameraPanel();
		 * mainPane.addTab(cameraPanel.getPanelName(), cameraPanel.getPanel());
		 * mainPane.setTabComponentAt(1, changeTab(cameraPanel.getPanelName()));
		 * // tab index, jLabel
		 */

		// LightingPanel lightPanel = new LightingPanel();
		// settingPane.addTab(lightPanel.getPanelName(), lightPanel.getPanel());

		// TemperaturePanel temperaturePanel = new TemperaturePanel();
		// settingPane.addTab(temperaturePanel.getPanelName(),temperaaturePanel.getPanel());

		final ExportImagesPanel eip = new ExportImagesPanel(dishList);
		mainPane.addTab(eip.getPanelName(), eip.getPanel());

		mainPane.addTab("Settings \n\n\n\n\n", settings);
		// settingPane.getComponentAt(0).setName("statusTab");
		// System.out.println("tab"+settingPane.getTabCount());
		// settingPane.getTabComponentAt(0);

		final JLabel tempPV = new JLabel(tempPanel.getTempOutput());
		final JButton lightPV = new JButton("Temp");
		Font buttonFont = new Font("Ariel", Font.PLAIN, 20);
		lightPV.setFont(buttonFont);
		lightPV.setForeground(Color.WHITE);
		lightPV.setPreferredSize(new Dimension(30, 50));
		lightPV.setMaximumSize(new Dimension(30, 50));

		lightPV.setBackground(lightPanel.getLightColor());
		// lightPV.setEnabled(false);
		lightPV.setFocusable(false);

		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				// every time tab changed, check for new file and usb names
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent
						.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				System.out.println("Tab changed to: "
						+ sourceTabbedPane.getTitleAt(index));

				// check if any values have updated so can use as dashboard
				lightPV.setBackground(lightPanel.getLightColor());
				lightPanel.setButtonForeground(lightPV);

				if (tempPanel.getTempOutput() != "") {
					if (tempPanel.getTempOutput() != "Error") {
						lightPV.setText(tempPanel.getTempOutput() + " C");
					}
					lightPV.setText(tempPanel.getTempOutput());
				}
				// System.out.println(lightPanel.getLightColor().getRGB());
				// tempPV.setText(tempPanel.getTempOutput());
				eip.refreshFileNames();
				eip.refreshUsbNames();
			}
		};

		mainPane.addChangeListener(changeListener);
		// mainPane.add(new JPanel());
		frame.setLayout(new GridBagLayout());
		GridBagConstraints r = new GridBagConstraints();
		r.anchor = GridBagConstraints.CENTER;
		r.gridx = 0;
		r.gridy = 0;
		r.gridwidth = 1;
		r.weighty = 1.0;
		r.weightx = 1.0;
		r.fill = GridBagConstraints.HORIZONTAL;
		// c.insets=new Insets(0,20,0,20);

		frame.add(mainPane, r); // Add the tabbed pane to the larger frame

		r.gridy = 1;
		r.gridx = 0;
		r.ipadx = 30;
		r.insets = new Insets(0, 20, 10, 0);
		r.fill = GridBagConstraints.NONE;

		r.anchor = GridBagConstraints.WEST;
		frame.add(lightPV, r);

		// frame.setComponentZOrder(mainPane, a0)
		// frame.setComponentZOrder(lightPV, 1);

		// frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setSize(new Dimension(SCREEN_HEIGHT, SCREEN_WIDTH)); // Set to
																	// screen
																	// resolution
		frame.setResizable(false);
		frame.setVisible(true); // Set the frame to be visible

		// Start fans running
		try {
			Process fOn = Runtime.getRuntime().exec(
					"sudo python " + PATH_TO_RELAY_ON + " " + FAN_PIN);
		} catch (IOException e) {
			System.out.println("Error could not turn on");
		}

	}

}
