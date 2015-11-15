package authoring_environment.ObjectGUI;

import java.util.HashMap;
import java.util.Map;

import authoring_environment.Sprite;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import structures.IObject;
import structures.data.DataSprite;
import structures.data.events.IDataEvent;

public class ObjectController {
	private IObject myObject;
	Map<String, DataSprite> mySprites;
	
	public ObjectController(IObject object, Map map) {
		myObject = object;
		mySprites = map;
	}
	
	public Map<String, DataSprite> getSprites() {
		return mySprites;
	}
	
	public ObservableList<IDataEvent> getEvents() {
		return myObject.getEvents();
	}
	
	public String getName() {
		return myObject.getName();
	}
	
	public double[] getSize() {
		return myObject.getSize();
	}
	
	public void addSprite(DataSprite sprite) {
		myObject.addSprite(sprite);
	}
	
	public void addEvent(IDataEvent e) {
		myObject.addEvent(e);
	}
	
	public void deleteEvent(IDataEvent e) {
		myObject.deleteEvent(e);
	}
	
	public void changeVisibility(boolean b) {
		myObject.setVisibility(b);
	}
	
	public DataSprite getCurrentSprite() {
		return myObject.getSprite();
		
	}
	
}
