package authoring_environment.EventGUI.PopUps;

import authoring_environment.main.EventPopup;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.events.KeyPressedEvent;
import structures.data.events.KeyReleasedEvent;


public class KeyPressPopUp extends KeyPopUp {

	public KeyPressPopUp(DataObject obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}
	public void eventPopup(){

		if(key ==null){
			nullAlert();
		}
		else{
			EventPopup p = new EventPopup();
			p.popup(new KeyPressedEvent(key),myObject);

		}
	}


}
