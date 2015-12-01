package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import authoring_environment.room.background_pop_up.BackgroundPopUpController;
import authoring_environment.room.preview.RoomCanvas;
import structures.data.DataRoom;


public class SetBackgroundButton extends RoomEditorButton {
	private static final String BACKGROUND = "Background";

	public SetBackgroundButton(ResourceBundle resources) {
		super(resources, BACKGROUND);
	}
	
}
