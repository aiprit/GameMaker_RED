package authoring_environment.ObjectGUI;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import structures.data.DataGame;
import javafx.collections.ObservableMap;
import structures.data.DataObject;
import structures.data.DataSprite;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class ObjectController {
	private DataObject myObject;
	ObservableList<DataSprite>  mySprites;
	double displayX, displayY;
	private DataGame game;

	public ObjectController(DataObject object, ObservableList<DataSprite> map, DataGame g) {
		myObject = object;
		mySprites = map;
		displayX= 1.0;
		displayY= 1.0;
		game = g;
	}

	public void setName(String name) {
		myObject.setName(name);
	}

	public ObservableList<DataSprite> getSprites() {
		return mySprites;
	}

	public Map<IDataEvent, List<IAction>>  getEvents() {
		return myObject.getEvents();
	}

	public String getName() {
		return myObject.getName();
	}

	public void addSprite(DataSprite sprite) {
		myObject.setSprite(sprite);
	}

	public void addEvent(IDataEvent e,List<IAction> actions) {
		myObject.bindEvent(e,actions);
	}

	public void deleteEvent(IDataEvent e) {
		myObject.removeEvent(e);
	}

	public DataSprite getCurrentSprite() {
		return myObject.getSprite();
	}

	public List<DataObject> getObjects() {
		return game.getObjects();
	}

}
