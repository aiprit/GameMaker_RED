package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class DragPane extends StackPane {
	private static final String ROOM_EDITOR_HEIGHT = "RoomEditorHeight";
	private static final String ROOM_EDITOR_WIDTH = "RoomEditorWidth";

	public DragPane(ResourceBundle resources, Node sprite) {
		super(sprite);
		super.setPrefWidth(Double.parseDouble(resources.getString(ROOM_EDITOR_WIDTH)));
		super.setPrefHeight(Double.parseDouble(resources.getString(ROOM_EDITOR_HEIGHT)));
	}

}
