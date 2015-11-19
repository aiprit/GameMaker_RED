package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import structures.data.DataGame;
import structures.data.DataRoom;

public class ButtonController {
	
	public ButtonController(DataRoom dataRoom, ResourceBundle resources) {
		ButtonModel model = new ButtonModel(dataRoom);
		SetBackgroundButtonView backgroundButton = new SetBackgroundButtonView(resources);
		backgroundButton.getBackgroundButton().setOnAction(e -> onClick()); 
	}
	
	private void onClick() {
		
	}
}
