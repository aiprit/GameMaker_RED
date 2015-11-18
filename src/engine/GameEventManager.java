package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import engine.collisions.CollisionManager;
import engine.events.EventManager;
import engine.events.IObjectModifiedHandler;
import javafx.scene.input.InputEvent;
import structures.data.events.CollisionEvent;
import structures.data.events.IDataEvent;
import structures.data.events.StepEvent;
import structures.run.RunObject;
import structures.run.RunRoom;
import utils.Pair;

/**
 * EventManager facilitates the running of the game loop
 * by calling the step event on each object in the room,
 * 
 * then calling gameplay (mouse click, key press) events
 * on the RunObjects that have a corresponding action for them.
 * 
 * It then calls the draw event on each object in the room.
 * 
 * @author baileyewall
 *
 */

public class GameEventManager implements IObjectModifiedHandler {

	private EventManager myEventManager;
	private IDraw myDrawListener;

	private Map<IDataEvent, ArrayList<RunObject>> myEvents;
	private EventFactory myEventFactory;
	
	private GroovyEngine myGroovyEngine;
	private RunRoom myRoom;
	
	private CollisionManager myCollisionManager;
	private Set<Pair<String>> myCollidingObjectPairs;

	public GameEventManager(RunRoom room, EventManager eventManager, IDraw drawListener, GroovyEngine groovyEngine){
		myEventManager = eventManager;
		myDrawListener = drawListener;
		myGroovyEngine = groovyEngine;
		myEventFactory = new EventFactory();
		myRoom = room;
		myCollisionManager = new CollisionManager();
		init(room);
	}

	/**
	 * Creates a map of GameplayEvents to a list of RunObjects
	 * that have a corresponding action for them.
	 * 
	 * @param room
	 */
	private void init(RunRoom room) {
		myEvents = new HashMap<IDataEvent, ArrayList<RunObject>>();
		myCollidingObjectPairs = new HashSet<>();
		
		// Add Events to Map
		for(RunObject o : room.getObjects()){
			for(IDataEvent e : o.getEvents()){
				
				// Add Event to Map
				if(!myEvents.containsKey(e)){
					myEvents.put(e, new ArrayList<RunObject>());
				}
				myEvents.get(e).add(o);
				
				// If Collision, add both objects' names to objects that collide
				if (e instanceof CollisionEvent) {
					myCollidingObjectPairs.add(new Pair<>(o.name, ((CollisionEvent)e).other.getName()));
				}
			}
		}
		
		// Which objects need to be checked for collisions?
		for (RunObject o : room.getObjects()) {
			potentiallyAddToCollideables(o);
		}
	}

	void loop() {
		step(myEvents.get(new StepEvent()));
		processGameplayEvents(myEventManager.getEvents());
		processCollisionEvents();
		draw();
	}

	/**
	 * Calls each object in the room's step event.
	 * 
	 * @param it
	 */
	private void step(List<RunObject> it) {
		if(it == null){
			return;
		}
		for(RunObject o : it){
			myGroovyEngine.runScript(o, o.getAction(new StepEvent()));
		}
	}

	/**
	 * Calls each GameplayEvent on the correct corresponding
	 * RunObject, and then clears the events.
	 * 
	 * @param events
	 */
	private void processGameplayEvents(Queue<InputEvent> events){
		for(InputEvent e : events){
			IDataEvent runEvent = myEventFactory.getEvent(e);
			if(myEvents.containsKey(runEvent)){
				List<RunObject> os = myEvents.get(runEvent);
				for(RunObject o : os){
					myGroovyEngine.runScript(o, o.getAction(runEvent));
				}
			}
		}
		events.clear();
	}
	
	private void processCollisionEvents() {
		for (Pair<String> pair : myCollidingObjectPairs) {
			List<Pair<RunObject>> collisions = myCollisionManager.detectCollisions(pair.one, pair.two);
			for (Pair<RunObject> collisionPair : collisions) {
				collisionPair.one.doAction(new CollisionEvent(collisionPair.two.name));
				collisionPair.two.doAction(new CollisionEvent(collisionPair.one.name));
			}
		}
	}
	
	private void potentiallyAddToCollideables(RunObject obj) {
		for (Pair<String> pair : myCollidingObjectPairs) {
			if (pair.contains(obj.name)) {
				myCollisionManager.addToCollideables(obj);
			}
		}
	}
	
	/**
	 * Draws each RunObject on the canvas.
	 */
	public void draw(){
		for(RunObject o : myRoom.getObjects()){
			o.draw(myDrawListener, myRoom.getView());
		}
	}

	/**
	 * When an object in the room is created, adds its
	 * GameplayEvents to the map.
	 */
	@Override
	public void onObjectCreate(RunObject runObject) {
		potentiallyAddToCollideables(runObject);
		
		for(IDataEvent e : runObject.getEvents()){
			if(!myEvents.containsKey(e)){
				myEvents.put(e, new ArrayList<RunObject>());
			}
			myEvents.get(e).add(runObject);
		}
	}

	/**
	 * When an object in the room is deleted, removes its
	 * GameplayEvents from the map.
	 */
	@Override
	public void onObjectDestroy(RunObject runObject) {
		myCollisionManager.removeFromCollideables(runObject);
		for(IDataEvent e : runObject.getEvents()){
			if(myEvents.containsKey(e)){
				myEvents.get(e).remove(runObject);
			}
		}
	}

}
