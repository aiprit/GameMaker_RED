package authoring_environment.ObjectPopUps;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;


public abstract class BasicPopUp implements PopUp {

	protected DataObject myObject;
	protected IObjectInterface myGame;

	public BasicPopUp(DataObject obj,IObjectInterface game){
		myObject = obj;
		myGame = game;
	}

	public void init() {
		eventPopup();

	}
	protected void close(Event e) {
		 Node  source = (Node)  e.getSource();
		 Stage stage  = (Stage) source.getScene().getWindow();
		 stage.close();
	}

}
