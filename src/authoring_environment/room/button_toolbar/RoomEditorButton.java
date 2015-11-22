package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import javafx.scene.control.Button;


public class RoomEditorButton extends Button {
	private static final String ROOM_EDITOR_BUTTON_WIDTH = "RoomEditorButtonWidth";

	public RoomEditorButton(ResourceBundle resources, String title) {
		super(resources.getString(title));
		this.setPrefWidth(Double.parseDouble(resources.getString(ROOM_EDITOR_BUTTON_WIDTH)));
	}

}
