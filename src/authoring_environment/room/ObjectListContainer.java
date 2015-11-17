package authoring_environment.room;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import structures.data.DataObject;

public class ObjectListContainer extends VBox {
	private ResourceBundle myResources;
	private Map<String, DataObject> myObjects;
	private ObjectListView myObjectsList;
	private ObjectListHeader myObjectListHeader;
	
	public ObjectListContainer(ResourceBundle resources, Map<String, DataObject> objects) {
		super();
		myResources = resources;
		myObjects = objects;
		initializeListHeader();
		initializeObjectListView();
		this.getChildren().addAll(myObjectListHeader, myObjectsList);
	}
	
	private void initializeListHeader() {
		myObjectListHeader = new ObjectListHeader(myResources);
	}
	
	private void initializeObjectListView() {
		myObjectsList = new ObjectListView(myResources, FXCollections.<String>observableArrayList(myObjects.keySet()));
	}
	
	public void setOnMouseClicked(Consumer<MouseEvent> f) {
		myObjectsList.setOnMousePressed(e -> f.accept(e));
	}
	
	public ObjectInstance startObjectDragAndDrop(MouseEvent event) {
		int selectedIdx = myObjectsList.getSelectionModel().getSelectedIndex();
		if (selectedIdx != -1) {
			String objectName = myObjectsList.getObjectsList().get(selectedIdx);
			ObjectInstance object = new ObjectInstance(myObjects.get(objectName));
			object.updateSpritePosition(event);
			myObjectsList.getSelectionModel().select(-1);
			return object;
		}
		return null;
	}
	
}
