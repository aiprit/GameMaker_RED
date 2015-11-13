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
	private static final String ROOM_EDITOR_TITLE = "RoomEditorTitle";
	private static final String ROOM_EDITOR_WIDTH = "RoomEditorWidth";
	private static final String ROOM_EDITOR_HEIGHT = "RoomEditorHeight";
	
	private ResourceBundle myResources;
	private RoomController myRoomController;
	private Map<String, IObject> myObjects;
	
	private Stage myEditor;
	private Group myRoot;
	private Group myPreviewRoot;
	private ObjectListView myObjectListView;
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
		//TODO populate the entire dialog
		myEditor.setScene(scene);
		myEditor.show();
	}

	private void initializeEditor() {
		myEditor.setWidth(Double.parseDouble(myResources.getString(ROOM_EDITOR_WIDTH)));
		myEditor.setHeight(Double.parseDouble(myResources.getString(ROOM_EDITOR_HEIGHT)));
		myEditor.setTitle(myResources.getString(ROOM_EDITOR_TITLE));
	}
	
	private void fillEditorWithComponents() {
		VBox totalPane = new VBox();
		HBox objectsAndPreview = new HBox();
		myObjectListView = new ObjectListView(myObjects);
		initializePreview();
		objectsAndPreview.getChildren().addAll(myObjectListView, myPreview);
	}
	
	private void initializePreview() {
		myPreviewRoot = new Group();
		myPreview = new RoomPreview(myPreviewRoot);
	}
	
}
