package authoring_environment.ObjectGUI;

import javafx.application.Application;
import javafx.stage.Stage;
import structures.IObject;
import structures.data.DataObject;
import structures.data.DataSprite;
import structures.data.events.KeyTypedEvent;

public class ObjectGUITester extends Application{
	public static void main(String[] args) {
		launch(args);		

	}

	public void start(Stage primaryStage) throws Exception {
		IObject object = new DataObject("dog", 100, 100);
		ObjectController c = new ObjectController(object, null);
		//		DataSprite sprite = new DataSprite();
		//		object.addSprite(sprite);
		ObjectGUI og = new ObjectGUI(c);
		og.init();
	}
}
