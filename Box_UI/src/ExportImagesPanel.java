import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
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
		exportImagesPanel.setLayout(new GridLayout(4, 3)); // Add a layout manager to align components

		panelName = "Export Images"; // Assign name
		
		UsbNames=new JComboBox();
		
		JButton UsbButton= new JButton("Choose USB");
		UsbButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				refreshFileNames();

				
				File[] paths;
				FileSystemView fsv = FileSystemView.getFileSystemView();

				// returns pathnames for files and directory
				paths = File.listRoots();
				UsbNames.removeAllItems();


				// for each pathname in pathname array
				for(File path:paths)
				{
					System.out.println(path.toString());
					if(!path.toString().equals( "C:\\")){
							UsbNames.addItem(path+ " " +fsv.getSystemTypeDescription(path));
					}
				    // prints file and directory paths
				    System.out.println("Drive Name: "+path);
				    System.out.println("Description: "+fsv.getSystemTypeDescription(path));
				}
				
			}

		});
		folderNames=  new JComboBox(dishes.toArray());
		exportImagesPanel.add(folderNames);
		exportImagesPanel.add(UsbButton);
		exportImagesPanel.add(UsbNames);
	
		System.out.println(folderNames.getSelectedIndex());
	}
	public void refreshFileNames(){
		folderNames.removeAllItems();
		for(String str: dishNames){
			folderNames.addItem(str);

		}
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

	
