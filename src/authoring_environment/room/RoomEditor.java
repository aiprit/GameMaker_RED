package authoring_environment.room;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.function.Consumer;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import structures.data.DataObject;

public class RoomEditor {
	private static final String ROOM_EDITOR_TITLE = "RoomEditorTitle";
	private static final String ROOM_EDITOR_WIDTH = "RoomEditorWidth";
	private static final String ROOM_EDITOR_HEIGHT = "RoomEditorHeight";
	
	private ResourceBundle myResources;
	private RoomController myRoomController;
	private Map<String, DataObject> myObjects;
	
	private Stage myEditor;
	private Group myRoot;
	private ObjectListContainer myObjectsList;
	private RoomPreview myPreview;
	private ButtonToolbar myToolbar;
	
	
	/**
	 * for TESTING purposes
	 */
	public RoomEditor(ResourceBundle resources, Map<String, DataObject> objects) {
		myResources = resources;
		myRoot = new Group();
		myObjects = objects;
		createEditor();
	}
	
	/**
	 * Map passed in as unmodifiable collection
	 */
	public RoomEditor(ResourceBundle resources, RoomController controller, Map<String, DataObject> objects) {
		myResources = resources;
		myRoomController = controller;
		myObjects = objects;
		myRoot = new Group();
		createEditor();
	}
	
	public void createEditor() {
		myEditor = new Stage();
		initializeEditor();
		fillEditorWithComponents();
		Scene scene = new Scene(myRoot);
		myEditor.setScene(scene);
		myEditor.show();
	}

	private void initializeEditor() {
		myEditor.setWidth(Double.parseDouble(myResources.getString(ROOM_EDITOR_WIDTH)));
		myEditor.setHeight(Double.parseDouble(myResources.getString(ROOM_EDITOR_HEIGHT)));
		myEditor.setTitle(myResources.getString(ROOM_EDITOR_TITLE));
		myEditor.setTitle(myResources.getString(ROOM_EDITOR_TITLE) + " - " + myRoomController.getName());
	}
	
	private void fillEditorWithComponents() {
		VBox totalPane = new VBox();
		initializeObjectListAndPreview(totalPane);
		initializeButtonsToolbar(totalPane);
		myRoot.getChildren().add(totalPane);
	}
	
	private void initializeObjectListAndPreview(VBox totalPane) {
		HBox objectsAndPreview = new HBox();
		initializeObjectList();
		myPreview = new RoomPreview(myResources, myRoomController);
		objectsAndPreview.getChildren().addAll(myObjectsList, myPreview);
		totalPane.getChildren().addAll(objectsAndPreview);
	}
	
	private void initializeObjectList() {
		myObjectsList = new ObjectListContainer(myResources, myObjects);
		Consumer<MouseEvent> dragStarterFunction = e -> startObjectDrag(e);
		myObjectsList.setOnMouseClicked(dragStarterFunction);
	}
	
	private void startObjectDrag(MouseEvent event) {
		PotentialObjectInstance objectInstance = myObjectsList.startObjectDragAndDrop(event);
		ImageView spriteInstance = objectInstance.getImageView();
		if (spriteInstance != null) {
			myRoot.getChildren().add(spriteInstance);
			dragSpriteIntoPreview(objectInstance);
		}
	}
	
	private void dragSpriteIntoPreview(PotentialObjectInstance objectInstance) {
		ImageView sprite = objectInstance.getImageView();
		sprite.setOnMousePressed(e -> setUpDraggingBehavior(objectInstance));
	}
	
	private void setUpDraggingBehavior(PotentialObjectInstance objectInstance) {
		ImageView sprite = objectInstance.getImageView();
		sprite.setOnMouseDragged(e -> addSpriteToRoom(e, objectInstance));
	}
	
	private void addSpriteToRoom(MouseEvent e, PotentialObjectInstance potentialObjectInstance) {
		potentialObjectInstance.updateSpritePosition(e);
		Point2D scenePoint = new Point2D(e.getSceneX(), e.getSceneY());
		if (potentialObjectInstance.inRoomBounds()) {
			Point2D canvasPoint = myPreview.translateSceneCoordinates(scenePoint);
			DoubleProperty x = new SimpleDoubleProperty();
			DoubleProperty y = new SimpleDoubleProperty();
			x.set(canvasPoint.getX());
			y.set(canvasPoint.getY());
			ObjectInstance objectInstance = new ObjectInstance(potentialObjectInstance.getObject(), x, y);
			myPreview.addImage(objectInstance, canvasPoint);
			myRoot.getChildren().remove(potentialObjectInstance.getImageView());
			myRoomController.addObject(objectInstance.getDataInstance());
		} 
	}
	
	private void initializeButtonsToolbar(VBox totalPane) {
		ButtonHandler handler = new ButtonHandler(myResources, myPreview);
		myToolbar = new ButtonToolbar(myResources, handler.getButtons());
		totalPane.getChildren().add(myToolbar);
	}
	
}
