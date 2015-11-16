package structures.data;

import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import structures.IObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;


public class DataObject implements IObject {

	private ObservableMap<IDataEvent, List<IAction>> myEvents;

	private String myName;
	private DataSprite mySprite;

	private int myZIndex;

	public DataObject(String name) {
		myName = name;
		myEvents = FXCollections.observableMap(new HashMap<>());
		myZIndex = 0;
	}

	@Override
	public String getName() {
		return myName;
	}
	@Override
	public void setName(String name) {
		myName = name;
	}

	@Override
	public void bindEvent(IDataEvent event, List<IAction> actions) {
		myEvents.put(event, actions);
	}
	@Override
	public void removeEvent(IDataEvent e) {
		myEvents.remove(e);
	}
	@Override
	public ObservableMap<IDataEvent, List<IAction>> getEvents(){
		return myEvents;
	}

	@Override
	public void setSprite(DataSprite sprite) {
		mySprite = sprite;
	}

	@Override
	public DataSprite getSprite() {
		return mySprite;
	}
	
	public int getZIndex() {
		return myZIndex;
	}
	public void setZIndex(int zIndex) {
		myZIndex = zIndex;
	}
}

