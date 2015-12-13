// This entire file is part of my masterpiece.
// Austin McKee

package utils.rectangle;

import utils.Point;
import utils.Utils;

/**
 * A rectangle defined at a given x,y, rotated and scaled as desired.
 * Center X and Center Y define an offset from the top-left corner of
 * the unrotated rectangle, explicitly setting the axis of rotation.
 * All of the hard logic for calculations has been abstracted out as
 * static functions on the interface class, and implementing classes
 * will most likely vary simply in how they store that data (although
 * they could most certainly override the math as well).
 * 
 * @author Austin McKee
 *
 */
public interface IRectangle {
	
	public enum quadrant {
		TOP,
		LEFT,
		RIGHT,
		BOTTOM
	}

	Point topLeft();

	Point topRight();

	Point bottomLeft();

	Point bottomRight();
	
	Point centerPoint();

	boolean contains(Point p);

	boolean intersects(IRectangle rect);
	
	quadrant quadrantOfPoint(Point p);

	double x();

	double y();

	double centerX();

	double centerY();

	double width();

	double height();

	double angle();

	IRectangle clone();
	
	/*
	 * The IRectangle interface does, unfortunately, expose
	 * a bit of information regarding what classes implement us
	 * with the following methods. However, these methods should
	 * still be requirements of all implementing classes.
	 */
	Rectangle getMutable();
	ImmutableRectangle getImmutable();
	
	/**
	 * Does the given rectangle enclose a given point?
	 * 
	 * @param rect
	 * @param p
	 * @return
	 */
	static boolean contains(IRectangle rect, Point p) {
		
		// We can get MUCH faster answer if we aren't rotated
		if (rect.angle() == 0.0) {
			return (p.x >= rect.x() - rect.centerX() && p.x <= rect.x() - rect.centerX() + rect.width() && p.y >= rect.y() - rect.centerY() && p.y <= rect.y() - rect.centerY() + rect.height());
		}
		
		double s = Math.sin(-1 * Math.toRadians(rect.angle()));
		double c = Math.cos(-1 * Math.toRadians(rect.angle()));
		
		double p2x = c * (p.x - rect.x()) - s * (p.y - rect.y()) + rect.x();
		double p2y = s * (p.x - rect.x()) + c * (p.y - rect.y()) + rect.y();
		
		double ax = rect.x() - rect.centerX();
		double ay = rect.y() - rect.centerY();
		double dx = rect.x() - rect.centerX() + rect.width();
		double dy = rect.y() - rect.centerY() + rect.height();
		
		if ((dy > ay && p2y >= ay && p2y <= dy) || (dy < ay && p2y <= ay && p2y >= dy )) {
			if ((dx > ax && p2x >= ax && p2x <= dx) || (dx < ax && p2x <= ax && p2x >= dx)) {
				return true;
			}
		}
		return false;	
	}
	
	/*
	 * The following methods find useful points on a given rectangle, taking into
	 * account all transformations on the rectangle. They all avoid a lot of
	 * time-consuming trigonometry in the case that the rectangle isn't rotated.
	 */
	public static Point bottomRight(IRectangle rect) {
		if (rect.angle() == 0.0) {
			return new Point(rect.x() - rect.centerX() + rect.width(), rect.y() - rect.centerY() + rect.height());
		}
		double thetac = Math.toRadians(rect.angle()) + Math.atan2(rect.height() - rect.centerY(), rect.width() - rect.centerX());
		double h = hypot(rect.width() - rect.centerX(), rect.height() - rect.centerY());
		return new Point(rect.x() + h * Math.cos(thetac), rect.y() + h * Math.sin(thetac));			
	}
	
	public static Point bottomLeft(IRectangle rect) {
		if (rect.angle() == 0.0) {
			return new Point(rect.x() - rect.centerX(), rect.y() - rect.centerY() + rect.height());
		}
		double thetac = Math.toRadians(rect.angle()) - Math.atan2(rect.height() - rect.centerY(), rect.centerX());
		double h = hypot(rect.centerX(), rect.height() - rect.centerY());
		return new Point(rect.x() - h * Math.cos(thetac), rect.y() - h * Math.sin(thetac));		
	}
	
	public static Point topRight(IRectangle rect) {
		if (rect.angle() == 0.0) {
			return new Point(rect.x() - rect.centerX() + rect.width(), rect.y() - rect.centerY());
		}
		double thetac = Math.toRadians(rect.angle()) - Math.atan2(rect.centerY(), rect.width() - rect.centerX());
		double h = hypot(rect.width() - rect.centerX(), rect.centerY());
		return new Point(rect.x() + h * Math.cos(thetac), rect.y() + h * Math.sin(thetac));	 
	}
	
	public static Point topLeft(IRectangle rect) {
		if (rect.angle() == 0.0) {
			return new Point(rect.x() - rect.centerX(), rect.y() - rect.centerY());
		}
		double thetac = Math.toRadians(rect.angle()) + Math.atan2(rect.centerY(), rect.centerX());
		double h = hypot(rect.centerX(), rect.centerY());
		return new Point(rect.x() - h * Math.cos(thetac), rect.y() - h * Math.sin(thetac));
	}
	
	public static Point centerPoint(IRectangle rect) {
		Point tl = topLeft(rect);
		Point br = bottomRight(rect);
		return new Point((tl.x + br.x) / 2, (tl.y + br.y) / 2);
	}
	
	/**
	 * Does one rectangle intersect another? 
	 * 
	 * @param rect1
	 * @param rect2
	 * @return
	 */
	public static boolean intersects(IRectangle rect1, IRectangle rect2) {
		return (rect1.contains(rect2.topLeft()) ||
				rect1.contains(rect2.topRight())|| 
				rect1.contains(rect2.bottomLeft()) ||
				rect1.contains(rect2.bottomRight()) ||
				rect2.contains(rect1.topLeft()) ||
				rect2.contains(rect1.topRight()) ||
				rect2.contains(rect1.bottomLeft()) ||
				rect2.contains(rect1.bottomRight()) );
	}
	
	/**
	 * Given a rectangle with lines drawn from its center point, passing through
	 * the four corners and extending to infinity, in which of these four diagonal
	 * 'quadrants' does a given point lie? Useful for behavior simulation of colliding
	 * rectangles.
	 * 
	 * @param rect
	 * @param point
	 * @return
	 */
	public static quadrant quadrantOfPoint(IRectangle rect, Point point) {		
		
		// Top or Left
		if (Utils.findSide(bottomLeft(rect), topRight(rect), point) < 0) {
			if (Utils.findSide(topLeft(rect), bottomRight(rect), point) >= 0) {
				return quadrant.LEFT;
			} else {
				return quadrant.TOP;
			}
			
		// Bottom or right
		} else {
			if (Utils.findSide(topLeft(rect), bottomRight(rect), point) > 0) {
				return quadrant.BOTTOM;
			} else {
				return quadrant.RIGHT;
			}
		}
	}
	
	/*
	 * The hypot() function implementation below posted by anon. user Jeff on
	 * StackOverflow on 2010-09-22:
	 * 
	 * https://stackoverflow.com/questions/3764978/why-hypot-function-is-so-slow
	 */
	public static final double TWO_POW_450 = Double.longBitsToDouble(0x5C10000000000000L);
	public static final double TWO_POW_N450 = Double.longBitsToDouble(0x23D0000000000000L);
	public static final double TWO_POW_750 = Double.longBitsToDouble(0x6ED0000000000000L);
	public static final double TWO_POW_N750 = Double.longBitsToDouble(0x1110000000000000L);
	
	public static double hypot(double x, double y) {
	    x = Math.abs(x);
	    y = Math.abs(y);
	    if (y < x) {
	        double a = x;
	        x = y;
	        y = a;
	    } else if (!(y >= x)) { // Testing if we have some NaN.
	        if ((x == Double.POSITIVE_INFINITY) || (y == Double.POSITIVE_INFINITY)) {
	            return Double.POSITIVE_INFINITY;
	        } else {
	            return Double.NaN;
	        }
	    }
	    if (y-x == y) { // x too small to substract from y
	        return y;
	    } else {
	        double factor;
	        if (x > TWO_POW_450) { // 2^450 < x < y
	            x *= TWO_POW_N750;
	            y *= TWO_POW_N750;
	            factor = TWO_POW_750;
	        } else if (y < TWO_POW_N450) { // x < y < 2^-450
	            x *= TWO_POW_750;
	            y *= TWO_POW_750;
	            factor = TWO_POW_N750;
	        } else {
	            factor = 1.0;
	        }
	        return factor * Math.sqrt(x*x+y*y);
	    }
	}

}