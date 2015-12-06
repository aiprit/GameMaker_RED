package authoring_environment.room.name_popup;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import authoring_environment.room.RoomController;
import authoring_environment.room.error.ErrorPopup;
import authoring_environment.room.preview.RoomCanvas;
import authoring_environment.room.view.DraggableView;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import structures.data.DataGame;
import structures.data.DataRoom;
import structures.data.IDataGame;

public class RoomNamePopupController {
	private static final String ICON_SIZE = "IconSize";
	private static final String DUPLICATE_ROOM_NAME_ERROR_MESSAGE = "DuplicateRoomNameErrorMessage";
	private static final String NULL_ROOM_NAME_ERROR_MESSAGE = "NullRoomNameErrorMessage";
	private static final String DEFAULT_ROOM_BACKGROUND_COLOR = "DefaultRoomBackgroundColor";
	private static final String ROOM_RESOURCE_FILE = "resources/RoomResources";
	
	private ResourceBundle myResources;
	private RoomNamePopup view;
	private DataRoom model;
	private int myIndex;
	
	public RoomNamePopupController(int i, IDataGame game) {
		myResources = ResourceBundle.getBundle(ROOM_RESOURCE_FILE);
		view = new RoomNamePopup(myResources);
		model = new DataRoom("", game.getViewWidth(), game.getViewHeight());
		model.setBackgroundColor(myResources.getString(DEFAULT_ROOM_BACKGROUND_COLOR));
		myIndex = i;
	}
	
	public RoomNamePopupController(DataRoom room, int i, IDataGame game) {
		myResources = ResourceBundle.getBundle(ROOM_RESOURCE_FILE);
		model = room;
		view = new RoomNamePopup(myResources, model.getName());
		view.getStartRoomButton().setSelected(game.getStartRoomIndex() == i);
		myIndex = i;
	}
	
	public RoomNamePopup getPopup() {
		return view;
	}
	
	private void setNameAndLaunchEditor(DataRoom room, IDataGame game, boolean addRoom, Consumer<Void> updateFcn) {
		if (saveRoomName(room, game, addRoom, updateFcn)) {
			RoomController roomController = new RoomController(myResources, room, game);
			roomController.getEditor().setOnClose(e -> updateMainGuiOnEditorClose(roomController, updateFcn));
			roomController.launch();
		}	
	}

	private boolean saveRoomName(DataRoom room, IDataGame game, boolean addRoom, Consumer<Void> updateFcn) {
		if (!view.getRoomName().equals("") && !isDuplicateName(room, game)) {
			model.setName(view.getRoomName());
		} else {
			String errorMessage = view.getRoomName().equals("") ? myResources.getString(NULL_ROOM_NAME_ERROR_MESSAGE) :
				myResources.getString(DUPLICATE_ROOM_NAME_ERROR_MESSAGE);
			ErrorPopup error = new ErrorPopup(myResources, errorMessage);
			return false;
		}
		setStartRoom(game);
		if (addRoom) {
			game.addRoom(model);
		}
		view.close();
		updateFcn.accept(null);
		return true;
	}
	
	private boolean isDuplicateName(DataRoom room, IDataGame game) {
		List<String> roomNames = game.getRooms().stream()
				.map(r -> r.getName())
				.collect(Collectors.toList());
		return roomNames.contains(view.getRoomName()) && roomNames.indexOf(view.getRoomName()) != myIndex;
	}
	
	private void setStartRoom(IDataGame game) {
		if (view.getStartRoomButton().isSelected()) {
			game.setStartRoom(myIndex);
		}
	}
	
	public void setOnClose(Consumer<Void> updateFcn, DataGame game, boolean addRoom) {
		view.getOpenEditorButton().setOnAction(e -> setNameAndLaunchEditor(model, game, addRoom, updateFcn));
		view.getSaveButton().setOnAction(e -> saveRoomName(model, game, addRoom, updateFcn));
	}
	
	private void updateMainGuiOnEditorClose(RoomController controller, Consumer<Void> updateFcn) {
		RoomCanvas canvas = controller.getEditor().getPreview().getCanvas();
		DraggableView view = canvas.getRoomView();
		canvas.drawSnapshot();
		int iconSize = (int)Double.parseDouble(myResources.getString(ICON_SIZE));
		WritableImage roomSnapshot = new WritableImage(iconSize, iconSize);
		SnapshotParameters params = new SnapshotParameters();
		Point2D newCoords = canvas.localToParent(new Point2D(view.getX(), view.getY()));
		Rectangle2D viewShot = new Rectangle2D(newCoords.getX() - view.getWidth() / 2.0, newCoords.getY() - view.getHeight() / 2.0, view.getWidth(), view.getHeight());
		params.setFill(Color.TRANSPARENT);
		params.setViewport(viewShot);
		double iconScale = iconSize / view.getWidth();
		Scale scale = new Scale(iconScale, iconScale, view.getX(), view.getY());
		params.setTransform(scale);
		canvas.snapshot(params, roomSnapshot);
		model.setSnapshot(roomSnapshot);
		updateFcn.accept(null);
	}

}
