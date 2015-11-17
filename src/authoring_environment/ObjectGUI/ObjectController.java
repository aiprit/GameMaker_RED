package authoring_environment.ObjectGUI;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import structures.IObject;
import structures.data.DataObject;
import structures.data.DataSprite;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class ObjectController {
	private IObject myObject;
	ObservableList<DataSprite>  mySprites;
	double displayX, displayY;

	public ObjectController(IObject object, ObservableList<DataSprite> map) {
		myObject = object;
		mySprites = map;
		displayX= 1.0;
		displayY= 1.0;
	}

	public void setName(String name) {
		myObject.setName(name);
	}

	public void setSize(double x, double y) {
		displayX = x;
		displayY = y;
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

	public double[] getSize() {
		return new double [] {displayX,displayY};
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

//	public void changeVisibility(boolean b) {
//		myObject.setVisible(b);
//	}

	public DataSprite getCurrentSprite() {
		return myObject.getSprite();
	}

//	public boolean getVisibile() {
//		return myObject.isVisible();
//	}

}
