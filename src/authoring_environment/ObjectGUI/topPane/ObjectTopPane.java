package authoring_environment.ObjectGUI.topPane;

import authoring_environment.ObjectGUI.ObjectController;
import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class ObjectTopPane {
	MenuBar menu = new MenuBar();
	Group root;
	private ObjectController myController;
	
	public ObjectTopPane(ObjectController controller) {
		myController = controller;
	}

	public Group init() {
		root = new Group();		
		
		ObjectSpriteMenu sprite = new ObjectSpriteMenu();
		Menu spriteMenu = sprite.makeMenu(myController.getSprites());
		
		ObjectVisibleMenu visible = new ObjectVisibleMenu();
		Menu visibleMenu = visible.makeMenu(myController);
		
		ObjectEventMenu event = new ObjectEventMenu();
		Menu eventMenu = event.makeMenu();
		
		menu.getMenus().addAll(spriteMenu, visibleMenu, eventMenu);
		
		root.getChildren().add(menu);
		return root;

	}
}
