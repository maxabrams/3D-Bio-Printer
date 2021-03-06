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
import java.io.IOException;
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
	JLabel statusLabel = new JLabel();
	
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
		UsbButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) { //Add Action listener to respond to button
				coppyFiles();
			}
			
		});

		folderNames=  new JComboBox(dishes.toArray());
		JLabel  dishLabel= new JLabel("Select your experiment");
		JLabel USBLabel= new JLabel("Select your USB");
		statusLabel.setText("Waiting to coppy...");

		c.gridx=0;
		c.gridy=0;
		c.ipady=50;
		c.ipadx=60;	
		exportImagesPanel.add(dishLabel,c);
		
		c.gridx=1;
		c.gridy=0;
		exportImagesPanel.add(USBLabel,c);

		c.gridx=0;
		c.gridy=1;	
		c.insets= new Insets(0,0,0,30);
		exportImagesPanel.add(folderNames,c);
		
	
		
		c.gridy=1;
		c.gridx=1;
		c.insets= new Insets(0,0,0,0);
		exportImagesPanel.add(UsbNames,c);
	
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=4;
		c.insets= new Insets(40,0,0,0);
		exportImagesPanel.add(UsbButton,c);
		
		//HELP WITH GRID CYN
		exportImagesPanel.add(statusLabel, c);
		UsbButton.setBackground(new Color(200,200,200));
		
		System.out.println(folderNames.getSelectedIndex());
	}
	
	private void coppyFiles(){
		statusLabel.setText("copying in progress... do not interrupt");
		 try {
			 System.out.println("sudo cp -a ~/Desktop/"+folderNames.getSelectedItem() + " /media/pi/"+UsbNames.getSelectedItem()+"/");
			Process p = Runtime.getRuntime().exec("sudo cp -a ~/Desktop/"+folderNames.getSelectedItem() + " /media/pi/"+UsbNames.getSelectedItem()+"/");
		} catch (IOException e) {
			System.out.println("Error! Could not copy!");
			statusLabel.setText("Copy error!");
		}
		
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

	
