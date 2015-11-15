package authoring_environment.room;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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

		initializeEditor();
		CreateView view = new CreateView(myResources);
		//ButtonToolbar toolbar = new ButtonToolbar(myResources);
		myRoot.getChildren().add(view.create());
		//myRoot.getChildren().add(toolbar.createButtons());
		//myRoot.getChildren().add(addScrollPane());
		//TODO populate the entire dialog
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
	}
	
	private ScrollPane addScrollPane() {
		ScrollPane scroll = new ScrollPane();
		Rectangle rect = new Rectangle();
		rect.setWidth(300);
		rect.setHeight(300);
//		rect.setX(500);
//		rect.setY(500);
		rect.setFill(Color.GREEN);
		scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scroll.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		scroll.setPrefSize(200, 200);
//		scroll.setTranslateX(500);
		//scroll.setTranslateY(500);
		scroll.setContent(rect);
		return scroll;
	}

		//myEditor.setTitle(myResources.getString(ROOM_EDITOR_TITLE) + " - " + myRoomController.getName());
	
	
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
		ObjectInstance objectInstance = myObjectsList.startObjectDragAndDrop(event);
		ImageView spriteInstance = objectInstance.getImageView();
		if (spriteInstance != null) {
			myRoot.getChildren().add(spriteInstance);
			dragSpriteIntoPreview(objectInstance);
		}
	}
	
	private void dragSpriteIntoPreview(ObjectInstance objectInstance) {
		ImageView sprite = objectInstance.getImageView();
		sprite.setOnMousePressed(e -> setUpDraggingBehavior(objectInstance));
	}
	
	private void setUpDraggingBehavior(ObjectInstance objectInstance) {
		ImageView sprite = objectInstance.getImageView();
		sprite.setOnMouseDragged(e -> objectInstance.updateSpritePosition(e));
		sprite.setOnMouseDragReleased(e -> addSpriteToRoom(objectInstance));
	}
	
	private void addSpriteToRoom(ObjectInstance objectInstance) {
		if (objectInstance.inRoomBounds()) {
			//TODO write object x,y to IObject
			myPreview.addNode(objectInstance.getImageView());
			myRoot.getChildren().remove(objectInstance.getImageView());
			myRoomController.addObject(objectInstance.getObject());
		}
	}
	
}
