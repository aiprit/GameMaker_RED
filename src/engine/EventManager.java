package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import javafx.scene.input.InputEvent;
import structures.data.events.IDataEvent;
import structures.data.events.StepEvent;
import structures.run.RunObject;
import structures.run.RunRoom;

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

public class EventManager implements IObjectModifiedHandler, IRoomChangedHandler {

	private IGamePlayHandler gameplayInputs;
	private IDraw drawListener;

	private Map<IDataEvent, ArrayList<RunObject>> myEvents;
	private EventFactory myEventFactory;
	
	private RunRoom myRoom;

	public EventManager(RunRoom room, IGamePlayHandler inputs, IDraw drawListener){
		this.gameplayInputs = inputs;
		this.drawListener = drawListener;
		myEventFactory = new EventFactory();
		myRoom = room;
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
		for(RunObject o : room.getObjects()){
			for(IDataEvent e : o.getEvents()){
				if(!myEvents.containsKey(e)){
					myEvents.put(e, new ArrayList<RunObject>());
				}
				myEvents.get(e).add(o);
			}
		}
	}
	
	void loop() {
		step(myEvents.get(new StepEvent()));
		processGameplayEvents(gameplayInputs.getEvents());
		draw();
	}

	/**
	 * Calls each object in the room's step event.
	 * 
	 * @param it
	 */
	private void step(List<RunObject> it) {
		for(RunObject o : it){
			o.doAction(new StepEvent());
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
					System.out.println("i'm running!");
					o.doAction(runEvent);
				}
			}
		}
		events.clear();
	}
	
	private void processCollisionEvents() {
		
	}
	
	/**
	 * Draws each RunObject on the canvas.
	 */
	public void draw(){
		for(RunObject o : myRoom.getObjects()){
			o.draw(drawListener, myRoom.getView());
		}
	}

	@Override
	public void onRoomChanged (RunRoom runRoom) {
		init(runRoom);
	}

	/**
	 * When an object in the room is created, adds its
	 * GameplayEvents to the map.
	 */
	@Override
	public void onObjectCreate(RunObject runObject) {
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
		for(IDataEvent e : runObject.getEvents()){
			if(myEvents.containsKey(e)){
				myEvents.get(e).remove(runObject);
			}
		}
	}

}
