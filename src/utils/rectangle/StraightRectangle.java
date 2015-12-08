package utils.rectangle;

import utils.Point;

public class StraightRectangle implements IRectangle {
	
	private double x, y, centerX, centerY, width, height;
	
	public StraightRectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.centerX = 0.0;
		this.centerY = 0.0;
	}

	@Override
	public Point topLeft() {
		return new Point(x - centerX, y - centerY);
	}

	@Override
	public Point topRight() {
		return new Point(x - centerX + width, y - centerY);
	}

	@Override
	public Point bottomLeft() {
		return new Point(x - centerX, y - centerY + height);
	}

	@Override
	public Point bottomRight() {
		return new Point(x - centerX + width, y - centerY + height);
	}

	@Override
	public Point centerPoint() {
		return new Point(x - centerX + width / 2.0, y - centerY + height / 2.0);
	}

	@Override
	public boolean contains(Point p) {
		return (p.x >= x - centerX && p.x <= x - centerX + width && p.y >= y - centerY && p.y <= y - centerY + height);
	}

	@Override
	public boolean intersects(IRectangle rect) {
		return IRectangle.intersects(this, rect);
	}

	@Override
	public quadrant quadrantOfPoint(Point p) {
		return IRectangle.quadrantOfPoint(this, p);
	}

	@Override
	public double x() {
		return x;
	}

	@Override
	public double y() {
		return y;
	}

	@Override
	public double centerX() {
		return centerX;
	}

	@Override
	public double centerY() {
		return centerY;
	}

	@Override
	public double width() {
		return width;
	}

	@Override
	public double height() {
		return height;
	}

	@Override
	public double angle() {
		return 0.0;
	}

	@Override
	public IRectangle clone() {
		StraightRectangle rect = new StraightRectangle(x, y, width, height);
		rect.centerX = centerX;
		rect.centerY = centerY;
		return rect;
	}

	@Override
	public Rectangle getMutable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImmutableRectangle getImmutable() {
		// TODO Auto-generated method stub
		return null;
	}

}
