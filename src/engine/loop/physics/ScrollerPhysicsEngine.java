package engine.loop.physics;

import structures.run.RunObject;
import utils.Vector;

public class ScrollerPhysicsEngine implements IPhysicsEngine {
	
	private double precision = .5;

	@Override
	public void step(RunObject obj) {
		
		// Gravity
		obj.getVelocity( Vector.add(obj.setVelocity(), obj.getGravity()) );
		
		// Friction
		if (obj.setVelocity().length() >= obj.getFriction()) {
			obj.getVelocity( obj.setVelocity().addLength(-1 * obj.getFriction()) );
		} else {
			obj.getVelocity(Vector.ZERO);
		}
		
		// If we aren't moving, we are done
		if (Math.abs(obj.setVelocity().x) < .0001 && Math.abs(obj.setVelocity().y) < .0001) {
			return;
		}
		
		// If solid, check to make sure we can move before we do it
		if (obj.isSolid()) {
			double desiredX = obj.getX() + obj.setVelocity().x;
			double desiredY = obj.getY() + obj.setVelocity().y;
			if (Math.abs(obj.setVelocity().x) > Math.abs(obj.setVelocity().y)) {
				stepX(obj, obj.getX(), obj.getX() + obj.setVelocity().x);
				stepY(obj, obj.getY(), obj.getY() + obj.setVelocity().y);
			} else {
				stepY(obj, obj.getY(), obj.getY() + obj.setVelocity().y);
				stepX(obj, obj.getX(), obj.getX() + obj.setVelocity().x);
			}
			if (Math.abs(obj.getX() - desiredX) > precision) {
				obj.getVelocity( obj.setVelocity().setX(0.0) );
			}
			if (Math.abs(obj.getY() - desiredY) > precision) {
				obj.getVelocity( obj.setVelocity().setY(0.0) );
			}
			
		// Otherwise just move
		} else {
			obj.move_to(obj.setVelocity().x, obj.setVelocity().y, true);
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
