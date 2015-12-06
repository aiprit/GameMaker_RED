package utils.rectangle;

import utils.Point;
import utils.Utils;

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
	
	Rectangle getMutable();
	
	ImmutableRectangle getImmutable();
	
	static boolean contains(IRectangle rect, Point p) {
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
	
	public static Point bottomRight(IRectangle rect) {
		double thetac = Math.toRadians(rect.angle()) + Math.atan2(rect.height() - rect.centerY(), rect.width() - rect.centerX());
		double h = Math.hypot(rect.width() - rect.centerX(), rect.height() - rect.centerY());
		return new Point(rect.x() + h * Math.cos(thetac), rect.y() + h * Math.sin(thetac));			
	}
	
	public static Point bottomLeft(IRectangle rect) {
		double thetac = Math.toRadians(rect.angle()) - Math.atan2(rect.height() - rect.centerY(), rect.centerX());
		double h = Math.hypot(rect.centerX(), rect.height() - rect.centerY());
		return new Point(rect.x() - h * Math.cos(thetac), rect.y() - h * Math.sin(thetac));		
	}
	
	public static Point topRight(IRectangle rect) {
		double thetac = Math.toRadians(rect.angle()) - Math.atan2(rect.centerY(), rect.width() - rect.centerX());
		double h = Math.hypot(rect.width() - rect.centerX(), rect.centerY());
		return new Point(rect.x() + h * Math.cos(thetac), rect.y() + h * Math.sin(thetac));	 
	}
	
	public static Point topLeft(IRectangle rect) {
		double thetac = Math.toRadians(rect.angle()) + Math.atan2(rect.centerY(), rect.centerX());
		double h = Math.hypot(rect.centerX(), rect.centerY());
		return new Point(rect.x() - h * Math.cos(thetac), rect.y() - h * Math.sin(thetac));
	}
	
	public static Point centerPoint(IRectangle rect) {
		Point tl = topLeft(rect);
		Point br = bottomRight(rect);
		return new Point((tl.x + br.x) / 2, (tl.y + br.y) / 2);
	}
	
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

}