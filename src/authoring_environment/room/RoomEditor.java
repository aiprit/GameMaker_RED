package authoring_environment.room;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
		Scene scene = new Scene(myRoot);
		//TODO populate the entire dialog
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
}
