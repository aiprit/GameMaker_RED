package authoring_environment.ObjectGUI;

import javafx.application.Application;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.DataSprite;
import structures.data.events.KeyTypedEvent;

public class ObjectGUITester extends Application{
	public static void main(String[] args) {
		launch(args);		

	}

	public void start(Stage primaryStage) throws Exception {
		DataObject object = new DataObject("dog",10,10);
		object.addEvent(new KeyTypedEvent());
//		DataSprite sprite = new DataSprite();
//		object.addSprite(sprite);
		ObjectGUI og = new ObjectGUI(object, primaryStage);
		og.init();
	}
}
