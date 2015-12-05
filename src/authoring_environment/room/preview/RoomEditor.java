package authoring_environment.room.preview;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.function.Consumer;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RoomEditor {
	private static final String ROOM_EDITOR_TITLE = "RoomEditorTitle";
	private static final String ROOM_EDITOR_WIDTH = "RoomEditorWidth";
	private static final String ROOM_EDITOR_HEIGHT = "RoomEditorHeight";
	public static final String DEFAULT_RESOURCE_PACKAGE = "css/";
	public static final String STYLESHEET = "blue.css";

	private Stage myEditor;
	private Group myRoot;
	private RoomPreview myPreview;
	private VBox myTotalView;
	private HBox myObjectsAndPreview;
	
	public RoomEditor(ResourceBundle resources, double width, double height, String roomTitle, String gameName) {
		myRoot = new Group();
		myEditor = new Stage();
		myEditor.setWidth(Double.parseDouble(resources.getString(ROOM_EDITOR_WIDTH)));
		myEditor.setHeight(Double.parseDouble(resources.getString(ROOM_EDITOR_HEIGHT)));
		myEditor.setTitle(resources.getString(ROOM_EDITOR_TITLE) + " - " + roomTitle);
		myTotalView = new VBox();
		myObjectsAndPreview = new HBox();
		myPreview = new RoomPreview(resources, width, height, gameName);
		myTotalView.getChildren().addAll(myObjectsAndPreview);
		myRoot.getChildren().add(myTotalView);
	}

	public void show() {
		Scene scene = new Scene(myRoot);
		scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
		myEditor.setResizable(false);
		myEditor.setScene(scene);
		myEditor.show();
	}
	
	public void setOnClose(Consumer<Void> updateFcn) {
		myEditor.setOnCloseRequest(e -> updateFcn.accept(null));
	}

	public Stage getEditor() {
		return myEditor;
	}

	public void setEditor(Stage myEditor) {
		this.myEditor = myEditor;
	}

	public Group getRoot() {
		return myRoot;
	}

	public void setRoot(Group myRoot) {
		this.myRoot = myRoot;
	}

	public RoomPreview getPreview() {
		return myPreview;
	}

	public void setPreview(RoomPreview myPreview) {
		this.myPreview = myPreview;
	}

	public VBox getTotalView() {
		return myTotalView;
	}

	public void setTotalView(VBox myTotalView) {
		this.myTotalView = myTotalView;
	}

	public HBox getObjectsAndPreview() {
		return myObjectsAndPreview;
	}

	public void setObjectsAndPreview(HBox myObjectsAndPreview) {
		this.myObjectsAndPreview = myObjectsAndPreview;
	}
	
}
