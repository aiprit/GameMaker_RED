package engine.collisions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		if (!myCollideables.containsKey(obj.name)) {
			myCollideables.put(obj.name, new ArrayList<RunObject>());
		}
		myCollideables.get(obj.name).add(obj);
	}
	
	public void removeFromCollideables(RunObject obj) {
		List<RunObject> list = myCollideables.get(obj.name);
		if (list != null) {
			list.remove(obj);
		}
	}
	
	public List<Pair<RunObject>> detectCollisions(String name1, String name2) {
		List<Pair<RunObject>> collisions = new ArrayList<>();
		
		List<RunObject> objects1 = myCollideables.get(name1);
		List<RunObject> objects2 = myCollideables.get(name2);
		
		for (RunObject obj1 : objects1) {
			for (RunObject obj2 : objects2) {
				if (myCollider.collides(obj1, obj2)) {
					collisions.add(new Pair<RunObject>(obj1, obj2));
				}
			}
		}
		
		return collisions;
	}
}
