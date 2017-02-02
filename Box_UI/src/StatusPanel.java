
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;


public class StatusPanel implements ItemListener {
	private JPanel statusPanel; //Panel to make modifications to 
	private String panelName; //Name for panel 
	private Font checkboxFont= new Font("Arial", Font.BOLD, 20);
	private String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	
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
		
		dish_1.setFont(checkboxFont);
		dish_2.setFont(checkboxFont);
		dish_3.setFont(checkboxFont);
		dish_4.setFont(checkboxFont);
		dish_5.setFont(checkboxFont);
		dish_6.setFont(checkboxFont);
		dish_7.setFont(checkboxFont);
		dish_8.setFont(checkboxFont);
		dish_9.setFont(checkboxFont);
		
		//Add components
		//3x3 matrix
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dish_1,100,100,100)
						.addComponent(dish_2,100,100,100)
						.addComponent(dish_3,100,100,100)
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dish_4,100,100,100)
						.addComponent(dish_5,100,100,100)
						.addComponent(dish_6,100,100,100)
						
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dish_7,100,100,100)
						.addComponent(dish_8,100,100,100)
						.addComponent(dish_9,100,100,100)						)
		
		);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dish_1,100,100,100)
						.addComponent(dish_4,100,100,100)
						.addComponent(dish_7,100,100,100)
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dish_2,100,100,100)
						.addComponent(dish_5,100,100,100)
						.addComponent(dish_8,100,100,100)
						
						)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dish_3,100,100,100)
						.addComponent(dish_6,100,100,100)
						.addComponent(dish_9,100,100,100)
						)
				);

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

	// behavior when checkbox is clicked
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		Object source= e.getItemSelectable();
		if(e.getStateChange()==1){
			timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			System.out.println("Selected"+" "+ timeStamp);
			}
		else{
			timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			System.out.println("Deselected"+" "+ timeStamp);
			}
		
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