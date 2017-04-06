import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
	private static String[] times = { "secs", "mins", "hours", "days" };

	private String captureMetric = "mins";
	private String expMetric = "mins";
	private int expTime;
	private CameraThread cameraThread; // Save as global for now in case we need
										// to interact with it later

	static final String SECS = "secs";
	static final String MINS = "mins";
	static final String HOURS = "hours";
	static final String DAYS = "days";
	
	static final String PIC_PARENT_DIR = "/Users/maxwell/Desktop/";//"/home/pi/desktop/";
	
	private ArrayList<String> dishNames;
	
//	public ArrayList <String> getdishNames(){
//		return dishNames;	
//	}
//	

	public StatusPanel(ArrayList<String> dishList) {
		dishNames=dishList;

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
		exp1 = new Dish("dish_1");
		exp2 = new Dish("dish_2");
		exp3 = new Dish("dish_3");
		exp4 = new Dish("dish_4");
		exp5 = new Dish("dish_5");
		exp6 = new Dish("dish_6");
		exp7 = new Dish("dish_7");
		exp8 = new Dish("dish_8");
		exp9 = new Dish("dish_9");

		// Add dishes to an array for camera thread processing
		Dish[] dishes = { exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8, exp9 };
		cameraThread = new CameraThread(dishes);
		new Thread(cameraThread).start();

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

	/*
	 * Function to be called by Dish Config when user presses "done" on pop-up GUI.
	 * Example call of how to bring up my GUI, whenever you need to bring up the pop-up (on selection) use: new DishConfig(4, this);
	 */
	public void setDishConfig(boolean isValid, int dishNum, String fileName, String numPics, String picMetric, String numTotal, String totalMetric){
		if(isValid){
			//DO DISH SETUP HERE. Using print statment now to check everything is working
			System.out.println("Dish config start:");
			System.out.println(dishNum);
			System.out.println(fileName);
			System.out.println(numPics);
			System.out.println(picMetric);
			System.out.println(numTotal);
			System.out.println(totalMetric);
			System.out.println("Dish config end");
		}
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
			// don't select dish if they don't input a name
			JCheckBox temp = whatCheckBox(dish);
			temp.setSelected(false);
			return "";
		}
	}

	// Get image capture rate information
	public int showImageDialogBox(String dish) {

		// captureRate defaults to 1 a minute
		SpinnerNumberModel captureModel = new SpinnerNumberModel(1, 0, 60, 1);
		JSpinner captureSpinner = new JSpinner(captureModel);

		SpinnerNumberModel experimentModel = new SpinnerNumberModel(1, 0, 100,
				1);
		JSpinner experimentSpinner = new JSpinner(experimentModel);

		// Create the combo box, select item at index 4.
		JComboBox timeList = new JComboBox(times);
		JComboBox expMetricList = new JComboBox(times);

		JPanel myPanel = new JPanel();

		GroupLayout layout = new GroupLayout(myPanel);
		myPanel.setLayout(layout); // Add a layout manager to align elements
		JLabel captureLabel = new JLabel("1 panel every");

		JLabel expLabel = new JLabel("Total time of experiment: ");

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup().addComponent(captureLabel)
								.addComponent(expLabel))
				.addGroup(
						layout.createParallelGroup()
								.addComponent(captureSpinner)
								.addComponent(experimentSpinner))
				.addGroup(
						layout.createParallelGroup().addComponent(timeList)
								.addComponent(expMetricList)));
		layout.setVerticalGroup(layout
				.createParallelGroup()
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(captureLabel)
								.addComponent(expLabel))
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(captureSpinner)
								.addComponent(experimentSpinner))
				.addGroup(
						layout.createSequentialGroup().addComponent(timeList)
								.addComponent(expMetricList)));

		// Show Option Pop up
		JOptionPane.showConfirmDialog(null, myPanel, "Enter Capture Rate",
				JOptionPane.OK_CANCEL_OPTION);

		frame.dispose();

		int captureRate = Integer.parseInt(captureModel.getValue().toString());
		captureMetric = (String) timeList.getSelectedItem();

		expTime = Integer.parseInt(experimentModel.getValue().toString());
		expMetric = (String) expMetricList.getSelectedItem();

		return captureRate;
	}

	public void dishSelected(String dishName) {
		// Show dialog boxes for dish name input and capture rate
		String fileName = showNameDialogBox(dishName);

		// Figure out which dish was chosen
		if (fileName == "") {
			System.out.println("exit before enter name");
			return;
		} else {
			
			if(createFolder(fileName) == false){ //Create folder if it has not been made before. Add to global array
				System.out.println("Error! File name already used for folder: " + fileName);
				JCheckBox temp = whatCheckBox(dishName);
				temp.setSelected(false);
				return;
			}
			// don't select the dish if they don't enter a name
			Dish dish = whatDish(dishName);
			int captureRate = showImageDialogBox(dishName);
			if (captureRate < 1) {
				captureRate = 1;
			}
			dish.setFileName(fileName);
			System.out.println("Dish Name Input: " + dish.getFileName());

			dish.setCaptureRate(captureRate);
			dish.setCaptureMetric(captureMetric);
			dish.setExperimentTime(expTime);
			dish.setExperimentMetric(expMetric);

			System.out.println("Capture Rate: 1 image every "
					+ dish.getCaptureRate() + " " + dish.getCaptureMetric());
			System.out.println("Total exp time: " + dish.getExperimentTime()
					+ " " + dish.getExperimentMetric());

			// Figure out how many pictures total are needed for the experiment
			// if same metric, easy
			if (dish.getCaptureMetric() == dish.getExperimentMetric()) {
				dish.setTotalImagesNeeded(dish.getExperimentTime()
						/ dish.getCaptureRate());
				System.out.println("Total # images taken: "
						+ dish.getTotalImagesNeeded());
			}
			// if different metric, do math
			//TODO: This math may or may not work- also may need to add a couple if we always want to guarantee a picture at the beginning and end of each experiment
			else{
				int capTimeSec=Integer.MAX_VALUE; //Fake value to override;
				int expTimeSec=Integer.MAX_VALUE;//Fake value to override;
				
				switch (dish.getCaptureMetric()) { 
					case SECS:
						capTimeSec = dish.getCaptureRate() * 1000;
						break;
					case MINS:
						capTimeSec = dish.getCaptureRate() * 60 * 1000;
						break;
					case HOURS:
						capTimeSec = dish.getCaptureRate() * 60 * 60 * 1000;
						break;
					case DAYS:
						capTimeSec = dish.getCaptureRate() * 24 * 60 * 60 * 1000;
						break;
				}
				switch (dish.getExperimentMetric()) { 
					case SECS:
						expTimeSec = dish.getExperimentTime() * 1000;
						break;
					case MINS:
						expTimeSec = dish.getExperimentTime() * 60 * 1000;
						break;
					case HOURS:
						expTimeSec = dish.getExperimentTime() * 60 * 60 * 1000;
						break;
					case DAYS:
						expTimeSec = dish.getExperimentTime() * 24 * 60 * 60 * 1000;
						break;
				}
				dish.setTotalImagesNeeded(expTimeSec/ capTimeSec);
				System.out.println("Total # images taken: " + dish.getTotalImagesNeeded());
			}
			

			// start time after file name is input
			timeStampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			timeStamp = new Date();

			System.out.println("Selected:" + " " + timeStampString);
			dish.setTimeStart(timeStamp); // TODO: might want to move this into
											// constructor to avoid NULL errors
											// later. Fine for now.
			dish.setTimeOfLastPic(timeStamp);
			dish.setEnabled(true);// last thing to set to start taking photos
			System.out.println("Time Start: " + dish.getTimeStart());
		}
	}

	private boolean createFolder(String fileName) {
		if(dishNames.contains(fileName)){
			return false;
		}
		
		File dir = new File(PIC_PARENT_DIR+fileName);
		if(dir.exists()){//If it exists in OS and we don't have it saved in global, delete (can be modified later)
			dir.delete();
		}
		dir.mkdir();
		dishNames.add(fileName);
		return true;
	}

	public void showDishMenu(Dish dish) {
		// Preset values with what is already there
		SpinnerNumberModel captureModel = new SpinnerNumberModel(
				dish.getCaptureRate(), 0, 60, 1);
		JSpinner captureSpinner = new JSpinner(captureModel);

		JComboBox timeList = new JComboBox(times);

		timeList.setSelectedIndex(Arrays.asList(times).indexOf(
				dish.getCaptureMetric()));

		JPanel myPanel = new JPanel();
		// edit name
		myPanel.add(new JLabel("Edit Dish Name:"));

		// edit image capture rate
		myPanel.add(new JLabel("Edit image capture rate:"));
		myPanel.add(new JLabel("1 panel every"));
		// input number
		myPanel.add(captureSpinner);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		// input second/minute/hour
		myPanel.add(timeList);

		// Show Option Pop up
		JOptionPane.showConfirmDialog(null, myPanel, "Dish Menu",
				JOptionPane.OK_CANCEL_OPTION);

		frame.dispose();

		int captureRate = Integer.parseInt(captureModel.getValue().toString());
		if (captureRate < 1) {
			captureRate = 1;
		}
		captureMetric = (String) timeList.getSelectedItem();
		dish.setCaptureRate(captureRate);
		dish.setCaptureMetric(captureMetric);
		whatCheckBox(dish.getDishString()).setSelected(true);

	}

	public void dishDeSelected(Dish dish) {
		// if dish is already selected, pull up an options menu- edit name,
		// image capture, exit experiment, download photos
		// don't uncheck the box unless they exit experiment

		showDishMenu(dish);

		timeStampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Date());
		timeStamp = new Date();

		System.out.println("Deselected" + " " + timeStampString);
		System.out.println("Dish Deselected: " + dish.getFileName());

		/*
		 * TODO: once "deslect" bug is fixed, just use: dish.isEnabled(false) to
		 * stop photos from being taken
		 */
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

	public JCheckBox whatCheckBox(String dish) {
		JCheckBox temp = new JCheckBox();
		if (dish == "dish_1") {
			temp = dish_1;
		} else if (dish == "dish_2") {
			temp = dish_2;
		} else if (dish == "dish_3") {
			temp = dish_3;
		} else if (dish == "dish_4") {
			temp = dish_4;
		} else if (dish == "dish_5") {
			temp = dish_5;
		} else if (dish == "dish_6") {
			temp = dish_6;
		} else if (dish == "dish_7") {
			temp = dish_7;
		} else if (dish == "dish_8") {
			temp = dish_8;
		} else if (dish == "dish_9") {
			temp = dish_9;
		} else {
			System.out.println("Invalid Dish");
		}
		return temp;
	}

	public Dish whatDish(String dishName) {
		Dish dish = new Dish();
		// don't select the dish if they don't enter a name
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
		}
		return dish;
	}
	

}
