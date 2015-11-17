package authoring_environment.ObjectGUI;

import javafx.application.Application;
import javafx.stage.Stage;
import structures.IObject;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataSprite;

public class ObjectGUITester extends Application{
	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage primaryStage) throws Exception {
		DataGame game = new DataGame("TestGame", "/home/nicholas");
		DataObject object = new DataObject("dog");
		ObjectController c = new ObjectController(object, null, game);
		//		DataSprite sprite = new DataSprite();
		//		object.addSprite(sprite);
		ObjectGUI og = new ObjectGUI(c);
		og.init();
	}
}
