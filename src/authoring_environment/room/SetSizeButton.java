package authoring_environment.room;

import java.util.ResourceBundle;

public class SetSizeButton extends RoomEditorButton {
	private static final String SIZE_TITLE = "SizeTitle";
	
	private RoomBackground myBackground;
	
	public SetSizeButton(ResourceBundle resources, RoomBackground background) {
		super(resources, SIZE_TITLE);
		myBackground = background;
	}
}
