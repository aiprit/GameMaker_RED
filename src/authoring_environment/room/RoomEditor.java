package authoring_environment.room;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.function.Consumer;

import authoring_environment.room.preview.RoomPreview;
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

	private Stage myEditor;
	private Group myRoot;
	private RoomPreview myPreview;
	private VBox myTotalView;
	private HBox myObjectsAndPreview;
	
	public RoomEditor(ResourceBundle resources) {
		myRoot = new Group();
		myEditor = new Stage();
		myEditor.setWidth(Double.parseDouble(resources.getString(ROOM_EDITOR_WIDTH)));
		myEditor.setHeight(Double.parseDouble(resources.getString(ROOM_EDITOR_HEIGHT)));
		myEditor.setTitle(resources.getString(ROOM_EDITOR_TITLE));
		myTotalView = new VBox();
		myObjectsAndPreview = new HBox();
		myPreview = new RoomPreview(resources);
		myTotalView.getChildren().addAll(myObjectsAndPreview);
		myRoot.getChildren().add(myTotalView);
	}

	public void show() {
		Scene scene = new Scene(myRoot);
		myEditor.setScene(scene);
		myEditor.show();
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
