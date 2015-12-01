package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import authoring_environment.room.preview.RoomCanvas;
import authoring_environment.room.size_popup.SizePopupController;
import structures.data.IDataRoom;

public class SetSizeController {

	private SetSizeButton view;
	//private DataRoom model;
	
	public SetSizeController(ResourceBundle resources, RoomCanvas canvas, IDataRoom room) {
		view = new SetSizeButton(resources);
		//model = room;
		view.setOnAction(e -> onClick(resources, canvas, room));
	}
	
	private void onClick(ResourceBundle resources, RoomCanvas background, IDataRoom room) {
		SizePopupController sizePopup = new SizePopupController(resources,background, room);
	}
	
	public RoomEditorButton getSetSizeButton() {
		return view;
	}
}
