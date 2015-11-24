package engine.loop.physics;

import structures.run.RunObject;
import utils.Vector;

public class ScrollerPhysicsEngine implements IPhysicsEngine {
	
	private double precision = .5;

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
		
		// If we aren't moving, we are done
		if (Math.abs(obj.velocity.x) < .0001 && Math.abs(obj.velocity.y) < .0001) {
			return;
		}
		
		// If solid, check to make sure we can move before we do it
		if (obj.solid) {
			double desiredX = obj.x + obj.velocity.x;
			double desiredY = obj.y + obj.velocity.y;
			if (Math.abs(obj.velocity.x) > Math.abs(obj.velocity.y)) {
				stepX(obj, obj.x, obj.x + obj.velocity.x);
				stepY(obj, obj.y, obj.y + obj.velocity.y);
			} else {
				stepY(obj, obj.y, obj.y + obj.velocity.y);
				stepX(obj, obj.x, obj.x + obj.velocity.x);
			}
			if (Math.abs(obj.x - desiredX) > precision) {
				obj.velocity = obj.velocity.setX(0.0);
			}
			if (Math.abs(obj.y - desiredY) > precision) {
				obj.velocity = obj.velocity.setY(0.0);
			}
			
		// Otherwise just move
		} else {
			obj.move_to(obj.velocity.x, obj.velocity.y, true);
		}
		
	}
	
	private void stepX(RunObject obj, double currentX, double desiredX) {
		if (desiredX > currentX) {
			for (double i = currentX; i <= desiredX; i += precision) {
				if (obj.collision_solid_at(i, obj.y)) {
					break;
				}
				obj.x = i;
			}
		} else {
			for (double i = currentX; i >= desiredX; i -= precision) {
				if (obj.collision_solid_at(i, obj.y)) {
					break;
				}
				obj.x = i;
			}			
		}
	}

	private void stepY(RunObject obj, double currentY, double desiredY) {
		if (desiredY > currentY) {
			for (double i = currentY; i <= desiredY; i += precision) {
				if (obj.collision_solid_at(obj.x, i)) {
					break;
				}
				obj.y = i;
			}
		} else {
			for (double i = currentY; i >= desiredY; i -= precision) {
				if (obj.collision_solid_at(obj.x, i)) {
					break;
				}
				obj.y = i;
			}			
		}
	}

}
