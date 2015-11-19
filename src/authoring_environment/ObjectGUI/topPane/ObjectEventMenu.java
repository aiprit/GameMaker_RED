package authoring_environment.ObjectGUI.topPane;

import java.util.ResourceBundle;

//import authoring_environment.main.EventPopup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import structures.data.DataGame;

public class ObjectEventMenu {
	ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/topPane/TopPaneResources");

	public Menu makeMenu() {
		Menu event = new Menu(r.getString("eventTitle"));
//		MenuItem addEvent = new MenuItem(r.getString("addEventTitle"));
//		event.getItems().add(addEvent);
//		EventPopup popup = new EventPopup();
//
//		addEvent.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent t) {
//				popup.popup();
//			}
//		});
		return event;
	}
}
