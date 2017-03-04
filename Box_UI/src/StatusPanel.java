import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;

public class StatusPanel implements ActionListener {
	private JPanel statusPanel; // Panel to make modifications to
	private String panelName; // Name for panel
	private Font checkboxFont = new Font("Arial", Font.BOLD, 20);
	private String timeStampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
			.format(new Date());
	private Date timeStamp;

	private JCheckBox dish_1, dish_2, dish_3, dish_4, dish_5, dish_6, dish_7,
			dish_8, dish_9;
	private Dish exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8, exp9;

	private Frame frame = new Frame();
	private String[] times = { "secs", "mins", "hours", "days" };
	
	private String captureMetric= "mins";


	public StatusPanel() {

		// Initialize components
		statusPanel = new JPanel();

		// Initialize Group Layout object
		GroupLayout layout = new GroupLayout(statusPanel);
		statusPanel.setLayout(layout); // Add a layout manager to align elements

		// specify automatic gap insertion
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		panelName = "Status"; // Assign name

		// Setup checkboxes
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

		// create Dishes
		exp1 = new Dish();
		exp2 = new Dish();
		exp3 = new Dish();
		exp4 = new Dish();
		exp5 = new Dish();
		exp6 = new Dish();
		exp7 = new Dish();
		exp8 = new Dish();
		exp9 = new Dish();

		// Add components
		// 3x3 matrix
		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(dish_1, 100, 100, 100)
								.addComponent(dish_2, 100, 100, 100)
								.addComponent(dish_3, 100, 100, 100))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(dish_4, 100, 100, 100)
								.addComponent(dish_5, 100, 100, 100)
								.addComponent(dish_6, 100, 100, 100)

				)
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(dish_7, 100, 100, 100)
								.addComponent(dish_8, 100, 100, 100)
								.addComponent(dish_9, 100, 100, 100))

		);

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(dish_1, 100, 100, 100)
								.addComponent(dish_4, 100, 100, 100)
								.addComponent(dish_7, 100, 100, 100))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(dish_2, 100, 100, 100)
								.addComponent(dish_5, 100, 100, 100)
								.addComponent(dish_8, 100, 100, 100)

				)
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(dish_3, 100, 100, 100)
								.addComponent(dish_6, 100, 100, 100)
								.addComponent(dish_9, 100, 100, 100)));

	}

	/*
	 * Accessor for Status Panel's JPanel
	 * 
	 * @returns JPanel
	 */
	public JPanel getPanel() {
		return this.statusPanel;
	}

	/*
	 * Accessor for Status Panel's name
	 * 
	 * @returns String
	 */
	public String getPanelName() {
		return this.panelName;
	}

	public String showNameDialogBox(String dish) {
		String fileName = (String) JOptionPane.showInputDialog(frame,
				"Enter File Name:\n", "Enter File Name",
				JOptionPane.PLAIN_MESSAGE);

		// TODO: text validation for appropriate file names
		if ((fileName != null) && (fileName.length() > 0)) {
			frame.dispose();
			// show image capture information
			return fileName;
		} else {
			JCheckBox temp;
			if (dish == "dish_1") {
				temp=dish_1;
			} else if (dish == "dish_2") {
				temp=dish_2;
			} else if (dish == "dish_3") {
				temp=dish_3;
			} else if (dish == "dish_4") {
				temp=dish_4;
			} else if (dish == "dish_5") {
				temp=dish_5;
			} else if (dish == "dish_6") {
				temp=dish_6;
			} else if (dish == "dish_7") {
				temp=dish_7;
			} else if (dish == "dish_8") {
				temp=dish_8;
			} else if (dish == "dish_9") {
				temp=dish_9;
			} else {
				System.out.println("Invalid Dish");
				return "";
			}
			temp.setSelected(false);
			return "";
		}
	}
	
	// Get image capture rate information
	public int showImageDialogBox(String dish) {

		// captureRate defaults to 1 a minute
		SpinnerNumberModel captureModel = new SpinnerNumberModel(1, 0, 60, 1);
		JSpinner captureSpinner = new JSpinner(captureModel);

		// Create the combo box, select item at index 4.
		JComboBox timeList = new JComboBox(times);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("1 panel every"));
		// input number
		myPanel.add(captureSpinner);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		// input second/minute/hour
		myPanel.add(timeList);

		// Show Option Pop up
		JOptionPane.showConfirmDialog(null, myPanel,
				"Enter Capture Rate", JOptionPane.OK_CANCEL_OPTION);

		frame.dispose();

		int captureRate = Integer.parseInt(captureModel.getValue().toString());
		captureMetric = (String) timeList.getSelectedItem();
		
		return captureRate;
	}

	public void dishSelected(String dishName) {
		//Show dialog boxes for dish name input and capture rate
		String fileName = showNameDialogBox(dishName);

		// Figure out which dish was chosen
		if (fileName == "") {
			System.out.println("exit before enter name");
			return;
		} else {
			Dish dish;
			//don't select the dish if they don't enter a name
			if (dishName == "dish_1") {
				dish = exp1;
			} else if (dishName == "dish_2") {
				dish = exp2;
			} else if (dishName == "dish_3") {
				dish = exp3;
			} else if (dishName == "dish_4") {
				dish = exp4;
			} else if (dishName == "dish_5") {
				dish = exp5;
			} else if (dishName == "dish_6") {
				dish = exp6;
			} else if (dishName == "dish_7") {
				dish = exp7;
			} else if (dishName == "dish_8") {
				dish = exp8;
			} else if (dishName == "dish_9") {
				dish = exp9;
			} else {
				System.out.println("Invalid Dish");
				return;

			}
			
			// if don't enter a name, don't ask for capture rate
			int captureRate = showImageDialogBox(dishName);
			
			dish.setFileName(fileName);
			System.out.println("Dish Name Input: " + dish.getFileName());

			dish.setCaptureRate(captureRate);
			dish.setCaptureMetric(captureMetric);
			System.out.println("Capture Rate: 1 image every " + dish.getCaptureRate()+ " "+dish.getCaptureMetric());

			// start time after file name is input
			timeStampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			timeStamp = new Date();

			System.out.println("Selected:" + " " + timeStampString);
			dish.setTimeStart(timeStamp);
			System.out.println("Time Start: " + dish.getTimeStart());
		}
	}

	public void dishDeSelected(Dish dish) {
		timeStampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Date());
		timeStamp = new Date();

		System.out.println("Deselected" + " " + timeStampString);
		System.out.println("Dish Deselected: " + dish.getFileName());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == dish_1) {
			System.out.println("dish_1");
			if (dish_1.getModel().isSelected() == true) {
				dishSelected("dish_1");
			} else {
				dishDeSelected(exp1);
			}

		} else if (source == dish_2) {
			System.out.println("dish_2");
			if (dish_2.getModel().isSelected() == true)
				dishSelected("dish_2");
			else
				dishDeSelected(exp2);

		} else if (source == dish_3) {
			System.out.println("dish_3");
			if (dish_3.getModel().isSelected() == true)
				dishSelected("dish_3");
			else
				dishDeSelected(exp3);
		} else if (source == dish_4) {
			System.out.println("dish_4");
			if (dish_4.getModel().isSelected() == true)
				dishSelected("dish_4");
			else
				dishDeSelected(exp4);
		} else if (source == dish_5) {
			System.out.println("dish_5");
			if (dish_5.getModel().isSelected() == true)
				dishSelected("dish_5");
			else
				dishDeSelected(exp5);
		} else if (source == dish_6) {
			System.out.println("dish_6");
			if (dish_6.getModel().isSelected() == true)
				dishSelected("dish_6");
			else
				dishDeSelected(exp6);
		} else if (source == dish_7) {
			System.out.println("dish_7");
			if (dish_7.getModel().isSelected() == true)
				dishSelected("dish_7");
			else
				dishDeSelected(exp7);
		} else if (source == dish_8) {
			System.out.println("dish_8");
			if (dish_8.getModel().isSelected() == true)
				dishSelected("dish_8");
			else
				dishDeSelected(exp8);
		} else if (source == dish_9) {
			System.out.println("dish_9");
			if (dish_9.getModel().isSelected() == true)
				dishSelected("dish_9");
			else
				dishDeSelected(exp9);
		}
	}
}
