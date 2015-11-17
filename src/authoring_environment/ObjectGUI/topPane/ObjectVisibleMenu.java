package authoring_environment.ObjectGUI.topPane;

import java.util.ResourceBundle;

import authoring_environment.ObjectGUI.ObjectController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class ObjectVisibleMenu {
	ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/topPane/TopPaneResources");

	public Menu makeMenu(ObjectController controller) {
		Menu image = new Menu(r.getString("visibleTitle"));
		MenuItem yes = new MenuItem("true");
		MenuItem no = new MenuItem("false");
		image.getItems().add(yes);
		image.getItems().add(no);
		yes.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
//				controller.changeVisibility(true);
				//todo with controller
			}
		});
		no.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//todo with controller
//				controller.changeVisibility(false);
			}
		});
		return image;
	}
}
