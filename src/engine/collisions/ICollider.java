package engine.collisions;

import structures.run.RunObject;

public interface ICollider {
	public boolean collides(RunObject one, RunObject two);
	public boolean collidesAt(RunObject one, double x, double y, RunObject two);
}
