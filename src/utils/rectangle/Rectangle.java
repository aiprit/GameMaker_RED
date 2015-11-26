package utils.rectangle;

import utils.Point;

public class Rectangle implements IRectangle {

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
		this.angle = angle;
	}

	@Override
	public Point topLeft() {
		return IRectangle.topLeft(this);
	}

	@Override
	public Point topRight() {
		return IRectangle.topRight(this);
	}

	@Override
	public Point bottomLeft() {
		return IRectangle.bottomLeft(this);	
	}

	@Override
	public Point bottomRight() {
		return IRectangle.bottomRight(this);
	}

	@Override
	public boolean contains(Point p) {
		return IRectangle.contains(this, p);
	}	
	
	
	@Override
	public boolean intersects(IRectangle rect) {
		return IRectangle.intersects(this, rect);
	}
	
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void resize(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public double x() {
		return x;
	}

	public void x(double x) {
		this.x = x;
	}

	@Override
	public double y() {
		return y;
	}

	public void y(double y) {
		this.y = y;
	}

	@Override
	public double centerX() {
		return centerX;
	}

	public void centerX(double centerX) {
		this.centerX = centerX;
	}

	@Override
	public double centerY() {
		return centerY;
	}

	public void centerY(double centerY) {
		this.centerY = centerY;
	}

	@Override
	public double width() {
		return width;
	}

	public void width(double width) {
		this.width = width;
	}

	@Override
	public double height() {
		return height;
	}

	public void height(double height) {
		this.height = height;
	}

	@Override
	public double angle() {
		return angle;
	}
	
	public void angle(double angle) {
		this.angle = angle;
	}
	
	@Override
	public Rectangle clone() {
		Rectangle rect = new Rectangle(x, y, width, height);
		rect.angle = angle;
		rect.centerX = centerX;
		rect.centerY = centerY;
		return rect;
	}
	
	@Override
	public ImmutableRectangle getImmutable() {
		return new ImmutableRectangle(x, y, width, height, centerX, centerY, angle);
	}

	@Override
	public Rectangle getMutable() {
		return clone();
	}

	@Override
	public quadrant quadrantOfPoint(Point p) {
		return IRectangle.quadrantOfPoint(this, p);
	}
	
	@Override
	public Point centerPoint() {
		return IRectangle.centerPoint(this);
	}
}
