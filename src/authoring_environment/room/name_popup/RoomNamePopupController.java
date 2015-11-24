package authoring_environment.room.name_popup;

import java.util.ResourceBundle;
import java.util.function.Consumer;

import authoring_environment.room.RoomController;
import structures.data.DataGame;
import structures.data.DataRoom;

public class RoomNamePopupController {
	private static final String DEFAULT_ROOM_BACKGROUND_COLOR = "DefaultRoomBackgroundColor";
	private static final String ROOM_RESOURCE_FILE = "resources/RoomResources";
	
	private ResourceBundle myResources;
	private RoomNamePopup view;
	private DataRoom model;
	
	public RoomNamePopupController(DataGame game) {
		myResources = ResourceBundle.getBundle(ROOM_RESOURCE_FILE);
		view = new RoomNamePopup(myResources);
		model = new DataRoom("", game.getViewWidth(), game.getViewHeight());
		model.setBackgroundColor(myResources.getString(DEFAULT_ROOM_BACKGROUND_COLOR));
	}
	
	public RoomNamePopupController(DataRoom room, DataGame game) {
		myResources = ResourceBundle.getBundle(ROOM_RESOURCE_FILE);
		model = room;
		view = new RoomNamePopup(myResources, model.getName());
	}
	
	public RoomNamePopup getPopup() {
		return view;
	}
	
	private void setNameAndLaunchEditor(DataRoom room, DataGame game, boolean addRoom, Consumer<Void> updateFcn) {
		setName();
		if (addRoom) {
			game.addRoom(model);
		}
		RoomController roomController = new RoomController(myResources, room, game);
		view.close();
		updateFcn.accept(null);
		roomController.getEditor().setOnClose(updateFcn);
		roomController.launch();	
	}
	
	private void setName() {
		try {
			model.setName(view.getRoomName());
		} catch (NullPointerException e) {
			//TODO launch exception popup
		}
	}
	
	public void setOnClose(Consumer<Void> updateFcn, DataGame game, boolean addRoom) {
		view.getSaveButton().setOnAction(e -> setNameAndLaunchEditor(model, game, addRoom, updateFcn));
	}

}
