import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Painter extends JPanel {

	private static final long serialVersionUID = 1L;
	private Grid grid;
	
	public Painter(int width, int height, Grid grid) {
		setSize(width, height);
		this.grid = grid;
	}
	
	@Override
	public void paintComponent(Graphics G) {
		
		for (Slot[] row : grid.slots) {
			for (Slot slot : row) {
				G.setColor(slot.color);
				G.fillRect(slot.x*ColoredGrid.PIXEL, slot.y*ColoredGrid.PIXEL, ColoredGrid.PIXEL, ColoredGrid.PIXEL);
			}
		}
		
		int xOff = Math.max(0, ColoredGrid.GRIDWIDTH % ColoredGrid.GAP / 2);
		int yOff = Math.max(0, ColoredGrid.GRIDHEIGHT % ColoredGrid.GAP / 2);
				
		for (Slot[] row : grid.slots) {
			for (Slot slot : row) {
				if (slot.x % ColoredGrid.GAP == 0 && slot.y % ColoredGrid.GAP == 0) {
					G.setColor(Color.darkGray);
					G.drawRect((slot.x + xOff)*ColoredGrid.PIXEL + ColoredGrid.PIXEL/2-1, 
							(slot.y + yOff)*ColoredGrid.PIXEL + ColoredGrid.PIXEL/2-1, 2, 2);
				}
			}
		}
	}
}
