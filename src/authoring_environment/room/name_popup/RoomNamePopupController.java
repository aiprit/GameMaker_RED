package authoring_environment.room.name_popup;

import java.util.ResourceBundle;
import java.util.function.Consumer;

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
		view.getSaveButton().setOnAction(e -> setNameAndLaunchEditor(model, game));
	}
	
	public RoomNamePopupController(DataRoom room, DataGame game) {
		myResources = ResourceBundle.getBundle(ROOM_RESOURCE_FILE);
		model = room;
		view = new RoomNamePopup(myResources, model.getName());
	}
	
	public RoomNamePopup getPopup() {
		return view;
	}
	
	private void setNameAndLaunchEditor(DataRoom room, DataGame game) {
		setName();
		game.addRoom(model);
		RoomController roomController = new RoomController(myResources, room, game);
		view.close();
		roomController.launch();
	}
	
	private void setName() {
		try {
			model.setName(view.getRoomName());
		} catch (NullPointerException e) {
			//TODO launch exception popup
		}
	}
	
	public void setOnClose(Consumer<Void> updateFcn, DataGame game) {
		view.getSaveButton().setOnAction(e -> setNameAndLaunchEditor(model, game));
		updateFcn.accept(null);
	}

}
