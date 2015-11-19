package authoring_environment.room;

import java.util.ResourceBundle;

import authoring_environment.room.preview.canvas.RoomBackground;

public class SetBackgroundButton extends RoomEditorButton {
	private static final String BACKGROUND = "Background";

	public SetBackgroundButton(ResourceBundle resources, RoomBackground background) {
		super(resources, BACKGROUND);
		this.setOnAction(e -> onClick(resources, background));
	}
	
	private void onClick(ResourceBundle resources, RoomBackground background) {
		BackgroundPopup popup = new BackgroundPopup(resources, background);
	}
}
