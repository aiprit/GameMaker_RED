package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import javafx.scene.input.InputEvent;
import structures.data.events.IDataEvent;
import structures.run.RunObject;
import structures.run.RunRoom;

public class EventManager implements IObjectModifiedHandler, IRoomChangedHandler {

	private IGamePlayHandler inputs;

	private Map<IDataEvent, ArrayList<RunObject>> myEvents;
	private EventFactory myEventFactory;
	
	private IDraw drawListener;
	private RunRoom myRoom;

	public EventManager(RunRoom room, IGamePlayHandler inputs, IDraw drawListener){
		this.inputs = inputs;
		this.drawListener = drawListener;
		myRoom = room;
		init(room);
	}

	private void init(RunRoom room) {
		myEventFactory = new EventFactory();

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
		System.out.println("a loop");
		List<RunObject> it = new ArrayList<RunObject>();
		step(it);
		processEvents(inputs.getEvents());
		draw();
	}

	private void step(List<RunObject> it) {
		//call step Events
	}

	private void processEvents(Queue<InputEvent> events){
		for(InputEvent e : events){
			IDataEvent runEvent = myEventFactory.getEvent(e);
			System.out.println("Event: " + runEvent.getName());
		}
		events.clear();
	}
	
	public void draw(){
		for(RunObject o : myRoom.getObjects()){
			o.draw(drawListener, myRoom.getView());
		}
	}

	@Override
	public void onRoomChanged (RunRoom runRoom) {
		init(runRoom);
	}

	@Override
	public void onObjectCreate(RunObject runObject) {
		for(IDataEvent e : runObject.getEvents()){
			if(!myEvents.containsKey(e)){
				myEvents.put(e, new ArrayList<RunObject>());
			}
			myEvents.get(e).add(runObject);
		}
	}

	@Override
	public void onObjectDestroy(RunObject runObject) {
		for(IDataEvent e : runObject.getEvents()){
			if(myEvents.containsKey(e)){
				myEvents.get(e).remove(runObject);
			}
		}
	}

}
