package authoring_environment.room.button_toolbar;

import authoring_environment.room.preview.canvas.RoomBackground;
import structures.data.DataGame;

public class ButtonModel {
	private DataGame myDataGame;
	private RoomBackground roomBackground;
	public ButtonModel(DataGame dataGame, RoomBackground roomBack) {
		myDataGame = dataGame;
		roomBackground = roomBack;
	}
}
