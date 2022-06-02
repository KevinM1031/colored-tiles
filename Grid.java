
public class Grid {

	private int width;
	private int height;
	private long redistributionTime;
	
	public double[][] energyDistr;
	public Slot[][] slots;
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		redistributionTime = System.currentTimeMillis() + ColoredGrid.REDISTRIBUTION_PERIOD;
		
		energyDistr = new double[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				energyDistr[x][y] = (Math.random() > ColoredGrid.ACTIVE_CHANCE) ? 0 : 1;
			}
		}
		
		slots = new Slot[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				slots[x][y] = new Slot(x, y, getEnergy(x, y, width, height));
			}
		}
	}
	
	public void update() {
		
		Slot[][] newSlots = slots.clone();
		
		// Updating grid
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				
				Slot slot = slots[x][y];
				int total = slot.getTotal();
				
				// Default color spread
				if (total*total > Math.random()*slot.MAX_TOTAL*slot.MAX_TOTAL) {
					int newX = Util.bound(x + Math.random()*2 - 1, 0, width-1);
					int newY = Util.bound(y + Math.random()*2 - 1, 0, height-1);
					newSlots[newX][newY].mixColor(slot.r, slot.g, slot.b);
					
					// Color changing
					newSlots[x][y].addColor((int)(Math.random()*10-5), (int)(Math.random()*10-5), (int)(Math.random()*10-5));
					newSlots[x][y].chargeColor();
				}
				
				// Branching
				if (Math.pow((double)total/slot.MAX_TOTAL, 2) * ((slot.r + 256.0) / 511.0) > Math.random()*100) {
					newSlots[x][y].addColor((int)(Math.random()*40-20), (int)(Math.random()*40-20), (int)(Math.random()*40-20));
					newSlots[x][y].randomizeBranch();
				}
				
				// Branch spread
				if (slot.branching) {
					int[] xy = slot.branchOnce();
					int newX = Util.bound(x + xy[0], 0, width-1);
					int newY = Util.bound(y + xy[1], 0, height-1);
					newSlots[newX][newY].setColor(slot.r, slot.g, slot.b);
					newSlots[x][y].branching = false;
					
					if (slot.magnitude > 0) {
						newSlots[newX][newY].randomizeBranch();
						newSlots[newX][newY].magnitude = slot.magnitude - 1;
					}
				}
			}
		}
		
		// New grid energy distribution
		if (redistributionTime < System.currentTimeMillis()) {
			redistributionTime = System.currentTimeMillis() + ColoredGrid.REDISTRIBUTION_PERIOD;

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					energyDistr[x][y] = (Math.random() > ColoredGrid.ACTIVE_CHANCE) ? 0 : 1;
				}
			}
		}
				
		slots = newSlots;
	}
	
	private double getEnergy(int x, int y, int width, int height) {
		int gap = ColoredGrid.GAP;
		double halfGap = gap / 2.0;
		int xOff = Math.max(0, width % gap / 2);
		int yOff = Math.max(0, height % gap / 2);
		
		double nx = (double)Math.abs(Math.abs(x - xOff) % gap - halfGap) / gap;
		double ny = (double)Math.abs(Math.abs(y - yOff) % gap - halfGap) / gap;
		
		double n = (nx + ny) * energyDistr[x/gap][y/gap] * 1.1;
		return Math.pow(n, 5);
	}
	
	
}