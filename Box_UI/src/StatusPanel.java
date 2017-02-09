
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class StatusPanel implements ActionListener {
	private JPanel statusPanel; //Panel to make modifications to 
	private String panelName; //Name for panel 
	private Font checkboxFont= new Font("Arial", Font.BOLD, 20);
	private String timeStampString= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	private int timeStamp;
	
	private JCheckBox dish_1, dish_2, dish_3, dish_4, dish_5, dish_6, dish_7, dish_8, dish_9; 
	private Dish exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8, exp9;
	
	private Frame frame=new Frame();
	
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

		dish_1.addActionListener(this);
		dish_2.addActionListener(this);
		dish_3.addActionListener(this);
		dish_4.addActionListener(this);
		dish_5.addActionListener(this);
		dish_6.addActionListener(this);
		dish_7.addActionListener(this);
		dish_8.addActionListener(this);
		dish_9.addActionListener(this);
		
		dish_1.setFont(checkboxFont);
		dish_2.setFont(checkboxFont);
		dish_3.setFont(checkboxFont);
		dish_4.setFont(checkboxFont);
		dish_5.setFont(checkboxFont);
		dish_6.setFont(checkboxFont);
		dish_7.setFont(checkboxFont);
		dish_8.setFont(checkboxFont);
		dish_9.setFont(checkboxFont);
		
		//create Dishes
		exp1=new Dish();
		exp2=new Dish();
		exp3=new Dish();
		exp4=new Dish();
		exp5=new Dish();
		exp6=new Dish();
		exp7=new Dish();
		exp8=new Dish();
		exp9=new Dish();
		
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

	
	public void showDialogBox(Dish dish){
		String fileName = (String)JOptionPane.showInputDialog(
		                    frame,
		                    "Enter File Name:\n",
		                    "Customized Dialog",
		                    JOptionPane.PLAIN_MESSAGE
		                   );
			
		//TODO: text validation for appropriate file names
		if ((fileName != null) && (fileName.length() > 0)) {
			frame.dispose();
			dish.setFileName(fileName);
			System.out.println("Dish Name Input: "+dish.getFileName());
			return;
		}
		//If you're here, the return value was null/empty.	
	}

	public void dishSelected(Dish dish){
		showDialogBox(dish);
		
		//start time after file name is input
		timeStampString= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		//right now only save time- but probably need to save date too but it won't let me save it all in an Int
		timeStamp= Integer.parseInt(new SimpleDateFormat("HHmmss").format(new Date()));
		
		System.out.println("Selected:"+" "+ timeStampString);
		dish.setTimeStart(timeStamp);
		System.out.println("Time Start: "+dish.getTimeStart());
	}
	public void dishDeSelected(Dish dish){
		//end time after file name is input
		timeStampString= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		//right now only save time- but probably need to save date too but it won't let me save it all in an Int
		timeStamp= Integer.parseInt(new SimpleDateFormat("HHmmss").format(new Date()));
		
		System.out.println("Deselected"+" "+ timeStampString);
		System.out.println("Dish Deselected: "+ dish.getFileName());

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source= e.getSource();


		if (source==dish_1){
			System.out.println("dish_1");
			if(dish_1.getModel().isSelected()==true){
				dishSelected(exp1);
				}
			else{
				dishDeSelected(exp1);
				}
						
		}else if (source==dish_2){
			System.out.println("dish_2");
			if(dish_2.getModel().isSelected()==true)
				dishSelected(exp2);	
			else
				dishDeSelected(exp2);
				
		}
		else if (source==dish_3){
			System.out.println("dish_3");
			if(dish_3.getModel().isSelected()==true)
				dishSelected(exp3);	
			else
				dishDeSelected(exp3);
		}
		else if (source==dish_4){
			System.out.println("dish_4");
			if(dish_4.getModel().isSelected()==true)
				dishSelected(exp4);	
			else
				dishDeSelected(exp4);
		}
		else if (source==dish_5){
			System.out.println("dish_5");
			if(dish_5.getModel().isSelected()==true)
				dishSelected(exp5);	
			else
				dishDeSelected(exp5);
		}
		else if (source==dish_6){
			System.out.println("dish_6");
			if(dish_6.getModel().isSelected()==true)
				dishSelected(exp6);	
			else
				dishDeSelected(exp6);
		}else if (source==dish_7){
			System.out.println("dish_7");
			if(dish_7.getModel().isSelected()==true)
				dishSelected(exp7);	
			else
				dishDeSelected(exp7);
		}
		else if (source==dish_8){
			System.out.println("dish_8");
			if(dish_8.getModel().isSelected()==true)
				dishSelected(exp8);	
			else
				dishDeSelected(exp8);
		}
		else if (source==dish_9){
			System.out.println("dish_9");
			if(dish_9.getModel().isSelected()==true)
				dishSelected(exp9);	
			else
				dishDeSelected(exp9);
		}
		
	}
	
	
}
