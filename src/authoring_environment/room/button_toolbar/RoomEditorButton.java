package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import javafx.scene.control.Button;


public class RoomEditorButton extends Button {
	private static final String BUTTON_PADDING = "   ";

	public RoomEditorButton(ResourceBundle resources, String title) {
		super(BUTTON_PADDING + resources.getString(title) + BUTTON_PADDING);
	}

}
