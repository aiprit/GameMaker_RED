package authoring_environment.EventGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class EventController {
	private IDataEvent myEvent;
	private ObservableMap<IDataEvent, ObservableList<IAction>> map;
	private ObservableList<IAction> alist;
	private DataObject myObject;
	public EventController(IDataEvent e, DataObject obj){
		myEvent = e;
		myObject = obj;
	}


	public ObservableList<IAction> getActions() {
		map = myObject.getEvents();
		if(map.containsKey(myEvent))
			return map.get(myEvent);
		return FXCollections.observableList(new ArrayList<IAction>());
	}


	public void close(ActionEvent e) {
		 Node  source = (Node)  e.getSource();
		 Stage stage  = (Stage) source.getScene().getWindow();
		 stage.close();
	}

	public void save(){
		myObject.bindEvent(myEvent,alist);
	}


	public void saveActions(ObservableList<IAction> l) {
		alist= l;
	}
}
