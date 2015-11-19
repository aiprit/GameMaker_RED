package authoring_environment.object_editor;

import java.util.ArrayList;

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
import structures.data.events.IDataEvent;
import structures.data.events.ObjectCreateEvent;

public class ObjectTester extends Application{
	ObjectEditorController oc;

	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("test");
		DataGame game = new DataGame("Fuck", "FUck");
		DataObject object = new DataObject("Luigi");
		DataSprite sprite = new DataSprite("Luigi.png", "Luigi.png");
		IDataEvent a = new ObjectCreateEvent();
		ObservableList<IAction> c =FXCollections.observableList( new ArrayList<IAction>());
		c.add(new Destroy());
		c.add(new Sleep());
		object.bindEvent(a,c);
		game.addObject(object);
		object.setSprite(sprite);
		oc = new ObjectEditorController(game, object);
	}
}
