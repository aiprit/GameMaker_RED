package authoring_environment.ObjectGUI;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataSprite;
import structures.data.actions.Destroy;
import structures.data.actions.IAction;
import structures.data.actions.Sleep;
import structures.data.events.CollisionEvent;
import structures.data.events.IDataEvent;
import structures.data.events.KeyPressedEvent;
import structures.data.events.ObjectCreateEvent;

public class ObjectGUITester extends Application{
	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage primaryStage) throws Exception {
		DataGame game = new DataGame("TestGame", "/home/nicholas");
		DataObject object = new DataObject("Luigi");
		DataObject object2 = new DataObject("cat");


		IDataEvent a = new ObjectCreateEvent();
		ObservableList<IAction> c =FXCollections.observableList( new ArrayList<IAction>());
		c.add(new Destroy());
		c.add(new Sleep());
		object.bindEvent(a,c);
		DataSprite sprite = new DataSprite("Luigi.png", "");
		DataSprite sprite2 = new DataSprite("black.png", "");
		object.addSprite(sprite);
		object.addSprite(sprite2);
		object.setSprite(sprite);
		object2.setSprite(sprite2);

		game.addObject(object);
		game.addObject(object2);
		game.addSprite(sprite);
		game.addSprite(sprite2);
		Stage cc = new Stage();
		ObjectController ct = new ObjectController(object,game.getObjects(),game.getSprites() ,cc);

		ObjectView og = new ObjectView(ct);

		og.init();
	}
}
