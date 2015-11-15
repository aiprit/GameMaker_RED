package authoring_environment.room;

import java.util.ResourceBundle;

public class ShowViewButton extends RoomEditorButton {
	private static final String SHOW_VIEW = "ShowView";
	private static final String HIDE_VIEW = "HideView";

	public ShowViewButton(ResourceBundle resources) {
		super(resources, SHOW_VIEW);
	}
}
