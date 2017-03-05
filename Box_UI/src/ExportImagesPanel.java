import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;

public class ExportImagesPanel implements ActionListener {
	private JPanel exportImagesPanel; // Panel to make modifications to
	private String panelName; // Name for panel
	private Font checkboxFont = new Font("Arial", Font.BOLD, 20);
	private String timeStampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
			.format(new Date());
	private Date timeStamp;

	private JButton dish_1, dish_2, dish_3, dish_4, dish_5, dish_6, dish_7,
			dish_8, dish_9;
	private Dish exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8, exp9;

	private Frame frame = new Frame();

	public ExportImagesPanel() {
		// Initialize components
		exportImagesPanel = new JPanel();

		// Initialize Group Layout object
		GroupLayout layout = new GroupLayout(exportImagesPanel);
		exportImagesPanel.setLayout(layout); // Add a layout manager to align
												// elements

		// specify automatic gap insertion
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		panelName = "Export Images"; // Assign name

		// Setup checkboxes
		dish_1 = new JButton("Dish 1");
		dish_2 = new JButton("Dish 2");
		dish_3 = new JButton("Dish 3");
		dish_4 = new JButton("Dish 4");
		dish_5 = new JButton("Dish 5");
		dish_6 = new JButton("Dish 6");
		dish_7 = new JButton("Dish 7");
		dish_8 = new JButton("Dish 8");
		dish_9 = new JButton("Dish 9");

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


	// TODO: only allow to click if already added
	public void dishSelected(Dish dish) {
		// end time after file name is input
		timeStampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Date());

		timeStamp = new Date();

		System.out.println("Selected:" + " " + timeStampString);
		System.out.println("Dish Name : " + dish.getFileName());
		System.out.println("Dish time start : " + dish.getTimeStart());
		System.out.println("Dish time start : " + dish.getCaptureRate());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();

		if (source == dish_1) {
			System.out.println("dish_1");
			dishSelected(exp1);
		} else if (source == dish_2) {
			System.out.println("dish_2");
			dishSelected(exp2);
		} else if (source == dish_3) {
			System.out.println("dish_3");
			dishSelected(exp3);
		} else if (source == dish_4) {
			System.out.println("dish_4");
			dishSelected(exp4);
		} else if (source == dish_5) {
			System.out.println("dish_5");
			dishSelected(exp5);
		} else if (source == dish_6) {
			System.out.println("dish_6");
			dishSelected(exp6);
		} else if (source == dish_7) {
			System.out.println("dish_7");
			dishSelected(exp7);
		} else if (source == dish_8) {
			System.out.println("dish_8");
			dishSelected(exp8);
		} else if (source == dish_9) {
			System.out.println("dish_9");
			dishSelected(exp9);
		}

	}

}
