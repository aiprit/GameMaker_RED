package authoring_environment.main;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class TopMenuBar {
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private MenuItem edit;
	private BorderPane bp;
	private MenuBar menuBar;

	public void init() {
		Menu load = new Menu(r.getString("Load"));
		Menu save = new Menu(r.getString("Save"));
		Menu e = new Menu(r.getString("EditView"));
		edit = new MenuItem(r.getString("EditView"));
		e.getItems().add(edit);
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(load, save, e);
	}

	public MenuBar getMenu() {
		return menuBar;
	}

	public MenuItem getEditMenu() {
		return edit;
	}
}