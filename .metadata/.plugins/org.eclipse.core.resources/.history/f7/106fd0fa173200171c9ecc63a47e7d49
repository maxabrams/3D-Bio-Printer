import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.MaskFormatter;

public class ExportImagesPanel  {
	private JPanel exportImagesPanel; // Panel to make modifications to
	private String panelName; // Name for panel
	private Font checkboxFont = new Font("Arial", Font.BOLD, 20);

	private JButton dish_1, dish_2, dish_3, dish_4, dish_5, dish_6, dish_7,
			dish_8, dish_9;
	private Dish exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8, exp9;

	private Frame frame = new Frame();

	private ArrayList<String> dishNames;
	JComboBox folderNames;
	
	JComboBox UsbNames;
	public ExportImagesPanel(ArrayList <String> dishes) {
		dishNames=dishes;
		// Initialize components
		exportImagesPanel = new JPanel();
		
		// Initialize Grid bag layout		
		exportImagesPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		
		panelName = "Export Images"; // Assign name
		
		UsbNames=new JComboBox();
		UsbNames.setVisible(false);
		
		JButton UsbButton= new JButton("Export");
//		UsbButton.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				File[] paths;
//				FileSystemView fsv = FileSystemView.getFileSystemView();
//
//				// returns pathnames for files and directory
//				paths = File.listRoots();
//				UsbNames.removeAllItems();
//
//				// for each pathname in pathname array
//				for(File path:paths)
//				{
//					System.out.println(path.toString());
//					if(!path.toString().equals( "C:\\")){
//							UsbNames.addItem(path+ " " +fsv.getSystemTypeDescription(path));
//					}
//				    // prints file and directory paths
//				    System.out.println("Drive Name: "+path);
//				    System.out.println("Description: "+fsv.getSystemTypeDescription(path));
//				}
//				UsbNames.setVisible(true);
//			}
//		});
		folderNames=  new JComboBox(dishes.toArray());
		JLabel  dishLabel= new JLabel("Select your experiment");
		JLabel USBLabel= new JLabel("Select your USB");
		
		JLabel blank= new JLabel("hello ");
		c.gridx=0;
		c.gridy=0;
		c.ipadx=50;
		exportImagesPanel.add(blank,c);
		
		c.insets= new Insets(0,00,0,10);
		c.anchor=GridBagConstraints.EAST;
		c.gridx=1;
		c.gridy=0;
		c.ipady=50;
		c.ipadx=200;	
		exportImagesPanel.add(dishLabel,c);
		
		c.insets= new Insets(0,00,0,10);
		c.gridx=2;
		c.gridy=0;
		exportImagesPanel.add(USBLabel,c);

		c.gridx=1;
		c.gridy=1;
		c.ipady=50;
		c.ipadx=0;		
		c.insets= new Insets(0,50,10,80);

		exportImagesPanel.add(folderNames,c);
		
		c.gridy=2;
		c.gridx=1;
		exportImagesPanel.add(UsbNames,c);
	
		c.gridx=1;
		c.gridy=3;
		c.ipadx=60;
//		c.gridwidth=1;
		c.insets= new Insets(50,50,50,50);
		exportImagesPanel.add(UsbButton,c);
		UsbButton.setBackground(new Color(200,200,200));
		
		System.out.println(folderNames.getSelectedIndex());
	}
	public void refreshFileNames(){
		folderNames.removeAllItems();
		for(String str: dishNames){
			folderNames.addItem(str);

		}
	}
	
	public void refreshUsbNames(){
		File[] paths;
		FileSystemView fsv = FileSystemView.getFileSystemView();
		UsbNames.removeAllItems();
		File dev = fsv.getChild(fsv.getRoots()[0], "dev/disk/by-label/");
		for(String path: dev.list()){
			if(!path.equals("boot")){
				System.out.println(path);
				UsbNames.addItem(path);
			}
		}
		UsbNames.setVisible(true);
	}
	/*
	 * Accessor for Status Panel's JPanel
	 * 
	 * @returns JPanel
	 */
	public JPanel getPanel() {
		return this.exportImagesPanel;
	}

	/*
	 * Accessor for Status Panel's name
	 * 
	 * @returns String
	 */
	public String getPanelName() {
		return this.panelName;
	}
}

	
