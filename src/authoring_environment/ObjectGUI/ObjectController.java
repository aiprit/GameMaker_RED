package authoring_environment.ObjectGUI;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import structures.IObject;
import structures.data.DataGame;
import structures.data.DataSprite;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class ObjectController {
	private IObject myObject;
	ObservableList<DataSprite>  mySprites;
	double displayX, displayY;
	private DataGame game;

	public ObjectController(IObject object, ObservableList<DataSprite> map, DataGame g) {
		myObject = object;
		mySprites = map;
		displayX= 1.0;
		displayY= 1.0;
		game = g;
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



	public DataSprite getCurrentSprite() {
		return myObject.getSprite();
	}

	public List<IObject> getObjects() {
		return game.getObjects();
	}

}
