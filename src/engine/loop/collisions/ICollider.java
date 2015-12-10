package engine.loop.collisions;

import structures.run.RunObject;

public interface ICollider {
	boolean collides(RunObject one, RunObject two);
	boolean collidesAt(RunObject one, double x, double y, RunObject two);
	boolean collidesAtReduced(RunObject one, double x, double y, RunObject two);
}
