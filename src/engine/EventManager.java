package engine;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import structures.run.RunRoom;
import structures.run.RunView;

public class EventManager implements IDraw, IGUIHandler, IRoomChangedHandler {
	
	private IDraw myDraw;
	private IGUIHandler myGUI;
	private List<IRoomChangedHandler> myRoomChanged;
	
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
	
	public void drawImage(Image image, RunView view, double x, double y, double centerX, double centerY, double scaleX, double scaleY, double angle){
		myDraw.drawImage(image, view, x, y, centerX, centerY, scaleX, scaleY, angle);
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

}
