package engine.collisions;

import structures.run.RunObject;
import utils.rectangle.Rectangle;

public class RectangleCollider implements ICollider {

	@Override
	public boolean collides(RunObject one, RunObject two) {
		return one.getBounds().intersects(two.getBounds());
	}

	@Override
	public boolean collidesAt(RunObject one, double x, double y, RunObject two) {
		Rectangle rect = one.getBounds().getMutable();
		rect.x(x);
		rect.y(y);
		return rect.intersects(two.getBounds());
	}

}
