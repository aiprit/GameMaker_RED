package authoring_environment.EventGUI.PopUps;

import authoring_environment.EventPopup;
import javafx.collections.ObservableMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import structures.data.events.KeyPressedEvent;


public class KeyPressPopUp extends KeyPopUp {
	public KeyPressPopUp(ObservableMap m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	public void eventPopup(){

		if(key ==null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No Key Selected");
			alert.setContentText("Please Select a Key");
			alert.showAndWait();
		}
		else{
			EventPopup p = new EventPopup();
			p.popup(new KeyPressedEvent(key),myMap);
			myStage.close();
		}
	}
}
