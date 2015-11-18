package engine.collisions;

import structures.run.RunObject;

public interface ICollider {
	public boolean collides(RunObject one, RunObject two);
}
