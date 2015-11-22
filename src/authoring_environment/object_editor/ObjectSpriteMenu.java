package authoring_environment.object_editor;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class ObjectSpriteMenu {
	ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/EventGUIResources");
	MenuItem addEvent;

	public Menu makeMenu() {
		Menu event = new Menu(r.getString("actionTitle"));
		addEvent = new MenuItem(r.getString("addSpriteTitle"));
		event.getItems().add(addEvent);
		return event;
	}

	public MenuItem getActionPopUpItem() {
		return addEvent;
	}
}
