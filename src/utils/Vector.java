package utils;
/**
 * I ended up doing so much vector math I thought
 * a class would simplify things. You can init a
 * vector with x and y or with length and direction,
 * but then you must supply a third argument with
 * true. Vectors are immutable.
 * 
 * @author Austin
 *
 */
public class Vector {
	
	public final double x, y;
	public static final Vector ZERO = new Vector(0.0, 0.0);
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Vector(double length, double direction, boolean lengthDirectionGiven) {
		if (lengthDirectionGiven) {
			double radians = Math.toRadians(direction);
			this.x = length * Math.cos(radians);
			this.y = length * Math.sin(radians);
		} else {
			this.x = length;
			this.y = direction;
		}
	}
	
	// Return a normalized form of this vector
	public Vector normalize() {
		double length = this.length();
		return new Vector(this.x / length, this.y / length);
	}

	// New vector with same direction but different length
	public Vector setLength(double length) {
		double ourLength = this.length();
		return new Vector(length * this.x / ourLength, length * this.y / ourLength);
	}
	
	// New vector with an added length
	public Vector addLength(double length) {
		double theta = Math.atan2(this.y, this.x);
		return new Vector(this.x + length * Math.cos(theta), this.y + length * Math.sin(theta));
	}
	
	// Direction we are pointing
	public double direction() {
		return Math.toDegrees(Math.atan2(this.y, this.x));
	}
	
	// Length of vector
	public double length() {
		return Math.sqrt(Math.pow(this.x, 2.0) + Math.pow(this.y, 2.0));
	}
	
	// Scalar multiplication
	public Vector scale(double c) {
		return new Vector(c * this.x, c * this.y);
	}
	
	// Dot product
	public static double dot(Vector a, Vector b) {
		return a.x * b.x + a.y * b.y;
	}
	
	// Vector addition
	public static Vector add(Vector a, Vector b) {
		return new Vector(a.x + b.x, a.y + b.y);
	}
	
}
