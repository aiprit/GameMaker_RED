package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.ToolBar;

public class ButtonToolbar extends ToolBar {
	private static final String BUTTON_TOOLBAR_HEIGHT = "ButtonToolbarHeight";

	public ButtonToolbar(ResourceBundle resources, Node... buttons) {
		super(buttons);
		this.setPrefHeight(Double.parseDouble(resources.getString(BUTTON_TOOLBAR_HEIGHT)));
	}
}
