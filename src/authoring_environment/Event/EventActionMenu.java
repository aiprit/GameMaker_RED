package authoring_environment.Event;

import java.util.ResourceBundle;

import authoring_environment.ActionPopup;
import authoring_environment.EventPopup;
import authoring_environment.EventGUI.EventRightPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import structures.data.DataGame;

public class EventActionMenu {
	ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/EventGUIResources");
	MenuItem addEvent;
	public Menu makeMenu(EventRightPane right) {
		Menu event = new Menu(r.getString("actionTitle"));
		addEvent = new MenuItem(r.getString("addActionTitle"));
		event.getItems().add(addEvent);
//		ActionPopup popup = new ActionPopup();
//
//		addEvent.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent t) {
//				popup.popup();
//			}
//		});
		return event;
	}
	public MenuItem getActionPopUpItem(){
		return addEvent;
	}
}
