package engine.physics;

import java.util.ArrayList;
import java.util.List;

import structures.run.RunObject;
import utils.Point;
import utils.Vector;

public class ScrollerPhysicsEngine implements IPhysicsEngine {
	
	private double myResolution;
	
	public ScrollerPhysicsEngine(double resolution) {
		myResolution = resolution;
	}

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
		
		
		
	}
	
	public List<Point> interpolate(double x1, double y1, double x2, double y2, double resolution) {
		Vector point = (new Vector(x2 - x1, y2 - y1)).setLength(resolution);
		int times = (int)(Math.hypot(x2 - x1, y2 - y1) / resolution);
		List<Point> list = new ArrayList<>(times + 1);	
		double cursorX = x1;
		double cursorY = y1;
		for (int i = 0; i < times; i++) {
			list.add(new Point(cursorX, cursorY));
			cursorX += point.x;
			cursorY += point.y;
		}
		list.add(new Point(x2, y2));
		return list;
	}

}
