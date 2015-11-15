package authoring_environment.ObjectGUI.topPane;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import structures.data.DataGame;

public class ObjectVisibleMenu {
	ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/topPane/TopPaneResources");

	public Menu makeMenu(DataGame game) {
		Menu image = new Menu(r.getString("visibleTitle"));
		MenuItem yes = new MenuItem("true");
		MenuItem no = new MenuItem("false");
		image.getItems().add(yes);
		image.getItems().add(no);
		yes.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//todo with controller
			}
		});
		no.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//todo with controller
			}
		});
		return image;
	}
}
