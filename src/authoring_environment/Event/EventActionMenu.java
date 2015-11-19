package authoring_environment.Event;

import java.util.ResourceBundle;

<<<<<<< HEAD

=======
//
//import authoring_environment.EventGUI.EventRightPane;
>>>>>>> e3f04b8aced0d2490528973bdfdfb2cce5fdc73d

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class EventActionMenu {
	ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/EventGUIResources");
	MenuItem addEvent;
	public Menu makeMenu(EventRightPane right) {
		Menu event = new Menu(r.getString("actionTitle"));
		addEvent = new MenuItem(r.getString("addActionTitle"));
		event.getItems().add(addEvent);
		return event;
	}
	public MenuItem getActionPopUpItem(){
		return addEvent;
	}
}
