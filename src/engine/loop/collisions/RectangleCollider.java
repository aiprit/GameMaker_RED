package engine.loop.collisions;

import structures.run.RunObject;
import utils.rectangle.Rectangle;

public class RectangleCollider implements ICollider {

	@Override
	public boolean collides(RunObject one, RunObject two) {
		if (one.name().equals("Mario") || two.name().equals("Mario")) {
			if (one.getBounds().intersects(two.getBounds())) {
				if (one.name().equals("Mario")) {
				System.out.println(String.format("Mario collides at (%.1f, %.1f)", one.x(), one.y()));
				} else {
				System.out.println(String.format("Mario collides at (%.1f, %.1f)", two.x(), two.y()));
				}
			}
		}
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
