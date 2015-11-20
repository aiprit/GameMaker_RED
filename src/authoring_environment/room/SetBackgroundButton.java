package authoring_environment.room;

import java.util.ResourceBundle;

import authoring_environment.room.background_pop_up.BackgroundPopUpController;
import authoring_environment.room.preview.RoomCanvas;
import structures.data.DataRoom;


public class SetBackgroundButton extends RoomEditorButton {
	private static final String BACKGROUND = "Background";

	public SetBackgroundButton(ResourceBundle resources, RoomCanvas background, DataRoom room) {
		super(resources, BACKGROUND);
		this.setOnAction(e -> onClick(resources, background, room));
	}
	
	private void onClick(ResourceBundle resources, RoomCanvas background, DataRoom room) {
		BackgroundPopUpController popup = new BackgroundPopUpController(resources, background, room);
	}
	
}
