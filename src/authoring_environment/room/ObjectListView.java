package authoring_environment.room;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ObjectListView extends ListView {
	private Map<String, Object> myObjects;
	private ObservableList<String> myObjectList;

	@SuppressWarnings("unchecked")
	public ObjectListView(Map<String, Object> objects) {
		super(FXCollections.<String>observableArrayList(objects.keySet()));
		myObjects = objects;
		myObjectList = FXCollections.<String>observableArrayList(myObjects.keySet());
	}
	
	
}
