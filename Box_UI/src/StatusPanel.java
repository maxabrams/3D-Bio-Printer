import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StatusPanel implements ActionListener {
	private static Color darkOrange= new Color(255,151,108);

	private JPanel statusPanel; // Panel to make modifications to
	private String panelName; // Name for panel
	private Font checkboxFont = new Font("Arial", Font.BOLD, 24);
	private String timeStampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
			.format(new Date());
	private Date timeStamp;

	private JCheckBox dish_0, dish_1, dish_2, dish_3, dish_4, dish_5, dish_6,
			dish_7, dish_8;
	private Dish exp0, exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8;

	private Frame frame = new Frame();
	private static String[] times = { "secs", "mins", "hours", "days" };

	private String captureMetric = "mins";
	private CameraThread cameraThread; // Save as global for now in case we need
	// to interact with it later

	static final String SECS = "secs";
	static final String MINS = "mins";
	static final String HOURS = "hours";
	static final String DAYS = "days";

	static final String PIC_PARENT_DIR = "/home/pi/Desktop/";

	private ArrayList<String> dishNames;
	String PRESPACE = "      ";
	Dish[] dishes;
	Dish[] namedExp;

	JCheckBox[] dishCheckBoxes;

	// store of all experiments that have been created by users
	ArrayList<Dish> namedExperiments;

	// public ArrayList <String> getdishNames(){
	// return dishNames;
	// }

	public StatusPanel(ArrayList<String> dishList) {

		namedExperiments = new ArrayList<Dish>();

		dishNames = dishList;

		// Initialize components
		statusPanel = new JPanel();
		// Initialize Grid bag layout
		statusPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		panelName = "Dashboard"; // Assign name

		// Setup checkboxes
		dish_0 = new JCheckBox("<html><pre>&nbsp;    1  <br/> </pre></html>");
		dish_1 = new JCheckBox("<html><pre>&nbsp;    2  <br/> </pre></html>");
		dish_2 = new JCheckBox("<html><pre>&nbsp;    3  <br/> </pre></html>");
		dish_3 = new JCheckBox("<html><pre>&nbsp;    4  <br/> </pre></html>");
		dish_4 = new JCheckBox("<html><pre>&nbsp;    5  <br/> </pre></html>");
		dish_5 = new JCheckBox("<html><pre>&nbsp;    6  <br/> </pre></html>");
		dish_6 = new JCheckBox("<html><pre>&nbsp;    7  <br/> </pre></html>");
		dish_7 = new JCheckBox("<html><pre>&nbsp;    8  <br/> </pre></html>");
		dish_8 = new JCheckBox("<html><pre>&nbsp;    9  <br/> </pre></html>");
//		dish_0.setFont(new Font("Ariel", Font.PLAIN, 6));
		// dish_0.setPreferredSize(new Dimension(30,30));
//		dish_0.setBorder(BorderFactory.createSoftBevelBorder(15));
//		dish_0.setBorderPainted(false);
//		dish_0.setMargin(new Insets(0, 0, 0, 0));
//		dish_0.setMaximumSize(new Dimension(30,30));
		// dish_0.setBackground(Color.BLUE);
		
	

		dish_0.addActionListener(this);
		dish_1.addActionListener(this);
		dish_2.addActionListener(this);
		dish_3.addActionListener(this);
		dish_4.addActionListener(this);
		dish_5.addActionListener(this);
		dish_6.addActionListener(this);
		dish_7.addActionListener(this);
		dish_8.addActionListener(this);

		dish_0.setFont(checkboxFont);
		dish_1.setFont(checkboxFont);
		dish_2.setFont(checkboxFont);
		dish_3.setFont(checkboxFont);
		dish_4.setFont(checkboxFont);
		dish_5.setFont(checkboxFont);
		dish_6.setFont(checkboxFont);
		dish_7.setFont(checkboxFont);
		dish_8.setFont(checkboxFont);

		// Add checkboxes to an array so that we can identify the dishes based
		// on index
		JCheckBox[] cb = { dish_0, dish_1, dish_2, dish_3, dish_4, dish_5,
				dish_6, dish_7, dish_8 };
		dishCheckBoxes = cb;

		// create Dishes
		exp0 = new Dish("dish_0");
		exp1 = new Dish("dish_1");
		exp2 = new Dish("dish_2");
		exp3 = new Dish("dish_3");
		exp4 = new Dish("dish_4");
		exp5 = new Dish("dish_5");
		exp6 = new Dish("dish_6");
		exp7 = new Dish("dish_7");
		exp8 = new Dish("dish_8");

		// Add dishes to an array for camera thread processing
		Dish[] d = new Dish[] { exp0, exp1, exp2, exp3, exp4, exp5, exp6, exp7,
				exp8 };
		dishes = d;
		System.out.println(dishes);
		System.out.println("======");

		cameraThread = new CameraThread(dishes);
		new Thread(cameraThread).start();

		int dim = 140;
	
		//print dishes on screen
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// i rows, j cols
				c.gridx = j;
				c.gridy = i;
				c.ipady = 55;
				c.ipadx = 55;
				c.insets= new Insets(5,5,5,5);
				
				System.out.println((3 * i) + j);
				statusPanel.add(cb[(3 * i) + j], c);
		
			}
		}

		// / this is super janky... find better way
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				checkPending();

			}
		}, 0, 1000);
		// / this is super janky... find better way
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
	 * Function to be called by Dish Config when user presses "done" on pop-up
	 * GUI. Example call of how to bring up my GUI, whenever you need to bring
	 * up the pop-up (on selection) use: new DishConfig(4, this);
	 */
	public void setDishConfig(boolean isValid, int dishNum, String fileName,
			String numPics, String picMetric, String numTotal,
			String totalMetric) {
		if (isValid) {
			// DO DISH SETUP HERE. Using print statement now to check everything
			// is working
			System.out.println("Dish config start:");
			System.out.println(dishNum);
			System.out.println(fileName);
			System.out.println(numPics);
			System.out.println(picMetric);
			System.out.println(numTotal);
			System.out.println(totalMetric);
			// figure out which dish is to be changed
			Dish dish = dishes[dishNum];

			if (createFolder(fileName) == false) { // Create folder if it has
													// not been made before. Add
													// to global array
				System.out.println("Error! File name already used for folder: "
						+ fileName);
				JCheckBox temp = dishCheckBoxes[dishNum];
				temp.setSelected(false);
				return;
			}

			int captureRate = Integer.parseInt(numPics);

			if (captureRate < 1) {
				captureRate = 1;
			}
			dish.setFileName(fileName);
			System.out.println("Dish Name Input: " + dish.getFileName());

			dish.setCaptureRate(captureRate);
			dish.setCaptureMetric(picMetric);
			dish.setExperimentTime(Integer.parseInt(numTotal));
			dish.setExperimentMetric(totalMetric);
			dish.setDishNum(dishNum);

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
			// TODO: This math may or may not work- also may need to add a
			// couple if we always want to guarantee a picture at the beginning
			// and end of each experiment
			else {
				int capTimeSec = Integer.MAX_VALUE; // Fake value to override;
				int expTimeSec = Integer.MAX_VALUE;// Fake value to override;

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
				dish.setTotalImagesNeeded(expTimeSec / capTimeSec);
				System.out.println("Total # images taken: "
						+ dish.getTotalImagesNeeded());
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
			dishCheckBoxes[dishNum].setForeground(darkOrange);
			if (fileName.length() > 5) {
				// to avoid resizing circle
				dishCheckBoxes[dishNum].setLabel("<html>"
						+ fileName.substring(0, 5) + "<br/>0/"
						+ dish.getTotalImagesNeeded() + "</html>");

			} else {
				dishCheckBoxes[dishNum].setLabel("<html>" + fileName
						+ "<br/>0/" + dish.getTotalImagesNeeded() + "</html>");

			}
			namedExperiments.add(dish);

		}

		else {
			dishCheckBoxes[dishNum].setSelected(false);
			System.out.println("Invalid Input");
		}
		System.out.println("Dish config end");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		int counter = 0;
		for (JCheckBox x : dishCheckBoxes) {
			if (source == x) {
				if (x.getModel().isSelected() == true) {
					DishConfig dc = new DishConfig(counter, this);
				} else {
					// The experiment is already started for this dish
					System.out.println("deselect");
					editDish(counter);
					// options should be to download photos, stop experiment,
					// edit experiment
				}
			}
			counter += 1;
		}
	}

	private boolean createFolder(String fileName) {
		if (dishNames.contains(fileName)) {
			return false;
		}

		File dir = new File(PIC_PARENT_DIR + fileName);
		/*
		 * if(dir.exists()){//If it exists in OS and we don't have it saved in
		 * global, delete (can be modified later) dir.delete(); }
		 */
		dir.mkdirs();
		dishNames.add(fileName);
		return true;
	}

	public void editDish(int dishNum) {
		// if dish is already selected, pull up an options menu- edit name,
		// image capture, exit experiment, download photos
		// don't uncheck the box unless they exit experiment

		showDishMenu(dishes[dishNum], dishNum);

		timeStampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Date());
		timeStamp = new Date();

		System.out.println("Deselected" + " " + timeStampString);
		System.out.println("Dish Deselected: " + dishes[dishNum].getFileName());

		/*
		 * TODO: once "deselect" bug is fixed, just use: dish.isEnabled(false)
		 * to stop photos from being taken
		 */
	}

	public void showDishMenu(final Dish dish, final int dishNum) {
		dishCheckBoxes[dishNum].setSelected(true);

		JPanel myPanel = new JPanel();
		// Stop the experiment- use if want to stop image capture
		final JButton stop = new JButton("Clear Experiment");
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dish.setEnabled(false);
				dishCheckBoxes[dishNum].setSelected(false);
				dish.reset();
				dishPending(dish);
				dishCheckBoxes[dishNum].setLabel("<html><pre>&nbsp;    "+(dishNum + 1)+"  <br/> </pre></html>");
			}
		});
		myPanel.add(stop);

		// pop up dialog
		CloseDialog x = new CloseDialog( dish.getFileName()+ " Options", new JPanel(), stop, 200, 100);
		dishCheckBoxes[dishNum].setForeground(Color.BLACK);

	}

	public void checkPending() {
		for (Dish d : dishes) {
			dishPending(d);
		}
	}

	public void dishPending(Dish d) {
		// If experiment is finished, change label to Done
		// System.out.println( namedExperiments);
		// System.out.println(dishes);
		if (namedExperiments.contains(d) && d.isFinished() && !d.isCleared()) {
			d.setCleared(true);
			dishes[d.getDishNum()] = new Dish();
			dishCheckBoxes[d.getDishNum()].setBorderPainted(false);
			dishCheckBoxes[d.getDishNum()].setForeground(new Color(0,150,0));
			if (d.getFileName().length() > 5) {
				dishCheckBoxes[d.getDishNum()].setLabel("<html><pre>&nbsp;  "
						 + d.getFileName().substring(0, 5) + "<br/>&nbsp;  "
						+ d.getPicsTaken() + "/" + d.getTotalImagesNeeded()
						+ "</pre></html>");

			} else {
				dishCheckBoxes[d.getDishNum()].setLabel("<html><pre>&nbsp;  "
					+  d.getFileName() + "<br/>&nbsp;  "
						+ d.getPicsTaken() + "/" + d.getTotalImagesNeeded()
						+ "</pre></html>");
			}
			System.out.println("Done" + d.getDishNum());

		}
		// if experiment is not finished, update number of photos taken
		else if (namedExperiments.contains(d) && !d.isCleared()) {
			System.out.println(d.getFileName() + "  dfsd");
			String fn = d.getFileName();
			if (fn.length() > 5) {
				// to avoid resizing circle
				dishCheckBoxes[d.getDishNum()].setLabel("<html><pre>&nbsp;  "
						+ fn.substring(0, 5) + "<br/>&nbsp;  " + d.getPicsTaken() + "/"
						+ d.getTotalImagesNeeded() + "</pre></html>");

			} else {
				dishCheckBoxes[d.getDishNum()].setLabel("<html><pre>&nbsp;  " + fn + "<br/>&nbsp;  "
						+ d.getPicsTaken() + "/" + d.getTotalImagesNeeded()
						+ "</pre></html>");

			}
		}
	}

}
