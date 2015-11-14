package authoring_environment.ObjectGUI.topPane;

import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class ObjectTopPane {
	MenuBar menu = new MenuBar();
	Group root;

	public Group init() {
		root = new Group();

		ObjectSpriteMenu sprite = new ObjectSpriteMenu();
		Menu spriteMenu = sprite.makeMenu(null);
		
		ObjectVisibleMenu visible = new ObjectVisibleMenu();
		Menu visibleMenu = visible.makeMenu(null);
		
		ObjectEventMenu event = new ObjectEventMenu();
		Menu eventMenu = event.makeMenu(null);
		
		menu.getMenus().addAll(spriteMenu, visibleMenu, eventMenu);
		
		root.getChildren().add(menu);
		return root;

	}
}
