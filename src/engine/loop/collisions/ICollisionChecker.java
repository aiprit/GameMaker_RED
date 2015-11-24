package engine.loop.collisions;

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
	
	public boolean collisionWithAt(double x, double y, RunObject obj1, RunObject obj2);
	
	public boolean collisionSolidAt(double x, double y, RunObject obj);
}
