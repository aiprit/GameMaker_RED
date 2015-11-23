package authoring_environment.room.name_popup;

import java.util.ResourceBundle;

import authoring_environment.room.RoomController;
import structures.data.DataGame;
import structures.data.DataRoom;

public class RoomNamePopupController {
	private static final String ROOM_RESOURCE_FILE = "resources/RoomResources";
	
	private ResourceBundle myResources;
	private RoomNamePopup view;
	private DataRoom model;
	
	public RoomNamePopupController(DataGame game) {
		myResources = ResourceBundle.getBundle(ROOM_RESOURCE_FILE);
		view = new RoomNamePopup(myResources);
		model = new DataRoom("", game.getViewWidth(), game.getViewHeight());
		game.addRoom(model);
		view.getSaveButton().setOnAction(e -> setNameAndLaunchEditor(model, game));
	}
	
	public RoomNamePopupController(DataRoom room, DataGame game) {
		myResources = ResourceBundle.getBundle(ROOM_RESOURCE_FILE);
		model = room;
		view = new RoomNamePopup(myResources, model.getName());
		view.getSaveButton().setOnAction(e -> setNameAndLaunchEditor(room, game));
	}
	
	private void setNameAndLaunchEditor(DataRoom room, DataGame game) {
		setName();
		RoomController roomController = new RoomController(myResources, room, game);
		view.close();
		roomController.launch();
	}
	
	private void setName() {
		try {
			model.setName(view.getRoomName());
		} catch (NullPointerException e) {
			//TODO exception popup here
		}
	}

}
