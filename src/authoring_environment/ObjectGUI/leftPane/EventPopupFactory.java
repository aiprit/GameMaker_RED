package authoring_environment.ObjectGUI.leftPane;


import java.util.List;

import authoring_environment.EventGUI.PopUps.GameEndPopUp;
import authoring_environment.EventGUI.PopUps.GameStartPopUp;
import authoring_environment.EventGUI.PopUps.KeyPressPopUp;
import authoring_environment.EventGUI.PopUps.KeyReleasePopUp;
import authoring_environment.EventGUI.PopUps.ObjectCreatePopUp;
import authoring_environment.EventGUI.PopUps.ObjectDestroyPopUp;
import authoring_environment.EventGUI.PopUps.PopUp;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;



public class EventPopupFactory {
	PopUp kp;
	public void create(String event,DataObject obj,ObservableList list) {
		//		if (event.equalsIgnoreCase("Collision Event")) {
		//
		//		}
		if (event.equalsIgnoreCase("Game End Event")) {
			kp = new GameEndPopUp(obj);
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