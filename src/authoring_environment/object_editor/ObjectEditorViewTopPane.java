package authoring_environment.object_editor;

import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	
	public MenuItem createMenuItem(String s, EventHandler<ActionEvent> e) {
		MenuItem m = new MenuItem(s);
		m.setOnAction(e);
		return m;
	}
	
	public List<MenuItem> getMenuItems() {
		return spriteMenu.getItems();
	}
	
	public void addToMenu(MenuItem m) {
		spriteMenu.getItems().add(m);
	}
	
}
