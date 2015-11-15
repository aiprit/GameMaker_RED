package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.control.Button;

public class RoomEditorButton extends Button {
	
	public RoomEditorButton(ResourceBundle resources, String title) {
		super(resources.getString(title));
	}

}
