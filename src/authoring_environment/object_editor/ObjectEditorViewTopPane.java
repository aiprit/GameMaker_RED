package authoring_environment.object_editor;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ObjectEditorViewTopPane {
	MenuBar menu = new MenuBar();
	Menu spriteMenu;
	
	public Group init() {
		Group root = new Group();
		spriteMenu = new Menu("sprites");
		menu.getMenus().add(spriteMenu);
		root.getChildren().add(menu);
		return root;
	}
	
	public Menu getMenu() {
		return spriteMenu;
	}
	
}
