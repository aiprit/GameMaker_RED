package authoring_environment.controller;

import java.util.HashMap;
import java.util.LinkedList;

import java.util.Observable;
import java.util.Observer;

import java.util.ResourceBundle;

import authoring_environment.View;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataSound;
import structures.data.DataSprite;

public class Controller {
	private DataGame myGame;

	private View myView;
	
	

	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	
	public Controller(){
		myGame = new DataGame(r.getString("GameTitle") , r.getString("FileTitle"));

	}

	public void addObject(){
		
	}
	public void addRoom(){
		
	}
	public void removeObject(){
		
	}
	public void removeRoom(){
		
	}
	public ObservableList<DataObject> getObjects(){
		ObservableList<DataObject> objects = myGame.getObjects();
		return objects;
	}
	public void setRooms(HashMap<DataRoom, Integer> levels){
		
	}
	public void setObjects(){
		ObservableList<DataObject> objects = myGame.getObjects();
		
	}
	public ObservableList<DataRoom> getRooms(){
		return myGame.getRooms();
	}
	public ObservableList<DataSprite> getSprites() {
		return myGame.getSprites();
	}
	public ObservableList<DataSound> getSounds() {
		return myGame.getSounds();
	}
	public void addObserversToGame(){
		myGame.addObserver(myView);
	}
	
}
