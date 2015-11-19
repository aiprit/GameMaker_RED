package authoring_environment.ObjectPopUps;

<<<<<<< HEAD
import authoring_environment.main.EventPopup;
=======
import authoring_environment.Event.EventController;
>>>>>>> a729fee0b69507a58aff9d3d702b022182226556
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
			EventController p = new EventController(new KeyPressedEvent(key),myObject);

		}
	}


}
