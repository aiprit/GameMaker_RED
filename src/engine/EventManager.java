package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import structures.run.RunRoom;
import structures.run.RunView;

public class EventManager implements IDraw, IGUIHandler, IRoomChangedHandler, IGamePlayHandler {
	
	private IDraw myDraw;
	private IGUIHandler myGUI;
	private List<IRoomChangedHandler> myRoomChanged;
	private IGamePlayHandler myUserInput;
	
	public EventManager(){
		myRoomChanged = new ArrayList<IRoomChangedHandler>();
	}
	
	public void setDrawInterface(IDraw draw){
		myDraw = draw;
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
	
	public void drawImage(Image image, RunView view, double x, double y, double centerX, double centerY, double scaleX, double scaleY, double angle){
		myDraw.drawImage(image, view, x, y, centerX, centerY, scaleX, scaleY, angle);
	}
    
	private void processCollisionEvents() {
		
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
