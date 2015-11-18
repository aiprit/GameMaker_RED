package engine.collisions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import structures.run.RunObject;
import utils.Pair;

public class CollisionManager {
	
	private Map<String, List<RunObject>> myCollideables;
	private ICollider myCollider;
	
	public CollisionManager() {
		myCollider = new RectangleCollider();
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
