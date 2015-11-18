package authoring_environment.EventGUI;

import authoring_environment.ObjectGUI.topPane.ObjectSpriteMenu;
import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class EventTopPane {
	MenuBar menu = new MenuBar();

	public Group init(){
		Group root = new Group();
		EventActionMenu action = new EventActionMenu();
		Menu actionMenu = action.makeMenu(null);
		menu.getMenus().addAll(actionMenu);
		root.getChildren().addAll(menu);
		return root;
	}

}
