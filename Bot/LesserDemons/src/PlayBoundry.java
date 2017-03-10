import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayBoundry extends JPanel{
    private Point initialClick;
    private JFrame parent;
    private int width;
	private int height;
	private ArrayList<Point> highlights;

    public PlayBoundry(final JFrame parent, int width, int height){
	    this.parent = parent;
	    this.width = width;
	    this.height = height;
	    highlights = new ArrayList<Point>();

	
	    addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            initialClick = e.getPoint();
	            getComponentAt(initialClick);
	        }
	    });
	
	    addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {
	
	            // get location of Window
	            int thisX = parent.getLocation().x;
	            int thisY = parent.getLocation().y;
	
	            // Determine how much the mouse moved since the initial click
	            int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
	            int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);
	
	            // Move window to this position
	            int X = thisX + xMoved;
	            int Y = thisY + yMoved;
	            parent.setLocation(X, Y);
	        }
	    });
    }
    
    @Override
    public void paint(Graphics g){
		g.setColor(Color.CYAN);
		g.drawLine(0, 0, 0, height);
		g.drawLine(0, 0, width, 0);
		g.drawLine(0, height, width, height);
		g.drawLine(width, 0, width, height);
		//g.setColor(Color.RED);
		//g.drawOval(500, 600, 10, 10);
		g.setColor(Color.GREEN);
	    for (Point p: highlights) {
	           // draw rectangle
	       	g.drawLine((int)p.getX(), (int)p.getY(), (int)p.getX(), (int)p.getY());
	        	
	   }
	 }
    
    /*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        for (Point p: highlights) {
            // draw rectangle
        	g2.drawLine((int)p.getX(), (int)p.getY(), (int)p.getX(), (int)p.getY());
        	
        }
    }
    */
    
    protected void addPoint(Point p){
    	highlights.add(p);
    }
    
    protected void refresh(){
    	repaint();
    }
    
}