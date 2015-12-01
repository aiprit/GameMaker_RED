package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import authoring_environment.room.background_pop_up.BackgroundPopUpController;
import authoring_environment.room.preview.RoomCanvas;
import structures.data.IDataRoom;

public class SetBackgroundController {

	private SetBackgroundButton view;
	private IDataRoom model;
	
	public SetBackgroundController(ResourceBundle resources, RoomCanvas canvas, IDataRoom room) {
		view = new SetBackgroundButton(resources);
		model = room;
		view.setOnAction(e -> onClick(resources, canvas));
	}
	
	private void onClick(ResourceBundle resources, RoomCanvas background) {
		BackgroundPopUpController popup = new BackgroundPopUpController(resources, background, model);
	}
	
	public RoomEditorButton getSetBackgroundButton() {
		return view;
	}
}
