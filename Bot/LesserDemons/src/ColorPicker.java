import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class ColorPicker extends JFrame {
	private static Robot bot;
	
	public ColorPicker(){
		super();
		setSize(800, 800);
	    setUndecorated(true);
	    setBackground(new Color(0, 0, 0, 0));
	    setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    addMouseListener(new MouseListener(){
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            System.out.print("Pixel Color at (" + e.getX()+","+e.getY() + ") is: ");             
	            try {
	                System.out.println(getPixel(e.getXOnScreen(),e.getYOnScreen()));
	            } catch (AWTException error) {
	                error.printStackTrace();
	            }

	        }

	        @Override
	        public void mouseEntered(MouseEvent e) {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void mousePressed(MouseEvent e) {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void mouseReleased(MouseEvent e) {
	            // TODO Auto-generated method stub

	        }

	    });
	}

	public static Color getPixel(int x,int y) throws AWTException{
	    Robot bot = new Robot();
	    return bot.getPixelColor(x, y);
	} 
	
	public static void main(String[] args){
		new ColorPicker();
	}
}