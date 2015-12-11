//This entire file is part of my masterpiece
//Nicholas Groszewski

package authoring_environment.object_editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import authoring_environment.Event.ClassesInPackage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.interfaces.IDataEvent;

public class LeftModel {

	private IObjectInterface game;
	private DataObject object;
	private EventPopupFactory fact = new EventPopupFactory();
	ObservableList<IDataEvent> eventList = FXCollections.observableList(new ArrayList<IDataEvent>());;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/object_editor/ObjectModelResources");
	
	public LeftModel(IObjectInterface g, DataObject o) {
		object = o;
		game = g;
	}
	
	public LeftModel(IObjectInterface g, String str) {
		game = g;
		object = new DataObject(str);
		game.addObject(object);
	}
	
	public ObservableList<String> createLeftPaneList() {
		ClassesInPackage classes = new ClassesInPackage();
		ObservableList<String> list = FXCollections.observableList(new ArrayList<String>());
		for (String s : classes.getAllClasses(r.getString("package"))) {
			list.add(s);
		}
		Collections.sort(list);
		return list;
	}
	
	public EventPopupFactory getPopUpFactory() {
		return fact;
	}
	
	public DataObject getObject() {
		return object;
	}
	
	public IObjectInterface getGame() {
		return game;
	}
}