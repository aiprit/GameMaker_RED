package authoring_environment.room;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import structures.IObject;

public class RoomEditor {
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	private static final String ROOM_EDITOR_TITLE = "RoomEditorTitle";
	private static final String ROOM_EDITOR_WIDTH = "RoomEditorWidth";
	private static final String ROOM_EDITOR_HEIGHT = "RoomEditorHeight";
	
	private ResourceBundle myResources;
	private RoomController myRoomController;
	private Map<String, IObject> myObjects;
	
	private Stage myEditor;
	private Group myRoot;
	private ObjectListContainer myObjectsList;
	private RoomPreview myPreview;
	
	
	/**
	 * for TESTING purposes
	 */
	public RoomEditor(ResourceBundle resources) {
		myResources = resources;
		myRoot = new Group();
		createEditor();
	}
	
	/**
	 * Map passed in as unmodifiable collection
	 */
	public RoomEditor(ResourceBundle resources, RoomController controller, Map<String, IObject> objects) {
		myResources = resources;
		myRoomController = controller;
		myObjects = objects;
		myRoot = new Group();
		createEditor();
	}
	
	public void createEditor() {
		myEditor = new Stage();
		Scene scene = new Scene(myRoot);
		initializeEditor();
		fillEditorWithComponents();
		//TODO populate the entire dialog
		myEditor.setScene(scene);
		myEditor.show();
	}

	private void initializeEditor() {
		myEditor.setWidth(Double.parseDouble(myResources.getString(ROOM_EDITOR_WIDTH)));
		myEditor.setHeight(Double.parseDouble(myResources.getString(ROOM_EDITOR_HEIGHT)));
		//myEditor.setTitle(myResources.getString(ROOM_EDITOR_TITLE) + " - " + myRoomController.getName());
	}
	
	private void fillEditorWithComponents() {
		VBox totalPane = new VBox();
		initializeObjectListAndPreview(totalPane);
		myRoot.getChildren().add(totalPane);
	}
	
	private void initializeObjectListAndPreview(VBox totalPane) {
		HBox objectsAndPreview = new HBox();
		myObjectsList = new ObjectListContainer(myResources, myObjects);
		myPreview = new RoomPreview(myResources);
		objectsAndPreview.getChildren().addAll(myObjectsList, myPreview);
		totalPane.getChildren().add(objectsAndPreview);
	}
	
}
