package authoring_environment.ObjectGUI;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import structures.IObject;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataSprite;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class ObjectController {
	private IObject myObject;
	ObservableList<DataSprite>  mySprites;
	double displayX, displayY;
	private ObservableList<IObject>  objects;
	private Stage myStage;

	public ObjectController(IObject object, ObservableList<DataSprite> map, ObservableList<IObject> obj,Stage st) {
		myObject = object;
		mySprites = map;
		displayX= 1.0;
		displayY= 1.0;
		objects = obj;
		myStage =st;
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

	public ObservableMap<IDataEvent, List<IAction>>  getEvents() {
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

	public void addEvent(IDataEvent e,ObservableList<IAction> actions) {
		myObject.bindEvent(e,actions);
	}

	public void deleteEvent(IDataEvent e) {
		myObject.removeEvent(e);
	}



	public DataSprite getCurrentSprite() {
		return myObject.getSprite();
	}

	public ObservableList<IObject> getObjects() {
		return objects;
	}

	public void close(ActionEvent e) {
		 Node  source = (Node)  e.getSource();
		 Stage stage  = (Stage) source.getScene().getWindow();
		 stage.close();
	}
	public DataObject getObject(){
		return (DataObject) myObject;
	}

}
