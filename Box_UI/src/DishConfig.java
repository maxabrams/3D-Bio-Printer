
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class DishConfig extends JFrame {
	private final static int SCREEN_WIDTH = 480;
	private final static int SCREEN_HEIGHT = 800;
	private int dishNum;
	private String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz-_";
	private JTextField fileName;
	private JTextField numPics;
	private JTextField totalTime;
	private Keyboard keyboard;
	private static String[] times = { "secs", "mins", "hours", "days" };
	private JComboBox picMetrics;
	private JComboBox totalMetrics;
	private StatusPanel callPanel;
	
	public DishConfig(int dishNumber, StatusPanel callingPanel){
		super("Dish Configuration");
		dishNum = dishNumber;
		setLayout(new GridLayout(5,1));
		callPanel = callingPanel;
		
		Border etched=BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

		//create smaller panels
		Dimension fieldDimension = new Dimension(180,45);
		JPanel filePanel = new JPanel();
		JLabel fileLabel = new JLabel("Enter file name:");
		fileName = new JTextField();
		fileName.setBorder(etched);

		fileName.setPreferredSize(fieldDimension);
		filePanel.add(fileLabel);
		filePanel.add(fileName);
		
		JPanel picPanel = new JPanel();
		JLabel picLabel = new JLabel("Enter the image frequency:");
		numPics = new JTextField("1");
		numPics.setPreferredSize(fieldDimension);
		numPics.setBorder(etched);
		numPics.setHorizontalAlignment(JTextField.CENTER);
		
		picMetrics = new JComboBox(times);
		picPanel.add(picLabel);
		picPanel.add(numPics);
		picPanel.add(picMetrics);
		
		JPanel totalPanel = new JPanel();
		JLabel totalLabel = new JLabel("Enter the total time of the experiment:");
		totalTime = new JTextField("1");
		totalTime.setBorder(etched);
		totalTime.setHorizontalAlignment(JTextField.CENTER);
		totalTime.setPreferredSize(fieldDimension);
		totalMetrics = new JComboBox(times);
		totalPanel.add(totalLabel);
		totalPanel.add(totalTime);
		totalPanel.add(totalMetrics);
		
		//create keyboard
		keyboard = new Keyboard(alphabet, fileName); //set filename to default
		
		//add listeners AFTER keyboard
		fileName.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				keyboard.setDestination(fileName);
				keyboard.setAlphaKeys(true);
			}

			@Override
			public void focusLost(FocusEvent e) {
				
			}});
		
		numPics.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				keyboard.setDestination(numPics);
				keyboard.setAlphaKeys(false);
			}

			@Override
			public void focusLost(FocusEvent e) {
				
			}});
		
		totalTime.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				keyboard.setDestination(totalTime);
				keyboard.setAlphaKeys(false);
			}

			@Override
			public void focusLost(FocusEvent e) {
				
			}});
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {	
				callPanel.setDishConfig(checkFields(), dishNum, fileName.getText(), numPics.getText(), (String) picMetrics.getSelectedItem(), totalTime.getText(), (String) totalMetrics.getSelectedItem());
				dispose();
			}
			
		});
		
		//add everything to frame
		add(filePanel);
		add(picPanel);
		add(totalPanel);
		add(keyboard.getPanel());
		add(doneButton);
		setSize(new Dimension(SCREEN_HEIGHT, SCREEN_WIDTH));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	private boolean checkFields(){
		if(fileName.getText().isEmpty() || numPics.getText().isEmpty() || totalTime.getText().isEmpty()){
			return false;
		}else{
			return true;
		}
	}

}
