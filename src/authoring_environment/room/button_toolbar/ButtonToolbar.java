package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;

public class ButtonToolbar extends ToolBar {
	private static final String BUTTON_TOOLBAR_HEIGHT = "ButtonToolbarHeight";
	
	private HBox myButtonPane;

	public ButtonToolbar(ResourceBundle resources) {
		super();
		this.setPrefHeight(Double.parseDouble(resources.getString(BUTTON_TOOLBAR_HEIGHT)));
		initializeButtonPane();
		this.getItems().add(myButtonPane);
	}
	
	public void addButton(RoomEditorButton newButton) {
		myButtonPane.getChildren().add(newButton);
	}
	
	private void initializeButtonPane() {
		myButtonPane = new HBox();
		myButtonPane.setAlignment(Pos.TOP_LEFT);
		myButtonPane.setSpacing(20);
	}
}
