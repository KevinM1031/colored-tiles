import javax.swing.JFrame;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Grid grid;
	
	public Window(String title, int width, int height, Grid grid) {
		setName(title);
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setContentPane(new Painter(width, height, grid));
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		
		setVisible(true);
		
		int decorWidth = getInsets().left + getInsets().right;
		int decorHeight = getInsets().top + getInsets().bottom;
		setSize(width + decorWidth, height + decorHeight);
		
		this.grid = grid;
		grid.slots[12][4].setColor(255, 0, 0);
		grid.slots[31][41].setColor(0, 255, 0);
		grid.slots[47][73].setColor(0, 0, 255);
		
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {
				grid.slots[30+i][30+j].setColor(0, 0, 255);
			}
		}
		
		for (int i = 70; i < 140; i++) {
			for (int j = 10; j < 90; j++) {
				grid.slots[10+i][0+j].setColor(255, 0, 0);
			}
		}
		
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {
				grid.slots[60+i][40+j].setColor(0, 255, 0);
			}
		}
	}
	
	public void update() {
		grid.update();
		repaint();
		revalidate();
	}

}