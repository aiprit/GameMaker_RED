package authoring_environment.room;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;
import structures.TestGameObject;
import structures.data.DataRoom;

public class RoomEditorTester extends Application {


	public static void main(String[] args) {
		launch(args);
	}
	
	public String getGamesDirectory() {
		try {
			String s = new File(".").getCanonicalPath();
			return s;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
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