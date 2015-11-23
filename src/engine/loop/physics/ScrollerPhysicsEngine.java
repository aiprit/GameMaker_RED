package engine.loop.physics;

import structures.run.RunObject;
import utils.Vector;

public class ScrollerPhysicsEngine implements IPhysicsEngine {

	@Override
	public void step(RunObject obj) {
		
		// Gravity
		obj.velocity = Vector.add(obj.velocity, obj.gravity);
		
		// Friction
		if (obj.velocity.length() >= obj.friction) {
			obj.velocity = obj.velocity.addLength(-1 * obj.friction);
		} else {
			obj.velocity = Vector.ZERO;
		}
		
		// Move
		obj.move_to(obj.velocity.x, obj.velocity.y, true);
	}

}
