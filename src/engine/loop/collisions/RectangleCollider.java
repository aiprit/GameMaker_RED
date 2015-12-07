package engine.loop.collisions;

import structures.run.RunObject;
import utils.rectangle.Rectangle;

public class RectangleCollider implements ICollider {

	@Override
	public boolean collides(RunObject one, RunObject two) {
		Rectangle twoB = two.getBounds().getMutable();
		twoB.width(twoB.width() + 6);
		twoB.height(twoB.height() + 15);
		twoB.center();
		Rectangle oneB = one.getBounds().getMutable();
		oneB.width(oneB.width() + 6);
		oneB.height(oneB.height() + 15);
		oneB.center();
		return oneB.intersects(twoB);
	}

	@Override
	public boolean collidesAt(RunObject one, double x, double y, RunObject two) {
		Rectangle rect = one.getBounds().getMutable();
		rect.x(x);
		rect.y(y);
		return rect.intersects(two.getBounds());
	}
	
	public boolean collidesAtReduced(RunObject one, double x, double y, RunObject two) {
		Rectangle rect = one.getBounds().getMutable();
		rect.x(x);
		rect.y(y);
		//rect.width(rect.width() - 2);
		//rect.height(rect.height() - 2);
		return rect.intersects(two.getBounds());
	}

}
