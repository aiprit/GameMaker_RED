package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ObjectListView extends ListView {
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private static final String OBJECTS_LIST_HEADER_HEIGHT = "ObjectsListHeaderHeight";
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	
	private ObservableList<String> myObjects;

	@SuppressWarnings("unchecked")
	public ObjectListView(ResourceBundle resources, ObservableList<String> objects) {
		super(objects);
		super.setPrefWidth(Double.parseDouble(resources.getString(OBJECTS_LIST_HEADER_WIDTH)));
		super.setPrefHeight(Double.parseDouble(resources.getString(PREVIEW_HEIGHT)) -
				Double.parseDouble(resources.getString(OBJECTS_LIST_HEADER_HEIGHT)));
		myObjects = objects;
	}
	
	public ObservableList<String> getObjectsList() {
		return myObjects;
	}
}
