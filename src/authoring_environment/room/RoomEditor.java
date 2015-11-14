package authoring_environment.room;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
		initializeObjectList();
		myPreview = new RoomPreview(myResources);
		objectsAndPreview.getChildren().addAll(myObjectsList, myPreview);
		totalPane.getChildren().add(objectsAndPreview);
	}
	
	private void initializeObjectList() {
		myObjectsList = new ObjectListContainer(myResources, myObjects);
		Consumer<MouseEvent> dragStarterFunction = e -> startObjectDrag(e);
		myObjectsList.setOnMouseClicked(dragStarterFunction);
	}
	
	private void startObjectDrag(MouseEvent event) {
		ImageView spriteInstance = myObjectsList.startObjectDragAndDrop(event);
		if (spriteInstance != null) {
			myRoot.getChildren().add(spriteInstance);
			dragSpriteIntoPreview(spriteInstance);
		}
	}
	
	private void dragSpriteIntoPreview(ImageView sprite) {
		sprite.setOnMousePressed(e -> setUpDraggingBehavior(sprite));
//		Event.fireEvent(sprite, new MouseEvent(sprite, null, MouseEvent.MOUSE_ENTERED, sprite.getX(), sprite.getY(),
//				sprite.getX(), sprite.getY(), MouseButton.PRIMARY, 1, false, false, false, false, true, false, false,
//				true, false, false, null));
//		Event.fireEvent(sprite, new MouseEvent(sprite, null, MouseEvent.MOUSE_DRAGGED, sprite.getX(), sprite.getY(),
//				sprite.getX(), sprite.getY(), MouseButton.PRIMARY, 1, false, false, false, false, true, false, false,
//				true, false, false, null));
	}
	
	private void setUpDraggingBehavior(ImageView sprite) {
		sprite.setOnMouseDragged(e -> updateSpritePosition(e, sprite));
	}
	
	/**
	 * DUPLICATED
	 */
	private void updateSpritePosition(MouseEvent event, ImageView sprite) {
		double x = event.getSceneX() - sprite.getImage().getWidth()/2;
		double y = event.getSceneY() - sprite.getImage().getHeight()/2;
		sprite.setX(x);
		sprite.setY(y);
	}
}
