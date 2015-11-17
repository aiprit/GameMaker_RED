package authoring_environment.controller;

import java.util.HashMap;
import java.util.LinkedList;

import javafx.collections.ObservableList;
import structures.IObject;
import structures.IRoom;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataSound;
import structures.data.DataSprite;

public class Controller {
	private DataGame myGame;
	
	public Controller(){
		myGame = new DataGame("GAME" , "file");
	}

	public void addObject(){
		
	}
	public void addRoom(){
		
	}
	public void removeObject(){
		
	}
	public void removeRoom(){
		
	}
	public ObservableList<IObject> getObjects(){
		ObservableList<IObject> objects = myGame.getObjects();
		return objects;
	}
	public void setRooms(HashMap<DataRoom, Integer> levels){
		
	}
	public void setObjects(){
		ObservableList<IObject> objects = myGame.getObjects();
		
	}
	public ObservableList<IRoom> getRooms(){
		return myGame.getRooms();
	}
	public ObservableList<DataSprite> getSprites() {
		return myGame.getSprites();
	}
	public ObservableList<DataSound> getSounds() {
		return myGame.getSounds();
	}
	
}
