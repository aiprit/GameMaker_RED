package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import engine.collisions.CollisionManager;
import engine.collisions.ICollisionChecker;
import engine.events.EventManager;
import engine.events.IObjectModifiedHandler;
import engine.physics.IPhysicsEngine;
import engine.physics.ScrollerPhysicsEngine;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import structures.data.events.CollisionEvent;
import structures.data.events.IDataEvent;
import structures.data.events.ObjectCreateEvent;
import structures.data.events.ObjectDestroyEvent;
import structures.data.events.ObjectMousePressedEvent;
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
 * then calling gameplay (mouse click, key press) events
 * on the RunObjects that have a corresponding action for them.
 * 
 * It then calls the draw event on each object in the room.
 * 
 * @author baileyewall
 *
 */

public class GameEventManager implements IObjectModifiedHandler, ICollisionChecker {

	private EventManager myEventManager;
	private IDraw myDrawListener;

	private Map<IDataEvent, ArrayList<RunObject>> myEvents;
	private EventFactory myEventFactory;

	private GroovyEngine myGroovyEngine;
	private RunRoom myRoom;
	private IPhysicsEngine myPhysicsEngine;

	private CollisionManager myCollisionManager;
	private Set<Pair<String>> myCollidingObjectPairs;

	private List<RunObject> myCreatedQueue;
	private List<RunObject> myDeleteQueue;

	public GameEventManager(RunRoom room, EventManager eventManager, IDraw drawListener, GroovyEngine groovyEngine){
		myEventManager = eventManager;
		myDrawListener = drawListener;
		myGroovyEngine = groovyEngine;
		myEventFactory = new EventFactory();
		myRoom = room;
		myCollisionManager = new CollisionManager();
		myCreatedQueue = new ArrayList<>();
		myDeleteQueue = new ArrayList<>();
		myPhysicsEngine = new ScrollerPhysicsEngine();
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
		stepPhysics();
		step(myEvents.get(new StepEvent()));
		processCollisionEvents();
		processGameplayEvents(myEventManager.getEvents());
		deleteObjects();
		draw();
		processCreateEvents();
	}

	/**
	 * Calls each object in the room's step event.
	 * 
	 * @param it
	 */
	private void step(List<RunObject> it) {
		if (it == null){
			return;
		}
		StepEvent step = new StepEvent();
		for (RunObject o : it) {	
			myGroovyEngine.runScript(o, o.getAction(step), new GroovyEvent(step));
		}
	}
	
	private void stepPhysics() {
		for (RunObject obj : myRoom.getObjects()) {
			myPhysicsEngine.step(obj);
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
			List<IDataEvent> runEvents = myEventFactory.getEvent(e);
			for(IDataEvent runEvent : runEvents){
				GroovyEvent event = new GroovyEvent(runEvent);
				if(event.hasXY()){
					event.setCoordinates(myEventFactory.getCoordinates(e));
				}
				if(myEvents.containsKey(runEvent)){
					List<RunObject> os = myEvents.get(runEvent);
					for(RunObject o : os){
						if(event.getLocalCheck()){
							if(o.getBounds().contains(event.getCoordinates())){
								myGroovyEngine.runScript(o, o.getAction(runEvent), event);
							}
						}
						else{
							myGroovyEngine.runScript(o, o.getAction(runEvent), event);
						}
					}
				}
			}
		}
		events.clear();
	}

	private void processCollisionEvents() {
		for (Pair<String> pair : myCollidingObjectPairs) {
			List<Pair<RunObject>> collisions = myCollisionManager.detectCollisions(pair.one, pair.two);
			for (Pair<RunObject> collisionPair : collisions) {
				CollisionEvent oneCollision = new CollisionEvent(collisionPair.two.name);
				CollisionEvent twoCollision = new CollisionEvent(collisionPair.one.name);
				myGroovyEngine.runScript(collisionPair.one, collisionPair.one.getAction(oneCollision), new GroovyEvent(oneCollision));
				myGroovyEngine.runScript(collisionPair.two, collisionPair.two.getAction(twoCollision), new GroovyEvent(twoCollision));
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

	private void processGroovyEvent(IDataEvent runEvent, GroovyEvent event){
	}

	@Override
	public void setView(Point coordinates) {
		myRoom.getView().updateLocation(coordinates.x, coordinates.y);
	}

	public void processCreateEvents(){
		for(RunObject o : myCreatedQueue){
			ObjectCreateEvent step = new ObjectCreateEvent();
			myGroovyEngine.runScript(o, o.getAction(step), new GroovyEvent(step));
		}
		myCreatedQueue.clear();
	}

	public void processDestroyedEvents(){
		for(RunObject o : myDeleteQueue){
			ObjectDestroyEvent step = new ObjectDestroyEvent();
			myGroovyEngine.runScript(o, o.getAction(step), new GroovyEvent(step));
		}
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

}
