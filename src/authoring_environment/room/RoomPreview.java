package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;

public class RoomPreview extends ScrollPane {
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private static final String ROOM_EDITOR_WIDTH = "RoomEditorWidth";
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	
	private Group myRoot;
	
	public RoomPreview(ResourceBundle resources) {
		super();
		super.setMinHeight(Double.parseDouble(resources.getString(PREVIEW_HEIGHT)));
		super.setMinWidth(Double.parseDouble(resources.getString(ROOM_EDITOR_WIDTH)) - 
				Double.parseDouble(resources.getString(OBJECTS_LIST_HEADER_WIDTH)));
		myRoot = new Group();
	}

}
