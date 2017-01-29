
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class StatusPanel implements ItemListener {
	private JPanel statusPanel; //Panel to make modifications to 
	private String panelName; //Name for panel 
	private JButton statusButton;
	private JButton newExperimentButton;
	private final String STATUS_BUTTON_TEXT = "Images1";
	private final String NEW_EXPERIMENT_BUTTON_TEXT = "Imgaes2";
	
	private JCheckBox dish_1, dish_2, dish_3, dish_4, dish_5, dish_6, dish_7, dish_8, dish_9; 
	
	public StatusPanel(){
		//Initialize components
		statusPanel = new JPanel();
		
		//Initialize Group Layout object
		GroupLayout layout = new GroupLayout(statusPanel);
		statusPanel.setLayout(layout); //Add a layout manager to align elements
		
		//specify automatic gap insertion
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		
		panelName = "Status"; //Assign name
		
		//Setup checkboxes
		dish_1 = new JCheckBox("Dish 1");
		dish_2 = new JCheckBox("Dish 2");
		dish_3 = new JCheckBox("Dish 3");
		dish_4 = new JCheckBox("Dish 4");
		dish_5 = new JCheckBox("Dish 5");
		dish_6 = new JCheckBox("Dish 6");
		dish_7 = new JCheckBox("Dish 7");
		dish_8 = new JCheckBox("Dish 8");
		dish_9 = new JCheckBox("Dish 9");


		
		dish_1.addItemListener(this);
		dish_2.addItemListener(this);
		dish_3.addItemListener(this);
		dish_4.addItemListener(this);
		dish_5.addItemListener(this);
		dish_6.addItemListener(this);
		dish_7.addItemListener(this);
		dish_8.addItemListener(this);
		dish_9.addItemListener(this);

		//Add components
		//3x3 matrix
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dish_1)
						.addComponent(dish_2)
						.addComponent(dish_3)
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dish_4)
						.addComponent(dish_5)
						.addComponent(dish_6)
						
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dish_7)
						.addComponent(dish_8)
						.addComponent(dish_9)						)
		
		);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dish_1)
						.addComponent(dish_4)
						.addComponent(dish_7)
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dish_2)
						.addComponent(dish_5)
						.addComponent(dish_8)
						
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dish_3)
						.addComponent(dish_6)
						.addComponent(dish_9))
				);

		
//		imagePanel.add(statusButton);
//		imagePanel.add(numSpinner);
	}
	

	/*
	 * Accessor for Status Panel's JPanel
	 * @returns JPanel
	 */
	public JPanel getPanel(){
		return this.statusPanel;
	}
	
	/*
	 * Accessor for Status Panel's name
	 * @returns String
	 */
	public String getPanelName(){
		return this.panelName;
	}

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		Object source= e.getItemSelectable();
		
		if (source==dish_1){
			System.out.println("dish_1");
		}else if (source==dish_2){
			System.out.println("dish_2");
		}
		else if (source==dish_3){
			System.out.println("dish_3");
		}
		else if (source==dish_4){
			System.out.println("dish_4");
		}
		else if (source==dish_5){
			System.out.println("dish_5");
		}
		else if (source==dish_6){
			System.out.println("dish_6");
		}else if (source==dish_7){
			System.out.println("dish_7");
		}
		else if (source==dish_8){
			System.out.println("dish_8");
		}
		else if (source==dish_9){
			System.out.println("dish_9");
		}
	}
	
}
