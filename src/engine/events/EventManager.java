package engine.events;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.scene.input.InputEvent;
import structures.run.RunRoom;

public class EventManager implements IGUIHandler, IRoomChangedHandler, IGamePlayHandler {

	private List<IGUIHandler> myGUI;
	private List<IRoomChangedHandler> myRoomChanged;
	private List<IGamePlayHandler> myUserInput;

	public EventManager(){
		myGUI = new ArrayList<IGUIHandler>();
		myRoomChanged = new ArrayList<IRoomChangedHandler>();
		myUserInput = new ArrayList<IGamePlayHandler>();
	}

	public void addGUIInterface(IGUIHandler gui){
		myGUI.add(gui);
	}

	public void addRoomChangedInterface(IRoomChangedHandler roomChanged){
		myRoomChanged.add(roomChanged);
	}

	public void addUserInputInterface(IGamePlayHandler userInput){
		myUserInput.add(userInput);
	}

	public void onReset(){
		for(IGUIHandler g : myGUI){
			g.onReset();
		}
	}

	public void onStart(){
		for(IGUIHandler g : myGUI){
			g.onStart();
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

	public void onRoomChanged(RunRoom runRoom){
		for(IRoomChangedHandler r : myRoomChanged){
			r.onRoomChanged(runRoom);
		}
	}

	@Override
	public void setOnEvent(InputEvent m) {
		for(IGamePlayHandler i : myUserInput){
			i.setOnEvent(m);
		}
	}

	@Override
	public Queue<InputEvent> getEvents() {
		Queue<InputEvent> currentEvents = new LinkedList<>();
		for(IGamePlayHandler i : myUserInput){
			currentEvents.addAll(i.getEvents());
		}
		return currentEvents;
	}

}
