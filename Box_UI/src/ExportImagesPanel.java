import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
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
	
	public ExportImagesPanel(ArrayList <String> dishes) {
		dishNames=dishes;
		// Initialize components
		exportImagesPanel = new JPanel();
		exportImagesPanel.setLayout(new GridLayout(1, 1)); // Add a layout manager to align components


		// Initialize Group Layout object
//		GroupLayout layout = new GroupLayout(exportImagesPanel);
//		exportImagesPanel.setLayout(layout); // Add a layout manager to align
//												// elements
//		// specify automatic gap insertion
//		layout.setAutoCreateGaps(true);
//		layout.setAutoCreateContainerGaps(true);

		panelName = "Export Images"; // Assign name
		
		JButton b= new JButton("Refresh");
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				refreshFileNames();
			}
			
			
		});
		
		folderNames=  new JComboBox(dishes.toArray());
		exportImagesPanel.add(folderNames);
		exportImagesPanel.add(b);
		System.out.println(folderNames.getSelectedIndex());
		

			
	}
	public void refreshFileNames(){
		folderNames.removeAllItems();
		for(String str: dishNames){
			folderNames.addItem(str);

		}
//		folderNames.addItem(dishNames.toArray());
		System.out.println(dishNames.size());
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

	
