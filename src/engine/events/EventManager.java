package engine.events;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.scene.input.InputEvent;
import structures.run.RunObject;
import structures.run.RunRoom;
import utils.Point;

/**
 * A centralized event system for an instance of an Engine. 
 * Any class can register themselves as a listener for a 
 * type of event defined by one of many Interfaces. In turn,
 * we are a listener to these events, and when we receive one,
 * we can push the event to all registered listeners.
 *
 */
public class EventManager implements IGUIHandler,
IGamePlayHandler, IObjectModifiedHandler, IFrontEndUpdateHandler {

	private List<IGUIHandler> myGUI;
	private List<IGamePlayHandler> myUserInput;
	private List<IObjectModifiedHandler> myObjectModified;
	private List<IFrontEndUpdateHandler> myFrontEndUpdater;

	public EventManager(){
		myGUI = new ArrayList<>();
		myUserInput = new ArrayList<>();
		myObjectModified = new ArrayList<>();
		myFrontEndUpdater = new ArrayList<>();
	}

	public void addGUIInterface(IGUIHandler gui){
		myGUI.add(gui);
	}

	public void addUserInputInterface(IGamePlayHandler userInput){
		myUserInput.add(userInput);
	}

	public void addObjectModifiedInterface(IObjectModifiedHandler objectModified){
		myObjectModified.add(objectModified);
	}
	
	public void addFrontEndUpdateInterface(IFrontEndUpdateHandler frontEnd){
		myFrontEndUpdater.add(frontEnd);
	}

	public void clearObjectModifiedInterface(){
		myObjectModified.clear();
	}

	public void onReset(){
		for(IGUIHandler g : myGUI){
			g.onReset();
		}
	}

	public void onResume(){
		for(IGUIHandler g : myGUI){
			g.onResume();
		}
	}
 
	public void onPause(){
		for(IGUIHandler g : myGUI){
			g.onPause();
		}
	}

	public void onLoadSave(String path){
		for(IGUIHandler g : myGUI){
			g.onLoadSave(path);
		}
	}

	public void onSave(){
		for(IGUIHandler g : myGUI){
			g.onSave();
		}
	}

	@Override
	public void setOnEvent(InputEvent m) {
		for(IGamePlayHandler i : myUserInput){
			i.setOnEvent(m);
		}
	}

	//get and clear events
	@Override
	public Queue<InputEvent> getEvents() {
		Queue<InputEvent> currentEvents = new LinkedList<>();
		for(IGamePlayHandler i : myUserInput){
			currentEvents.addAll(i.getEvents());
			i.getEvents().clear();
		}
		return currentEvents;
	}

	@Override
	public void onObjectCreate(RunObject runObject) {
		for(IObjectModifiedHandler m : myObjectModified){
			m.onObjectCreate(runObject);
		}
	}

	@Override
	public void onObjectDestroy(RunObject runObject) {
		for(IObjectModifiedHandler m : myObjectModified){
			m.onObjectDestroy(runObject);
		}
	}

	@Override
	public void setView(Point coordinates) {
		for(IObjectModifiedHandler m : myObjectModified){
			m.setView(coordinates);
		}
	}

	@Override
	public void addStringToDraw(String draw) {
		for(IObjectModifiedHandler m : myObjectModified){
			m.addStringToDraw(draw);
		}
	}

	@Override
	public void onRoomChanged(RunRoom runRoom){
		for(IFrontEndUpdateHandler f : myFrontEndUpdater){
			f.onRoomChanged(runRoom);
		}
	}
	
	@Override
	public void setHighScore(double highScore) {
		for(IFrontEndUpdateHandler f : myFrontEndUpdater){
			f.setHighScore(highScore);
		}
	}

}
