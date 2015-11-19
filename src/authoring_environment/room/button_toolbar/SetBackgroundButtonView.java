package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import javafx.scene.control.Button;


public class SetBackgroundButtonView extends RoomEditorButtonView {
	private static final String BACKGROUND = "Background";

	public SetBackgroundButtonView(ResourceBundle resources) {
		super(resources, BACKGROUND);
	}
	
	public Button getBackgroundButton() {
		return this;
	}
}
