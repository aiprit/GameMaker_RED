package authoring_environment.object_editor;


import java.util.List;

import authoring_environment.ObjectPopUps.CollisionPopUp;
import authoring_environment.ObjectPopUps.GameEndPopUp;
import authoring_environment.ObjectPopUps.GameStartPopUp;
import authoring_environment.ObjectPopUps.KeyPressPopUp;
import authoring_environment.ObjectPopUps.KeyReleasePopUp;
import authoring_environment.ObjectPopUps.ObjectCreatePopUp;
import authoring_environment.ObjectPopUps.ObjectDestroyPopUp;
import authoring_environment.ObjectPopUps.PopUp;
import javafx.collections.ObservableList;
import structures.data.DataObject;






public class EventPopupFactory {
	PopUp kp;
	public void create(String event,DataObject obj, ObservableList<DataObject> list) {
		//		if (event.equalsIgnoreCase("Collision Event")) {
		//
		//		}
		if (event.equalsIgnoreCase("Game End Event")) {
			kp = new GameEndPopUp(obj);
		}
		if (event.equalsIgnoreCase("Collision Event")) {
			kp = new CollisionPopUp(obj,list);
		}
		
		if (event.equalsIgnoreCase("On Game Start Event")) {
			kp = new GameStartPopUp(obj);
		}
		
		if (event.equalsIgnoreCase("On Key Press Event")) {
			kp = new KeyPressPopUp(obj);
		}
		if (event.equalsIgnoreCase("On Key Release Event")) {
			kp = new KeyReleasePopUp(obj);
		}
		if (event.equalsIgnoreCase("On Object Create Event")) {
			kp = new ObjectCreatePopUp(obj);
		}
		if (event.equalsIgnoreCase("On Object Destroy Event")) {
			kp = new ObjectDestroyPopUp(obj);
		}
		//	}

		kp.init();
	}

}
