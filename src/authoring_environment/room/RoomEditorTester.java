package authoring_environment.room;

import authoring_environment.room.name_popup.RoomNamePopupController;
import javafx.application.Application;
import javafx.stage.Stage;
import structures.TestGameObject;
import structures.data.DataRoom;

public class RoomEditorTester extends Application {


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		TestGameObject test = new TestGameObject();
		DataRoom room = test.getTestGame().getRooms().get(1);
		RoomNamePopupController controller = new RoomNamePopupController(room, test.getTestGame());
	}
}