package authoring_environment.ObjectPopUps;


import authoring_environment.Event.EventController;

import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.events.KeyPressedEvent;
import structures.data.events.KeyReleasedEvent;


public class KeyPressedPopUp extends KeyPopUp {

	public KeyPressedPopUp(DataObject obj) {
		super(obj);
		init();

		// TODO Auto-generated constructor stub
	}
	public void eventPopup(){

		if(key ==null){
			nullAlert();
		}
		else{
			EventController p = new EventController(new KeyPressedEvent(key),myObject);

		}
	}


}
