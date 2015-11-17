package authoring_environment.ObjectGUI;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataSprite;
import structures.data.actions.Destroy;
import structures.data.actions.IAction;
import structures.data.actions.Sleep;
import structures.data.events.IDataEvent;
import structures.data.events.KeyPressedEvent;
import structures.data.events.ObjectCreateEvent;

public class ObjectGUITester extends Application{
	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage primaryStage) throws Exception {
		DataGame game = new DataGame("TestGame", "/home/nicholas");
		DataObject object = new DataObject("dog");


		IDataEvent a = new ObjectCreateEvent();
		List<IAction> c = new ArrayList<IAction>();
		c.add(new Destroy());
		c.add(new Sleep());
		object.bindEvent(a,c);
		Stage cc = new Stage();
		ObjectController ct = new ObjectController(object, null, null,cc);

		//		DataSprite sprite = new DataSprite();
		//		object.addSprite(sprite);
		ObjectGUI og = new ObjectGUI(ct);
		og.init();
	}
}
