package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonToolbar {
	private static final String BUTTON_TOOLBAR_HEIGHT = "ButtonToolbarHeight";
	
	private ResourceBundle myResources;
	private static final String SIZE_TITLE = "SizeTitle";
	private static final String BACKGROUND = "Background";
	private static final String SHOW_VIEW = "ShowView";
	private static final String SAVE_TITLE = "SaveTitle";

	public ButtonToolbar(ResourceBundle resources) {
		myResources = resources;
	}
	
	public HBox createButtons() {
		HBox hbox = new HBox();
		ButtonHandler handler = new ButtonHandler();
		String[] buttonLabels = {myResources.getString(SIZE_TITLE), myResources.getString(BACKGROUND), myResources.getString(SHOW_VIEW), myResources.getString(SAVE_TITLE)};
		Button[] arr = handler.create(4, buttonLabels);
		hbox.getChildren().addAll(arr);
		hbox.setSpacing(50);
		//hbox.setPrefHeight(Double.parseDouble(myResources.getString(BUTTON_TOOLBAR_HEIGHT)));
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}
	
}
