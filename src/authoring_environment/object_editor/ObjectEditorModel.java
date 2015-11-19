package authoring_environment.object_editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Dialog;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class ObjectEditorModel {
	private DataGame game;
	private DataObject object;
	private EventPopupFactory fact = new EventPopupFactory();
	ObservableList<IDataEvent> eventList = FXCollections.observableList(new ArrayList<IDataEvent>());;
	private ResourceBundle l = ResourceBundle.getBundle("authoring_environment/object_editor/EventListResources");
	public ObjectEditorModel(DataGame g, DataObject o) {
		game = g;
		object = o;

	}

	public ObservableList<String> createLeftPaneList(){
		Enumeration <String> keys = l.getKeys();
		List<String> keylist = Collections.list(keys);
		ObservableList<String> list = FXCollections.observableList(new ArrayList<String>());
		Collections.sort(keylist);
		for (String str:keylist) {
			String value = l.getString(str);
			list.add(value);

		}
		return list;
	}

	public ObjectEditorModel(DataGame g) {
		game = g;
		object = new DataObject(String.valueOf(new Dialog().showAndWait().get()));;

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
		eventList.clear();
		ObservableMap<IDataEvent, ObservableList<IAction>> map = object.getEvents();
		for (IDataEvent e:map.keySet()) {
			eventList.add(e);
		}
		return eventList;
	}

	public ObservableList<String> getEventsAsStrings() {
		ObservableList<String> list = FXCollections.observableList(new ArrayList<String>());
		for (IDataEvent e : getEvents()) {
			list.add(e.toString());
		}
		return list;
	}

	public void deleteEvent(IDataEvent iDataEvent) {
		object.getEvents().remove(iDataEvent);
	}
	public DataObject getObject(){
		return object;
	}
	public ObservableList<DataObject> getObjectList(){
		return game.getObjects();
	}

	public EventPopupFactory getPopUpFactory() {
		return fact;
	}
	public ObservableMap<IDataEvent, ObservableList<IAction>> getMap(){
		return object.getEvents();
	}

}
