package authoring_environment.room.object_list;

import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ObjectListView extends ListView {
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private static final String OBJECTS_LIST_HEADER_HEIGHT = "ObjectsListHeaderHeight";
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	
	private ObservableList<String> myObjects;

	@SuppressWarnings("unchecked")
	public ObjectListView(ResourceBundle resources) {
		super();
		super.setPrefWidth(Double.parseDouble(resources.getString(OBJECTS_LIST_HEADER_WIDTH)));
		super.setPrefHeight(Double.parseDouble(resources.getString(PREVIEW_HEIGHT)) -
				Double.parseDouble(resources.getString(OBJECTS_LIST_HEADER_HEIGHT)));
	}
	
	public ObservableList<String> getObjectsList() {
		return myObjects;
	}
	
	public void setObjectsList(ObservableList<String> objects) {
		myObjects = objects;
		this.setItems(myObjects);
	}
}
