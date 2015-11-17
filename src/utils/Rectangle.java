package utils;

public class Rectangle {

	private double x, y, centerX, centerY, width, height, angle;

	public Rectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.centerX = 0.0;
		this.centerY = 0.0;
		this.angle = 0.0;
	}

	public void center() {
		centerX = width / 2.0;
		centerY = height / 2.0;
	}
	
	public void center(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
	}

	public void setAngle(double angle) {
		this.angle = Math.toRadians(angle);
	}

	public Point topLeft() {
		double thetac = angle - Math.atan2(centerY, centerX);
		double h = Math.hypot(centerX, centerY);
		return new Point(this.x - h * Math.cos(thetac), this.y + h * Math.sin(thetac));
	}

	public Point topRight() {
		double thetac = angle + Math.atan2(centerY,width - centerX);
		double h = Math.hypot(width - centerX, centerY);
		return new Point(this.x + h * Math.cos(thetac), this.y + h * Math.sin(thetac));	
	}

	public Point bottomLeft() {
		double thetac = angle - Math.atan2(height - centerY, centerX);
		double h = Math.hypot(centerX, height - centerY);
		return new Point(this.x - h * Math.cos(thetac), this.y + h * Math.sin(thetac));		
	}

	public Point bottomRight() {
		double thetac = angle + Math.atan2(height - centerY, width - centerX);
		double h = Math.hypot(width - centerX, height - centerY);
		return new Point(this.x + h * Math.cos(thetac), this.y + h * Math.sin(thetac));			
	}

	public boolean contains(Point p) {
		double s = Math.sin(-1 * angle);
		double c = Math.cos(-1 * angle);
		
		double p2x = c * (p.x - x) - s * (p.y - y) + x;
		double p2y = s * (p.x - x) + c * (p.y - y) + y;
		
		double ax = x - centerX;
		double ay = y - centerY;
		double dx = x - centerX + width;
		double dy = y - centerY + height;
		
		if ((dy > ay && p2y > ay && p2y < dy) || (dy < ay && p2y < ay && p2y > dy )) {
			if ((dx > ax && p2x > ax && p2x < dx) || (dx < ax && p2x < ax && p2x > dx)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean intersects(Rectangle rect) {
		return	contains(rect.topLeft()) ||
				contains(rect.bottomRight()) ||
				contains(rect.topLeft()) ||
				contains(rect.bottomLeft());
	}
	
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void resize(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public double x() {
		return x;
	}

	public void x(double x) {
		this.x = x;
	}

	public double y() {
		return y;
	}

	public void y(double y) {
		this.y = y;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public double width() {
		return width;
	}

	public void width(double width) {
		this.width = width;
	}

	public double height() {
		return height;
	}

	public void height(double height) {
		this.height = height;
	}

	public double angle() {
		return angle;
	}
	
	@Override
	public Rectangle clone() {
		Rectangle rect = new Rectangle(x, y, width, height);
		rect.angle = angle;
		rect.centerX = centerX;
		rect.centerY = centerY;
		return rect;
	}
}
