package authoring_environment.room;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import structures.IObject;

public class ObjectListContainer extends VBox {
	
	private Map<String, IObject> myObjects;
	private ObjectListView myObjectsList;
	private ObjectListHeader myObjectListHeader;
	
	public ObjectListContainer(ResourceBundle resources, Map<String, IObject> objects) {
		super();
		myObjects = objects;
		initializeListHeader(resources);
		initializeObjectListView(resources);
		this.getChildren().addAll(myObjectListHeader, myObjectsList);
	}
	
	private void initializeListHeader(ResourceBundle resources) {
		myObjectListHeader = new ObjectListHeader(resources);
	}
	
	private void initializeObjectListView(ResourceBundle resources) {
		myObjectsList = new ObjectListView(resources, myObjects);
	}
	
}
