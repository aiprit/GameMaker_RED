package authoring_environment.object_editor;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class ObjectEditorModel {
	private DataGame game;
	private DataObject object;
	private ObservableList<IDataEvent> eventList;
	public ObjectEditorModel(DataGame g, DataObject o) {
		game = g;
		object = o;
		eventList = getEvents();
	}
	
	public void changeObjectName(String name) {
		object.setName(name);
	}
	
	public String getObjectName() {
		return object.getName();
	}
	
	public String getSpriteName() {
		return object.getSprite().getName();
	}
	
	public ObservableList<IDataEvent> getEvents() {
		ObservableMap<IDataEvent, ObservableList<IAction>> map = object.getEvents();
		ObservableList<IDataEvent> list = FXCollections.observableList(new ArrayList<IDataEvent>());
		for (IDataEvent e:map.keySet()) {
			list.add(e);
		}
		return list;
	}
	
	public ObservableList<String> getEventsAsStrings() {
		ObservableList<String> list = FXCollections.observableList(new ArrayList<String>());
		for (IDataEvent e : getEvents()) {
			list.add(e.toString());
		}
		return list;
	}
	
	public void addEvent(IDataEvent e) {
		eventList.add(e);
	}
	
	public void deleteEvent(String s) {
		eventList.remove(s);
	}

}
