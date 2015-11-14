package authoring_environment.ObjectGUI;

import authoring_environment.EventPopup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ObjectLeftPane {

	private Group root;

	public Group init(Stage s) {
		root = new Group();

		EventPopup e = new EventPopup();
		Button b = new Button("Add New Event");
		root.getChildren().add(b);
		    b.setOnAction(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent event) {
		        e.popup(s);
		      }
		    });

		return root;

	}
}
