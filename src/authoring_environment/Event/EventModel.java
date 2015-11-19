package authoring_environment.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class EventModel {
	DataObject myObject;
	IDataEvent myEvent;
	private ObservableList<IAction> alist;
	private ResourceBundle l = ResourceBundle.getBundle("authoring_environment/Event/ActionListResources");
	public EventModel(DataObject obj,IDataEvent e){
		myObject = obj;
		myEvent = e;
		alist = getActions();
	}
	public EventModel(DataObject obj){
		myObject = obj;
	}
	public void saveEvent(){
		myObject.bindEvent(myEvent,alist);
	}
	public ObservableList<IAction> getActions(){
		ObservableMap<IDataEvent, ObservableList<IAction>> map = myObject.getEvents();
		if(map.containsKey(myEvent))
			return map.get(myEvent);
		return FXCollections.observableList(new ArrayList<IAction>());
	}
	public void addAction(IAction a) {
		alist.add(a);
	}
	public void deleteAction(IAction a) {
		// TODO Auto-generated method stub
		alist.remove(a);
	}
	public ObservableList<String> initTempActions(){
		ObservableList<String> list = FXCollections.observableList(new ArrayList<String>());
		Enumeration <String> keys = l.getKeys();
		List<String> keylist = Collections.list(keys);
		Collections.sort(keylist);
		for (String str:keylist) {
			String value = l.getString(str);
			list.add(value);

		};
		return list;
	}
}
