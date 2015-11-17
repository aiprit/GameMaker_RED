package authoring_environment.ObjectGUI.leftPane;

import java.lang.reflect.Field;
import java.util.Optional;

import javax.swing.KeyStroke;

import authoring_environment.Event.GUI.PopUps.KeyPopUp;
import authoring_environment.Event.GUI.PopUps.KeyPressPopUp;
import authoring_environment.Event.GUI.PopUps.KeyReleasePopUp;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import structures.data.events.KeyPressedEvent;

public class EventPopupFactory {

	public void create(String event) {
		//		if (event.equalsIgnoreCase("Collision Event")) {
		//
		//		}
		//		if (event.equalsIgnoreCase("Game End Event")) {
		//
		//		}
		//		if (event.equalsIgnoreCase("On Game Start Event")) {
		//
		//		}
		if (event.equalsIgnoreCase("On Key Press Event")) {
			KeyPopUp kp = new KeyPressPopUp();
			kp.init();

		}
		if (event.equalsIgnoreCase("On Key Release Event")) {
			KeyPopUp kp = new KeyReleasePopUp();
			kp.init();
		}

		//		if (event.equalsIgnoreCase("On Object Create Event")) {
		//
		//		}
		//		if (event.equalsIgnoreCase("On Object Destroy Event")) {
		//
		//		}
		//	}



	}

}