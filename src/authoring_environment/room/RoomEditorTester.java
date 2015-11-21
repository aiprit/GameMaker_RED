package authoring_environment.room;

import authoring_environment.room.RoomController;
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
		RoomController controller = new RoomController(room, test.getTestGame());
		controller.launch();
	}
}