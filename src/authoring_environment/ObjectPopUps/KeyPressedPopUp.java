package authoring_environment.ObjectPopUps;


import authoring_environment.PopUpError;
import authoring_environment.Event.EventController;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.KeyPressedEvent;



public class KeyPressedPopUp extends KeyPopUp {

	public KeyPressedPopUp(DataObject obj,IObjectInterface game) {
		super(obj,game);
		init();

		// TODO Auto-generated constructor stub
	}
	public void eventPopup(){

		if(key ==null){
			new PopUpError(r.getString("Error"));
		}
		else{
			EventController p = new EventController(new KeyPressedEvent(key),myObject,myGame);
			p.showAndWait();
		}
	}


}
