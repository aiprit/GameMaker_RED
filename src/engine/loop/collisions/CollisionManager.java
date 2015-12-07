package engine.loop.collisions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import structures.data.events.CollisionEvent;
import structures.data.interfaces.IDataEvent;
import structures.run.RunObject;
import utils.Pair;

/**
 * Keeps Lists of objects that can collide in a room,
 * and when requested, checks for collisions between
 * two types of objects using logic provided in 
 * IProvider.
 *
 */
public class CollisionManager {

	private Map<String, List<RunObject>> myCollideables;
	private ICollider myCollider;

	public CollisionManager() {
		myCollider = new RectangleCollider();
		myCollideables = new HashMap<>();
	}

	public void addToCollideables(RunObject obj) {
		if (!myCollideables.containsKey(obj.name())) {
			myCollideables.put(obj.name(), new ArrayList<RunObject>());
		}
		if(!myCollideables.get(obj.name()).contains(obj)){
			myCollideables.get(obj.name()).add(obj);
		}
	}

	public void removeFromCollideables(RunObject obj) {
		List<RunObject> list = myCollideables.get(obj.name());
		if (list != null) {
			list.remove(obj);
		}
	}

	public List<Pair<RunObject>> detectCollisions(String name1, String name2) {
		List<Pair<RunObject>> collisions = new ArrayList<>();
		
		List<RunObject> objects1 = myCollideables.get(name1);
		List<RunObject> objects2 = myCollideables.get(name2);
		
		if(objects1 == null || objects2 == null){
			return collisions;
		}

		for (RunObject obj1 : objects1) {
			for (RunObject obj2 : objects2) {
				if (myCollider.collides(obj1, obj2)) {
					collisions.add(new Pair<RunObject>(obj1, obj2));
				}
			}
		}

		return collisions;
	}
	
	public boolean collisionAt(RunObject obj, String otherObjectName, double x, double y) {
		List<RunObject> otherObjects = myCollideables.get(otherObjectName);
		
		for (RunObject other : otherObjects) {
			if (myCollider.collidesAt(obj, x, y, other)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean collisionSolidAt(RunObject obj, String otherObjectName, double x, double y) {
		List<RunObject> otherObjects = myCollideables.get(otherObjectName);
		for (RunObject other : otherObjects) {
			if (!other.isSolid()) {
				return false;
			}
			if (myCollider.collidesAtReduced(obj, x, y, other)) {
				return true;
			}
		}
		
		return false;	
	}
	
	public boolean collisionWithAt(double x, double y, RunObject obj1, RunObject obj2) {
		return myCollider.collidesAt(obj1, x, y, obj2);
	}
	
	public boolean collisionWithAtReduced(double x, double y, RunObject obj1, RunObject obj2) {
		return myCollider.collidesAtReduced(obj1, x, y, obj2);
	}
	
}
