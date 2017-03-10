import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Overlay extends JFrame{
	private final static int GAME_WIDTH = 280; //780
	private final static int GAME_HEIGHT = 150; //600
	PlayBoundry window;

	public PlayBoundry getPanel(){
		return window;
	}
	 public Overlay(final int[] coords) {
	        JFrame frame = new JFrame("Script Window");
	        frame.setSize(GAME_WIDTH + 1, GAME_HEIGHT + 10);
	        frame.setUndecorated(true);
	        frame.setBackground(new Color(0, 0, 0, 0));
	        frame.setAlwaysOnTop(true);
	        frame.setLayout(new BorderLayout());
	        // Without this, the window is draggable from any non transparent
	        // point, including points  inside textboxes.
	        //frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);

	       // frame.getContentPane().setLayout(new java.awt.BorderLayout());
	        //frame.getContentPane().add(new JTextField("text field north"), java.awt.BorderLayout.NORTH);
	        window  = new PlayBoundry(frame, GAME_WIDTH, GAME_HEIGHT);
	        JButton startButton = new JButton("Start");
	        startButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Point startPoint = window.getLocationOnScreen();
					coords[0] = startPoint.x;
					coords[1] = startPoint.y;
					coords[2] = GAME_WIDTH;
					coords[3] = GAME_HEIGHT;
					
					
				}
			});
	 
	        
	       
	        frame.add(window, BorderLayout.CENTER);
	        frame.add(startButton, BorderLayout.SOUTH);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //frame.pack();
	 }
}