package authoring_environment.room;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;

public class RoomPreview extends ScrollPane {
	private Group myRoot;
	
	public RoomPreview(Group root) {
		super(root);
		myRoot = root;
	}

}
