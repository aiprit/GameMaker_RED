package authoring_environment.EventGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class EventController {
	DataObject myObject;
	IDataEvent myEvent;
	Stage myStage;
	public EventController(IDataEvent e,Stage st){
		myEvent = e;
		myStage = st;
	}


	public List<IAction> getActions() {
		Map<IDataEvent, List<IAction>> map = myObject.getEvents();
		if(map.containsKey(myEvent))
			return map.get(myEvent);
		return new ArrayList<IAction>();
	}


	public void close(ActionEvent e) {
		 Node  source = (Node)  e.getSource();
		 Stage stage  = (Stage) source.getScene().getWindow();
		 stage.close();
	}

}
