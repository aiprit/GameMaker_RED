package authoring_environment.room;

import java.util.ResourceBundle;

import authoring_environment.room.name_popup.RoomNamePopupController;
import javafx.application.Application;
import javafx.stage.Stage;
import structures.TestGameObject;
import structures.data.DataRoom;

public class RoomEditorTester extends Application {


	public static void main(String[] args) {
		launch(args);
	}
	
	public String getGamesDirectory() {
		String path = getClass().getResource("/dummy.file").getPath().replace("dummy.file", "");
		return path;
    }

	@Override
	public void start(Stage primaryStage) {
		TestGameObject test = new TestGameObject();
		DataRoom room = test.getTestGame(getGamesDirectory()).getRooms().get(1);
		ResourceBundle myResources = ResourceBundle.getBundle("resources/RoomResources");
		RoomController controller = new RoomController(myResources, room, test.getTestGame(getGamesDirectory()));
		controller.launch();
	}
}