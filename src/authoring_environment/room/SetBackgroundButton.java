package authoring_environment.room;

import java.util.ResourceBundle;


public class SetBackgroundButton extends RoomEditorButton {
	public SetBackgroundButton(ResourceBundle resources, String title) {
		super(resources, title);
		// TODO Auto-generated constructor stub
	}

	private static final String BACKGROUND = "Background";

//	public SetBackgroundButton(ResourceBundle resources, RoomBackground background) {
//		super(resources, BACKGROUND);
//		this.setOnAction(e -> onClick(resources, background));
//	}
//	
//	private void onClick(ResourceBundle resources, RoomBackground background) {
//		BackgroundPopup popup = new BackgroundPopup(resources, background);
//	}
}
