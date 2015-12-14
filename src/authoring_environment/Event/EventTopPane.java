// This entire file is part of my masterpiece.
// Parit Burintrathikul
package authoring_environment.Event;


import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class EventTopPane {
	private MenuBar menu = new MenuBar();
	private EventActionMenu action;
	public Group init(){
		Group root = new Group();
		action = new EventActionMenu();
		Menu actionMenu = action.makeMenu(null);
		menu.getMenus().add(actionMenu);
		root.getChildren().add(menu);
		return root;
	}
	public MenuItem getMenuItem(){
		return action.getActionPopUpItem();
	}

}
