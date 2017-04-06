import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Keyboard extends JFrame {
	private final static int SCREEN_WIDTH = 480;
	private final static int SCREEN_HEIGHT = 800;
	String alphabet;
	JTextField destination;
	JTextField popUp;
	int numRow;
	int numCol;
	JPanel keyboardPanel;
	ArrayList<JButton> buttons;
	JPanel retPanel;
	
	public Keyboard(String alphabet, JTextField destination, int numRow, int numCol, JPanel returnPanel){
		super("Keyboard");
		this.alphabet = alphabet;
		this.destination = destination;
		this.numRow = numRow;
		this.numCol = numCol;
		this.retPanel = returnPanel; 
		setLayout(new BorderLayout());
		popUp = new JTextField(destination.getText());
		popUp.setHorizontalAlignment(JTextField.CENTER);
		add(popUp, BorderLayout.NORTH);
		createKeyboard();
		setSize(new Dimension(SCREEN_HEIGHT, SCREEN_WIDTH));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public Keyboard(String alphabet, JTextField destination){
		super("Keyboard");
		this.alphabet = alphabet;
		this.destination = destination;
		setLayout(new BorderLayout());
		this.keyboardPanel = createKeyboard(3, 10);
		pack();
		setVisible(false);
	}
	
	public JPanel getPanel(){
		return keyboardPanel;
	}
	
	private void createKeyboard(){
		
		JPanel buttonPannel = new JPanel();
		buttonPannel.setLayout(new GridLayout(this.numRow, this.numCol));
		//Auto populate with given keys
		for (int i = 0; i < this.alphabet.length(); i++) {
			JButton addButton = new JButton(this.alphabet.substring(i, i + 1));
	        addButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					String action = e.getActionCommand();
					destination.setText(destination.getText() + action);
					popUp.setText(popUp.getText() + action);
							
				}
			});
	        buttonPannel.add(addButton);
	       
	    }
		
		//Add seperate panel for formatting
		//JPanel stdButtonPanel = new JPanel();
		//stdButtonPanel.setLayout(new GridLayout(2, 1));
		
		//Add a delete button
		JButton deleteButton = new JButton("Del"); 
		deleteButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {	
					if(destination.getText().length() > 0){
						//Only remove one if there is one to remove
						destination.setText(destination.getText().substring(0, destination.getText().length() - 1));
						popUp.setText(popUp.getText().substring(0, popUp.getText().length() - 1));
					}else{
						System.out.println("cannot print");
					}
				}
		});
		//stdButtonPanel.add(deleteButton);	
		buttonPannel.add(deleteButton, BorderLayout.CENTER);
		
		//Add done button to close keyboard
		JButton doneButton = new JButton("DONE");
		doneButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					if(popUp.getText().isEmpty()){
						popUp.setText("0");
						destination.setText("0");
					}
					dispose();
					retPanel.requestFocusInWindow();
					
				}
			});
		//stdButtonPanel.add(doneButton); //Always add a "done" button to exit
		
		//Add to main panel
		//add(stdButtonPanel, BorderLayout.SOUTH);
		buttonPannel.add(doneButton, BorderLayout.CENTER);
		add(buttonPannel, BorderLayout.CENTER);
	}		
	
private JPanel createKeyboard(int row, int col){
		
		JPanel buttonPannel = new JPanel();
		buttonPannel.setLayout(new GridLayout(row, col));
		buttons = new ArrayList<JButton>();
		//Auto populate with given keys
		for (int i = 0; i < this.alphabet.length(); i++) {
			JButton addButton = new JButton(this.alphabet.substring(i, i + 1));
	        addButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					String action = e.getActionCommand();
					destination.setText(destination.getText() + action);
							
				}
			});
	        buttons.add(addButton);
	        buttonPannel.add(addButton);
	       
	        
	        
	    }

		
		//Add a delete button
		JButton deleteButton = new JButton("Del"); 
		deleteButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {	
					if(destination.getText().length() > 0){
						//Only remove one if there is one to remove
						destination.setText(destination.getText().substring(0, destination.getText().length() - 1));
					}else{
						//System.out.println("cannot print");
					}
				}
		});
		buttonPannel.add(deleteButton);
		
		//Let frame that contains keyboard create its own done Button
		return buttonPannel;
	}

	public void setDestination(JTextField newField){
		this.destination = newField;
	}
	
	public void setAlphaKeys(boolean enabled){
		if(enabled){
			for(JButton button : buttons){
				button.setEnabled(true);
			}
		}else{
			//Disable alphabetic buttons
			for(JButton button : buttons){
				if(Character.isDigit(button.getText().charAt(0)) == false){
					button.setEnabled(false);
				}
			}
		}
	}
}