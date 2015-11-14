package authoring_environment.ObjectGUI;

import authoring_environment.EventPopup;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class ObjectLeftPane {

	private Group root;

	public Group init() {
		root = new Group();

		EventPopup e = new EventPopup();
		Button b = new Button("Add New Event");
		root.getChildren().add(b);
		b.setOnAction(e.popup())

		return root;

	}
}
