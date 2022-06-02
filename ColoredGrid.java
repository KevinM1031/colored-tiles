import java.awt.Toolkit;

public class ColoredGrid {

	private static final String TITLE = "Colored Grid";
	private final static boolean RUNNING = true;
	
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final long FPS = 10;
	public static final long delayInMS = 1000/FPS;
	public static final double timeSpeed = 12;
	
	public static final int PIXEL = 10;
	public static final int DENSITY = 18;
	public static final double ACTIVE_CHANCE = 0.3;
	public static final long REDISTRIBUTION_PERIOD = 200000;
	
	public static final int GRIDWIDTH = WIDTH / PIXEL;
	public static final int GRIDHEIGHT = HEIGHT / PIXEL;
	public static final int GAP = GRIDWIDTH / DENSITY;
	
	public static void main(String[] args) {		
		defaultDriver();
	}
	
	private static void defaultDriver() {
		Grid G = new Grid(WIDTH / PIXEL, HEIGHT / PIXEL);
		Window W = new Window(TITLE, WIDTH, HEIGHT, G);
		
		long t1, t2 = System.currentTimeMillis(), updateCount = 0;
		while(RUNNING) {
			
			t1 = System.currentTimeMillis();
			
			W.update();
			
			updateCount++;
			if(System.currentTimeMillis()-t2 >= 1000) {
				System.out.println("[SYSTEM] FPS: " + updateCount 
						+ " (maximum: " + FPS + ")");
				updateCount = 0;
				t2 = System.currentTimeMillis();
			}
			
			try {
				Thread.sleep(delayInMS - (System.currentTimeMillis()-t1)%delayInMS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}