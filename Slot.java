import java.awt.Color;

public class Slot {
	
	public final double CHARGE_OFFSET = 0.2;
	public final int MAX_TOTAL = 255;
	
	public final int x, y;
	public final double energy;
	
	public int r, g, b;
	public Color color;
	
	public boolean branching = true;
	public double magnitude, h, v;
	
	public Slot(int x, int y, double energy) {
		this.x = x;
		this.y = y;
		this.energy = energy;
		
		r = g = b = 0;
		color = Color.black;
		h = 0f;
		v = 0f;
	}
	
	public void setColor(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.color = new Color(r, g, b);
	}
	
	public void mixColor(int r, int g, int b) {
		setColor(mix(this.r, r), mix(this.g, g), mix(this.b, b));
	}
	private int mix(int c, int n) {
		return (c + n) / 2;
	}
	
	public void chargeColor() {
		if (getTotal() < MAX_TOTAL)
			setColor(charge(r), charge(g), charge(b));
	}
	private int charge(int c) {
		return Util.bound((energy + CHARGE_OFFSET) * c, 0, 255);
	}
	
	public void addColor(int r, int g, int b) {
		setColor(Util.bound(this.r+r, 0, 255), Util.bound(this.g+g, 0, 255), Util.bound(this.b+b, 0, 255));
	}
	
	public int getSaturation() {
		return Math.max(r, Math.max(g, b));
	}
	
	public int getTotal() {
		return r + g + b;
	}
	
	public void randomizeBranch() {
		branching = true;
		magnitude = energy * (g+1) * Math.random() * ColoredGrid.GAP * 1.1;
		h = 2 * Math.random() - 1;
		v = 2 * Math.random() - 1;
	}
	
	public int[] branchOnce() {
		
		int x = 0;
		int y = 0;
		
		if (Math.random() * Math.abs(h) > Math.random() * Math.abs(v))
			x = Util.signOf(h);
		else y = Util.signOf(v);
		
		return new int[] {x, y};
	}
	
}