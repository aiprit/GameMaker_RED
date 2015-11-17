package utils;

public class Point {
	
	final public double x, y;
	public static final Point ORIGIN = new Point(0, 0);
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return String.format("(%.2f, %.2f)", x, y);
	}

}
