package engine.loop.physics;

import structures.run.RunObject;
import utils.Vector;

public class ScrollerPhysicsEngine implements IPhysicsEngine {
	
	private double precision = .5;

	@Override
	public void step(RunObject obj) {
		
		// Gravity
		obj.setVelocity( Vector.add(obj.getVelocity(), obj.getGravity()) );
		
		// Friction
		if (obj.getVelocity().length() >= obj.getFriction()) {
			obj.setVelocity( obj.getVelocity().addLength(-1 * obj.getFriction()) );
		} else {
			obj.setVelocity(Vector.ZERO);
		}
		
		// If we aren't moving, we are done
		if (Math.abs(obj.getVelocity().x) < .0001 && Math.abs(obj.getVelocity().y) < .0001) {
			return;
		}
		
		// If solid, check to make sure we can move before we do it
		if (obj.isSolid()) {
			double desiredX = obj.getX() + obj.getVelocity().x;
			double desiredY = obj.getY() + obj.getVelocity().y;
			if (Math.abs(obj.getVelocity().x) > Math.abs(obj.getVelocity().y)) {
				stepX(obj, obj.getX(), obj.getX() + obj.getVelocity().x);
				stepY(obj, obj.getY(), obj.getY() + obj.getVelocity().y);
			} else {
				stepY(obj, obj.getY(), obj.getY() + obj.getVelocity().y);
				stepX(obj, obj.getX(), obj.getX() + obj.getVelocity().x);
			}
			if (Math.abs(obj.getX() - desiredX) > precision) {
				obj.setVelocity( obj.getVelocity().setX(0.0) );
			}
			if (Math.abs(obj.getY() - desiredY) > precision) {
				obj.setVelocity( obj.getVelocity().setY(0.0) );
			}
			
		// Otherwise just move
		} else {
			obj.move_to(obj.getVelocity().x, obj.getVelocity().y, true);
		}
		
	}
	
	private void stepX(RunObject obj, double currentX, double desiredX) {
		if (desiredX > currentX) {
			for (double i = currentX; i <= desiredX; i += precision) {
				if (obj.collision_solid_at(i, obj.getY())) {
					break;
				}
				obj.setX( i );
			}
		} else {
			for (double i = currentX; i >= desiredX; i -= precision) {
				if (obj.collision_solid_at(i, obj.getY())) {
					break;
				}
				obj.setX( i );
			}			
		}
	}

	private void stepY(RunObject obj, double currentY, double desiredY) {
		if (desiredY > currentY) {
			for (double i = currentY; i <= desiredY; i += precision) {
				if (obj.collision_solid_at(obj.getX(), i)) {
					break;
				}
				obj.setY( i );
			}
		} else {
			for (double i = currentY; i >= desiredY; i -= precision) {
				if (obj.collision_solid_at(obj.getX(), i)) {
					break;
				}
				obj.setY( i );
			}			
		}
	}

}
