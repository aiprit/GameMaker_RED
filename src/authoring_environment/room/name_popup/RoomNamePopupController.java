package authoring_environment.room.name_popup;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import authoring_environment.room.RoomController;
import authoring_environment.room.error.ErrorPopup;
import structures.data.DataGame;
import structures.data.DataRoom;

public class RoomNamePopupController {
	private static final String DUPLICATE_ROOM_NAME_ERROR_MESSAGE = "DuplicateRoomNameErrorMessage";
	private static final String NULL_ROOM_NAME_ERROR_MESSAGE = "NullRoomNameErrorMessage";
	private static final String ERROR = "Error";
	private static final String DEFAULT_ROOM_BACKGROUND_COLOR = "DefaultRoomBackgroundColor";
	private static final String ROOM_RESOURCE_FILE = "resources/RoomResources";
	
	private ResourceBundle myResources;
	private RoomNamePopup view;
	private DataRoom model;
	private int myIndex;
	
	public RoomNamePopupController(int i, DataGame game) {
		myResources = ResourceBundle.getBundle(ROOM_RESOURCE_FILE);
		view = new RoomNamePopup(myResources);
		model = new DataRoom("", game.getViewWidth(), game.getViewHeight());
		model.setBackgroundColor(myResources.getString(DEFAULT_ROOM_BACKGROUND_COLOR));
		myIndex = i;
	}
	
	public RoomNamePopupController(DataRoom room, int i, DataGame game) {
		myResources = ResourceBundle.getBundle(ROOM_RESOURCE_FILE);
		model = room;
		view = new RoomNamePopup(myResources, model.getName());
		view.getStartRoomButton().setSelected(game.getStartRoomIndex() == i);
		myIndex = i;
	}
	
	public RoomNamePopup getPopup() {
		return view;
	}
	
	private void setNameAndLaunchEditor(DataRoom room, DataGame game, boolean addRoom, Consumer<Void> updateFcn) {
		if (!view.getRoomName().equals("") && !isDuplicateName(room, game)) {
			model.setName(view.getRoomName());
		} else {
			String errorMessage = view.getRoomName().equals("") ? myResources.getString(NULL_ROOM_NAME_ERROR_MESSAGE) :
				myResources.getString(DUPLICATE_ROOM_NAME_ERROR_MESSAGE);
			ErrorPopup error = new ErrorPopup(myResources, myResources.getString(ERROR), errorMessage);
			return;
		}
		setStartRoom(game);
		if (addRoom) {
			game.addRoom(model);
		}
		RoomController roomController = new RoomController(myResources, room, game);
		view.close();
		updateFcn.accept(null);
		roomController.getEditor().setOnClose(updateFcn);
		roomController.launch();	
	}
	
	private boolean isDuplicateName(DataRoom room, DataGame game) {
		List<String> roomNames = game.getRooms().stream()
				.map(r -> r.getName())
				.collect(Collectors.toList());
		return roomNames.contains(view.getRoomName()) && roomNames.indexOf(view.getRoomName()) != myIndex;
	}
	
	private void setStartRoom(DataGame game) {
		if (view.getStartRoomButton().isSelected()) {
			game.setStartRoom(myIndex);
		}
	}
	
	public void setOnClose(Consumer<Void> updateFcn, DataGame game, boolean addRoom) {
		view.getSaveButton().setOnAction(e -> setNameAndLaunchEditor(model, game, addRoom, updateFcn));
	}

}
