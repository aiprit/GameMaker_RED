package authoring_environment.room.button_toolbar;

import structures.data.DataGame;

public class ButtonController {
	public ButtonController(DataGame dataGame) {
		ButtonModel model = new ButtonModel(dataGame);
		SetBackgroundButtonView backgroundButton = new SetBackgroundButtonView();
		backgroundButton.getBackgroundButton().setOnAction(e -> onClick()); 
	}
	
	private void onClick() {
		
	}
}
