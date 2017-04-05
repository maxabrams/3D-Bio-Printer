import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Keyboard extends JFrame {
	String alphabet;
	JTextField destination;
	int numRow;
	int numCol;
	
	public Keyboard(String alphabet, JTextField destination, int numRow, int numCol){
		super("Keyboard");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.alphabet = alphabet;
		this.destination = destination;
		this.numRow = numRow;
		this.numCol = numCol;
		setLayout(new BorderLayout());
		createKeyboard();
		pack();
		setVisible(true);
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
							
				}
			});
	        buttonPannel.add(addButton);
	        add(buttonPannel, BorderLayout.CENTER);
	    }
		
		//Add seperate panel for formatting
		JPanel stdButtonPanel = new JPanel();
		stdButtonPanel.setLayout(new GridLayout(2, 1));
		
		//Add a delete button
		JButton deleteButton = new JButton("Delete"); 
		deleteButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {	
					if(destination.getText().length() > 0){
						//Only remove one if there is one to remove
						destination.setText(destination.getText().substring(0, destination.getText().length() - 1));
					}else{
						System.out.println("cannot print");
					}
				}
		});
		stdButtonPanel.add(deleteButton);	
		
		//Add done button to close keyboard
		JButton doneButton = new JButton("DONE");
		doneButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {	
					dispose();
					
				}
			});
		stdButtonPanel.add(doneButton); //Always add a "done" button to exit
		
		//Add to main panel
		add(stdButtonPanel, BorderLayout.SOUTH);
	}		
}