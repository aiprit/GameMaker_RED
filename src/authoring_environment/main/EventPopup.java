package authoring_environment.main;

import java.util.List;

import authoring_environment.EventGUI.EventController;
import authoring_environment.EventGUI.EventGUI;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class EventPopup {

	public void popup(IDataEvent event,	DataObject obj){

		 EventController control = new EventController(event,obj);
         EventGUI gui = new EventGUI(control);
	}

}
