package authoring_environment.room;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import structures.data.DataObject;

//TODO change to MVC by factoring out VBox
public class ObjectListContainer extends VBox {
	private ResourceBundle myResources;
	private List<DataObject> myObjects;
	private ObjectListView myObjectsList;
	private ObjectListHeader myObjectListHeader;
	
	public ObjectListContainer(ResourceBundle resources, List<DataObject> objects) {
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
		List<String> objectNames = myObjects.stream()
											.map(e -> e.getName())
											.collect(Collectors.toList());
		myObjectsList = new ObjectListView(myResources, FXCollections.<String>observableArrayList(objectNames));
	}
	
	public void setOnMouseClicked(Consumer<MouseEvent> f) {
		myObjectsList.setOnMousePressed(e -> f.accept(e));
	}
	
	public PotentialObjectInstance startObjectDragAndDrop(MouseEvent event) {
		int selectedIdx = myObjectsList.getSelectionModel().getSelectedIndex();
		if (selectedIdx != -1) {
			PotentialObjectInstance object = new PotentialObjectInstance(myObjects.get(selectedIdx));
			object.updateSpritePosition(event);
			myObjectsList.getSelectionModel().select(-1);
			return object;
		}
		return null;
	}
	
}
