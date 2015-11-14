package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class RoomPreview extends ScrollPane {
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	private static final String PREVIEW_WIDTH = "PreviewWidth";
	
	private Group myRoot;
	
	public RoomPreview(ResourceBundle resources) {
		super();
		super.setMinHeight(Double.parseDouble(resources.getString(PREVIEW_HEIGHT)));
		super.setMinWidth(Double.parseDouble(resources.getString(PREVIEW_WIDTH)));
		myRoot = new Group();
		super.setContent(myRoot);
	}
	
	public void addNode(Node element) {
		myRoot.getChildren().add(element);
	}
	
	public void removeNode(Node element) {
		myRoot.getChildren().remove(element);
	}

}
