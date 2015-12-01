package authoring_environment.room.name_popup;

import java.util.ResourceBundle;

import authoring_environment.room.PopupTemplate;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class RoomNamePopup extends PopupTemplate {
	private static final int CONTENTS_SPACING = 15;
	private static final String START_ROOM_BUTTON_TEXT = "StartRoomButtonText";
	private static final String ROOM_NAME = "RoomName";
	private static final String SET_ROOM_NAME = "SetRoomName";

	private TextField myRoomName;
	private RadioButton myStartRoom;
	
	public RoomNamePopup(ResourceBundle resources) {
		super(resources, ROOM_NAME);
	}
	
	public RoomNamePopup(ResourceBundle resources, String roomName) {
		super(resources, ROOM_NAME);
		myRoomName.setText(roomName);
	}
	
	public String getRoomName() {
		return myRoomName.getText();
	}
	
	public RadioButton getStartRoomButton() {
		return myStartRoom;
	}

	@Override
	protected void setContents() {
		myContentsBox.getChildren().addAll(createContents(), initializeStartRoomButton());
		myContentsBox.setSpacing(CONTENTS_SPACING);
	}
	
	private GridPane createContents() {
		GridPane pane = new GridPane();
		Text setRoomName = new Text(myResources.getString(SET_ROOM_NAME));
		myRoomName = new TextField();
		pane.add(setRoomName, 0, 0);
		pane.add(myRoomName, 1, 0);
		pane.setAlignment(Pos.CENTER);
		return pane;
	}
	
	private HBox initializeStartRoomButton() {
		HBox buttonContainer = new HBox();
		buttonContainer.setAlignment(Pos.CENTER);
		myStartRoom = new RadioButton(myResources.getString(START_ROOM_BUTTON_TEXT));
		buttonContainer.getChildren().add(myStartRoom);
		return buttonContainer;
	}

}
