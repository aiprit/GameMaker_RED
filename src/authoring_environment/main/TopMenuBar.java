package authoring_environment.main;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class TopMenuBar {
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private MenuItem load, save;
	private BorderPane bp;
	private Menu run;
	private MenuBar menuBar;

	public void init() {
		Menu file = new Menu(r.getString("File"));
		run = new Menu(r.getString("Run"));
//		Menu e = new Menu(r.getString("EditView"));
		load = new MenuItem(r.getString("Load"));
		save = new MenuItem(r.getString("Save"));
		file.getItems().addAll(load,save);
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(file,run);
	}

	public MenuBar getMenu() {
		return menuBar;
	}

	public MenuItem getLoadMenu() {
		return load;
	}
	public MenuItem getSaveMenu() {
		return save;
	}
	public Menu getRunMenu(){
		return run;
	}
}