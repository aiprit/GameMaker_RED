package authoring_environment;

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



	public void popup(IDataEvent event,	ObservableMap<IDataEvent,List<IAction>> m){

		Stage dialog = new Stage();
		 EventController control = new EventController(event,dialog,m);
         EventGUI gui = new EventGUI(control);
	}

}
