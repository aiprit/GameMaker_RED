package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import authoring_environment.room.preview.RoomCanvas;


public class SetSizeButton extends RoomEditorButton {
	private static final String SIZE_TITLE = "SizeTitle";
	
	public SetSizeButton(ResourceBundle resources) {
		super(resources, SIZE_TITLE);
	}
}
