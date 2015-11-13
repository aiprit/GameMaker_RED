package authoring_environment.room;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import structures.IObject;

public class ObjectListView extends ListView {
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private static final String OBJECTS_LIST_HEADER_HEIGHT = "ObjectsListHeaderHeight";
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	
	private Map<String, IObject> myObjects;
	private static String[] temp = {"testing1", "testing2", "testing3"};
	private static ObservableList<String> myObjectList = FXCollections.<String>observableArrayList(temp);

	@SuppressWarnings("unchecked")
	public ObjectListView(ResourceBundle resources, Map<String, IObject> objects) {
		//super(FXCollections.<String>observableArrayList(objects.keySet()));
		super(myObjectList);
		super.setPrefWidth(Double.parseDouble(resources.getString(OBJECTS_LIST_HEADER_WIDTH)));
		super.setPrefHeight(Double.parseDouble(resources.getString(PREVIEW_HEIGHT)) -
				Double.parseDouble(resources.getString(OBJECTS_LIST_HEADER_HEIGHT)));
		myObjects = objects;
		//myObjectList = FXCollections.<String>observableArrayList(myObjects.keySet());
	}
	
	
}
