package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import structures.data.events.IDataEvent;
import structures.run.RunGame;
import structures.run.RunObject;
import structures.run.RunRoom;

public class EventManager {
	
	private RunRoom myRoom;
	private IGamePlayHandler inputs;
	
	private Map<IDataEvent, List<RunObject>> myEvents;
	
	public EventManager(RunRoom room, IGamePlayHandler inputs){
		myRoom = room;
		this.inputs = inputs;
	}
	
	void loop() {
		//List<RunObject> it = myGame.getCurrentRoom().getObjects();
		List<RunObject> it = new ArrayList<RunObject>();
		step(it);
		processEvents(inputs.getEvents());
	}
	
	private void step(List<RunObject> it) {
		//call step Events
	}

	private void processEvents(Queue<InputEvent> events){
		for(InputEvent e : events){
			if(e instanceof MouseEvent){
				//get event out of map and execute it
				System.out.println("mouse event");
			}
			else if(e instanceof KeyEvent){
				System.out.println("key event");
				System.out.println(((KeyEvent) e).getText());
			}
		}
		events.clear();
	}

}
