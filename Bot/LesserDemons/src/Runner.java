import java.awt.AWTException;

public class Runner {

	public static void main(String[] args) throws AWTException {
		int coords[] = new int[4];
		coords[0] = -1;
		Overlay boundry = new Overlay(coords);
		while(coords[0] == -1){
			//spinlock
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Starting Script");
		Bot bot = new Bot(coords[0], coords[1], coords[2], coords[3], boundry.getPanel());
		bot.start();
	}

}