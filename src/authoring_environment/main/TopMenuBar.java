package authoring_environment.main;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class TopMenuBar {
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private MenuItem load, save, exit, saveAs;
	private BorderPane bp;
	private Menu run;
	private MenuBar menuBar;
	private MenuItem viewSize;

	public void init() {
		Menu file = new Menu(r.getString("File"));
		run = new Menu(r.getString("Run"));
		exit = new MenuItem(r.getString("Exit"));
//		Menu e = new Menu(r.getString("EditView"));
		load = new MenuItem(r.getString("Load"));
		save = new MenuItem(r.getString("Save"));
		saveAs = new MenuItem(r.getString("SaveAs"));
		Menu view = new Menu(r.getString("View"));
		viewSize = new MenuItem(r.getString("EditView"));
		view.getItems().add(viewSize);
		file.getItems().addAll(load,save,exit,saveAs);
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(file,run,view);
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
	public MenuItem getExitMenu(){
		return exit;
	}
	public MenuItem getViewMenu() {
		return viewSize;
	}
	public Menu getRunMenu(){
		return run;
	}
	public MenuItem getSaveAsMenu(){
		return saveAs;
	}
	
}