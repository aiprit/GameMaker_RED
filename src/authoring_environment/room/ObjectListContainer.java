package authoring_environment.room;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import structures.IObject;

public class ObjectListContainer extends VBox {
	////FOR TESTING
	private static String[] temp = {"testing1", "testing2", "testing3"};
	private static ObservableList<String> myObjectList = FXCollections.<String>observableArrayList(temp);
	//////
	
	private ResourceBundle myResources;
	private Map<String, IObject> myObjects;
	private ObjectListView myObjectsList;
	private ObjectListHeader myObjectListHeader;
	private int myIDCounter;
	
	public ObjectListContainer(ResourceBundle resources, Map<String, IObject> objects) {
		super();
		myResources = resources;
		myIDCounter = 0;
		myObjects = objects;
		initializeListHeader();
		initializeObjectListView();
		this.getChildren().addAll(myObjectListHeader, myObjectsList);
	}
	
	private void initializeListHeader() {
		myObjectListHeader = new ObjectListHeader(myResources);
	}
	
	private void initializeObjectListView() {
		//myObjectsList = new ObjectListView(myResources, FXCollections.<String>observableArrayList(myObjects.keySet()));
		
		////FOR TESTING
		myObjectsList = new ObjectListView(myResources, myObjectList);
		//////
	}
	
	public void setOnMouseClicked(Consumer<MouseEvent> f) {
		myObjectsList.setOnMousePressed(e -> f.accept(e));
	}
	
	public ObjectInstance startObjectDragAndDrop(MouseEvent event) {
		int selectedIdx = myObjectsList.getSelectionModel().getSelectedIndex();
		if (selectedIdx != -1) {
			String objectName = myObjectsList.getObjectsList().get(selectedIdx);
			ObjectInstance object = new ObjectInstance(myResources, myObjects.get(objectName), myIDCounter++);
			//ImageView sprite = object.getImageView();
			
			///// FOR TESTING
			ImageView sprite = new ImageView(new Image("authoring_environment/room/smallstar.png"));
			//////
			
			object.updateSpritePosition(event);
			myObjectsList.getSelectionModel().select(-1);
			return object;
		}
		return null;
	}
	
}
