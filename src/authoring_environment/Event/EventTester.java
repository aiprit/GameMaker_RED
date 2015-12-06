package authoring_environment.Event;

import java.util.ArrayList;

import authoring_environment.Event.EventController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;

import structures.data.actions.object.Destroy;

import structures.data.events.ObjectCreateEvent;
import structures.data.interfaces.IAction;
import structures.data.interfaces.IDataEvent;

public class EventTester  extends Application{
	public static void main(String[] args) {
		launch(args);

	}
	public void start(Stage primaryStage) throws Exception {
//		DataGame game = new DataGame("TestGame", "/home/nicholas");
//		DataObject object = new DataObject("Luigi");
//		DataObject object2 = new DataObject("cat");
//
//
//		IDataEvent a = new ObjectCreateEvent();
//		ObservableList<IAction> c =FXCollections.observableList( new ArrayList<IAction>());
//		c.add(new Destroy());
//		c.add(new Sleep());
//		object.bindEvent(a,c);
//		game.addObject(object);
//		game.addObject(object2);
//		Stage cc = new Stage();
//		EventController gui = new EventController(a,object);

	}

}
