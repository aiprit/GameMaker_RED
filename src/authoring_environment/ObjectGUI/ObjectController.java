package authoring_environment.ObjectGUI;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import structures.data.DataObject;
import structures.data.DataSprite;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class ObjectController {
	private DataObject myObject;
	ObservableList<DataSprite>  mySprites;

	public ObjectController(DataObject object, ObservableList<DataSprite> map) {
		myObject = object;
		mySprites = map;
	}

	public void setName(String name) {
		myObject.setName(name);
	}

	public void setSize(double x, double y) {
		myObject.getSprite().setScaleX(x);
		myObject.getSprite().setScaleY(y);
	}

	public ObservableList<DataSprite> getSprites() {
		return mySprites;
	}

	public ObservableMap<IDataEvent, List<IAction>>  getEvents() {
		return myObject.getEvents();
	}

	public String getName() {
		return myObject.getName();
	}

	public double[] getSize() {
		return new double [] {myObject.getSprite().getScaleX(),myObject.getSprite().getScaleY()};
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
}
