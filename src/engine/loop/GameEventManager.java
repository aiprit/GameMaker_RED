package engine.loop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.events.EventManager;
import engine.events.IObjectModifiedHandler;
import engine.front_end.IDraw;
import engine.loop.collisions.CollisionManager;
import engine.loop.collisions.ICollisionChecker;
import engine.loop.groovy.GroovyCollisionEvent;
import engine.loop.groovy.GroovyEngine;
import engine.loop.groovy.IGroovyEvent;
import engine.loop.physics.IPhysicsEngine;
import engine.loop.physics.ScrollerPhysicsEngine;
import javafx.scene.image.Image;
import structures.data.events.CollisionEvent;
import structures.data.events.IDataEvent;
import structures.data.events.LeaveRoomEvent;
import structures.data.events.ObjectCreateEvent;
import structures.data.events.ObjectDestroyEvent;
import structures.data.events.StepEvent;
import structures.run.RunAction;
import structures.run.RunObject;
import structures.run.RunRoom;
import utils.Pair;
import utils.Point;

/**
 * EventManager facilitates the running of the game loop
 * by calling the step event on each object in the room,
 * 
 * then calling input (mouse click, key press) events
 * on the RunObjects that have a corresponding action for them.
 * 
 * It then calls the draw event on each object in the room.
 * 
 * @author baileyewall
 *
 */

public class GameEventManager implements IObjectModifiedHandler, ICollisionChecker, IGameEventHandler {

	private EventManager myEventManager;
	private IDraw myDrawListener;

	private Map<IDataEvent, ArrayList<RunObject>> myEvents;

	private GroovyEngine myGroovyEngine;
	private RunRoom myRoom;
	private IPhysicsEngine myPhysicsEngine;
	private InputManager myInputManager;

	private CollisionManager myCollisionManager;
	private Set<Pair<String>> myCollidingObjectPairs;

	private List<RunObject> myCreatedQueue;
	private List<RunObject> myDeleteQueue;
	private List<String> myStringsToDraw;

	public GameEventManager(RunRoom room, EventManager eventManager, IDraw drawListener, GroovyEngine groovyEngine){
		myEventManager = eventManager;
		myDrawListener = drawListener;
		myGroovyEngine = groovyEngine;
		myRoom = room;
		myCollisionManager = new CollisionManager();
		myInputManager = new InputManager(eventManager, true);
		myCreatedQueue = new ArrayList<>();
		myDeleteQueue = new ArrayList<>();
		myPhysicsEngine = new ScrollerPhysicsEngine();
		myStringsToDraw = new ArrayList<>();
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
			
			// Give objects a way to test theoretical collisions
			o.setCollisionChecker(this);
			
			for(IDataEvent e : o.getEvents()){

				// Add Event to Map
				if(!myEvents.containsKey(e)){
					myEvents.put(e, new ArrayList<RunObject>());
				}
				myEvents.get(e).add(o);

				// If Collision, add both objects' names to objects that collide
				if (e instanceof CollisionEvent) {
					Pair<String> collideThese = new Pair<>(o.name, ((CollisionEvent) e).other.getName());
					myCollidingObjectPairs.add(collideThese);
				}
			}
		}

		// Which objects need to be checked for collisions?
		for (RunObject o : room.getObjects()) {
			potentiallyAddToCollideables(o);
		}

		for(RunObject o : room.getObjects()) {
			myCreatedQueue.add(o);
		}
	}

	void loop() {
		System.out.println("____");
		processStepEvents();
		myInputManager.processInputEvents(this);
		processLeaveRoomEvents();
		deleteObjects();
		processCreateEvents();
		stepPhysics();
		processCollisionEvents();
		draw();
	}
	
	@Override
	public List<RunObject> getRegistered(IDataEvent event) {
		List<RunObject> objects = myEvents.get(event);
		if (objects == null) {
			return Collections.emptyList();
		} else {
			return objects;
		}
	}
	
	@Override
	public void fire(RunObject object, IDataEvent event) {
		fire(object, event, null);
	}
	
	@Override
	public void fire(RunObject object, IDataEvent event, IGroovyEvent data) {
		RunAction action = object.getAction(event);
		if (action != null) {
			myGroovyEngine.runScript(object, object.getAction(event), data);
		}
	}

	/**
	 * Calls each object in the room's step event.
	 */
	private void processStepEvents() {
		for(RunObject obj : getRegistered(StepEvent.event)){
			fire(obj, StepEvent.event);
		}
	}
	
	private void stepPhysics() {
		for (RunObject obj : myRoom.getObjects()) {
			myPhysicsEngine.step(obj);
		}
	}



	private void processCollisionEvents() {
		for (Pair<String> pair : myCollidingObjectPairs) {
			List<Pair<RunObject>> collisions = myCollisionManager.detectCollisions(pair.one, pair.two);
			for (Pair<RunObject> collisionPair : collisions) {
				fire(collisionPair.one,
					new CollisionEvent(collisionPair.two.name),
					new GroovyCollisionEvent(collisionPair.two));
				fire(collisionPair.two,
					new CollisionEvent(collisionPair.one.name),
					new GroovyCollisionEvent(collisionPair.one));
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
		if(myRoom.getBackground() != null){
			try {
				Image backgroundImage = new Image(myRoom.getBackground());
				myDrawListener.drawBackgroundImage(backgroundImage, myRoom.getView(), myRoom.getWidth(), myRoom.getHeight());
			}
			catch(IllegalArgumentException e){
				myDrawListener.drawBackgroundColor(myRoom.getBackground(), myRoom.getView());
			}
		}
		for(RunObject o : myRoom.getObjects()){
			o.draw(myDrawListener, myRoom.getView());
			//myDrawListener.drawRectangle(o.getBounds(), myRoom.getView(), Color.BISQUE);
		}
		for(String s : myStringsToDraw){
			myDrawListener.drawText(s, myRoom.getView());
		}
		myStringsToDraw.clear();

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
		myCreatedQueue.add(runObject);
	}

	/**
	 * When an object in the room is deleted, removes its
	 * GameplayEvents from the map.
	 */
	@Override
	public void onObjectDestroy(RunObject runObject) {
		myDeleteQueue.add(runObject);
	}

	public void deleteObjects(){
		processDestroyedEvents();
		for(RunObject o : myDeleteQueue){
			myCollisionManager.removeFromCollideables(o);
			for(IDataEvent e : o.getEvents()){
				if(myEvents.containsKey(e)){
					myEvents.get(e).remove(o);
				}
			}
			myRoom.getObjects().remove(o);
		}
		myDeleteQueue.clear();
	}

	@Override
	public void setView(Point coordinates) {
		myRoom.getView().updateLocation(coordinates.x, coordinates.y);
	}

	public void processCreateEvents(){
		for(RunObject obj : myCreatedQueue){
			fire(obj, ObjectCreateEvent.event);
		}
		myCreatedQueue.clear();
	}

	public void processDestroyedEvents(){
		for (RunObject obj : myDeleteQueue) {
			fire(obj, ObjectDestroyEvent.event);
		}
	}

	@Override
	public void addStringToDraw(String draw) {
		myStringsToDraw.add(draw);
	}
	
	@Override
	public boolean collisionAt(double x, double y, RunObject obj) {
		for (Pair<String> pair : myCollidingObjectPairs) {
			if (pair.contains(obj.name)) {
				String otherName = pair.one.equals(obj.name) ? pair.two : pair.one;
				if (myCollisionManager.collisionAt(obj, otherName, x, y)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean collisionSolidAt(double x, double y, RunObject obj) {
		for (RunObject obj2 : myRoom.getObjects()) {
			if (obj2.solid && obj != obj2) {
				if (myCollisionManager.collisionWithAt(x, y, obj, obj2)) {
					return true;
				}
			}
		}
		return false;		
	}

	public void processLeaveRoomEvents(){
		for (RunObject o : getRegistered(LeaveRoomEvent.event)) {
			if(o.get_x_position() < 0 || o.get_x_position() > myRoom.getWidth() ||
					o.get_y_position() < 0 || o.get_y_position() > myRoom.getHeight()){
				fire(o, LeaveRoomEvent.event);
			}
		}
	}

	@Override
	public RunRoom getCurrentRoom() {
		return myRoom;
	}

	@Override
	public boolean collisionWithAt(double x, double y, RunObject obj1, RunObject obj2) {
		return myCollisionManager.collisionWithAt(x, y, obj1, obj2);
	}
	
	/**
	 * Call before the game loop is restarted to make sure nothing
	 * happens when the game is paused
	 */
	public void clearInputEvents(){
		myInputManager.clearQueue();
	}

}
