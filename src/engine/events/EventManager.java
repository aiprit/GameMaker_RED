package engine.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import engine.IDraw;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import structures.run.RunRoom;
import structures.run.RunView;

public class EventManager implements IGUIHandler, IRoomChangedHandler, IGamePlayHandler {
	
	private IGUIHandler myGUI;
	private List<IRoomChangedHandler> myRoomChanged;
	private IGamePlayHandler myUserInput;
	
	public EventManager(){
		myRoomChanged = new ArrayList<IRoomChangedHandler>();
	}
	
	public void setGUIInterface(IGUIHandler gui){
		myGUI = gui;
	}
	
	public void addRoomChangedInterface(IRoomChangedHandler roomChanged){
		myRoomChanged.add(roomChanged);
	}
	
	public void addUserInputInterface(IGamePlayHandler userInput){
		myUserInput = userInput;
	}
	
	public void onReset(){
		myGUI.onReset();
	}
	
	public void onStart(){
		myGUI.onStart();
	}
	
	public void onPause(){
		myGUI.onPause();
	}
	
	public void onLoadSave(String path){
		myGUI.onLoadSave(path);
	}
	
	public void onSave(){
		myGUI.onSave();
	}
	
	public void onRoomChanged(RunRoom runRoom){
		for(IRoomChangedHandler roomHandler : myRoomChanged){
			roomHandler.onRoomChanged(runRoom);
		}
	}

	@Override
	public void setOnEvent(InputEvent m) {
		myUserInput.setOnEvent(m);
	}

	@Override
	public Queue<InputEvent> getEvents() {
		return myUserInput.getEvents();
	}

}
