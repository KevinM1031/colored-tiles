
public class Util {

	public static int bound(double num, int min, int max) {
		return (int) Math.max(min, Math.min(max, Math.round(num)));
	}
	
	public static int bound(int num, int min, int max) {
		return Math.max(min, Math.min(max, num));
	}
	
	public static int signOf(double num) {
		return (num > 0) ? 1 : ((num < 0) ? -1 : 0);
	}
	
}
