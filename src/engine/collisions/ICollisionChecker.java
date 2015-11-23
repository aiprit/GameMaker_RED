package engine.collisions;

import structures.run.RunObject;

public interface ICollisionChecker {
	
	/**
	 * If the object, moved to position (x, y), would throw
	 * a collision event.
	 * 
	 * @param x
	 * @param y
	 * @param obj
	 * @return
	 */
	public boolean collisionAt(double x, double y, RunObject obj);
}
