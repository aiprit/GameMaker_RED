package utils;

public final class ImmutableRectangle implements IRectangle {
	
	private final double x, y, centerX, centerY, width, height, angle;
	
	public ImmutableRectangle(	double x,
								double y,
								double width,
								double height,
								double centerX,
								double centerY,
								double angle	) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.centerX = centerX;
		this.centerY = centerY;
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
		return angle;
	}

	@Override
	public ImmutableRectangle clone() {
		return this;
	}
	
	public Rectangle getMutable() {
		Rectangle rect = new Rectangle(x, y, width, height);
		rect.center(centerX, centerY);
		rect.setAngle(angle);
		return rect;
	}

}
