package structures.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;


public class DataObject{

	private ObservableMap<IDataEvent, ObservableList<IAction>> myEvents;

	private String myName;
	private DataSprite mySprite;

	private int myZIndex;

	public DataObject(String name) {
		myName = name;
		myEvents = FXCollections.observableMap(new HashMap<>());
		myZIndex = 0;
	}

	public String getName() {
		return myName;
	}

	public void setName(String name) {
		myName = name;
	}


	public void bindEvent(IDataEvent event, ObservableList<IAction> actions) {
		myEvents.put(event, actions);
	}

	public void removeEvent(IDataEvent e) {
		myEvents.remove(e);
	}

	public ObservableMap<IDataEvent, ObservableList<IAction>> getEvents(){

		return myEvents;
	}

	public void setSprite(DataSprite sprite) {
		mySprite = sprite;
	}

	public DataSprite getSprite() {
		return mySprite;
	}

	public int getZIndex() {
		return myZIndex;
	}
	public void setZIndex(int zIndex) {
		myZIndex = zIndex;
	}

	public void addSprite(DataSprite s) {
		// TODO Auto-generated method stub
	}
}

