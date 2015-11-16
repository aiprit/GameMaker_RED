package structures.data;

import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import structures.IObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;
import utils.Point;

public class DataObject implements IObject {

	private ObservableMap<IDataEvent, List<IAction>> myEvents;

	private final String myName;
	private String mySprite;

	private Point myPosition;
	private double myAngle, myAngularVelocity;
	private double myAlpha;

	private boolean myVisible;
	private int myZIndex;

	public DataObject(String name) {
		myName = name;
		myZIndex = 0;
		myPosition = Point.ORIGIN;
		myAngle = 0.0;
		myAngularVelocity = 0.0;
		myVisible = true;
		myEvents = FXCollections.observableMap(new HashMap<IDataEvent, List<IAction>>());
	}
	
	@Override
	public String getName() {
		return myName;
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
	public void setSprite(String sprite) {
		mySprite = sprite; 
	}
	@Override
	public String getSprite() {
		return mySprite;
	}
	

	@Override
	public void setPosition(Point pos) {
		myPosition = pos;
	}
	@Override
	public Point getPosition() {
		return myPosition;
	}
	

	@Override
	public void setVisible(boolean visible) {
		myVisible = visible;
	}
	@Override
	public boolean isVisible() {
		return myVisible;
	}
	

	@Override
	public void setAngle(double angle) {
		myAngle = angle;
	}
	@Override
	public double getAngle() {
		return myAngle;
	}
	
	@Override
	public void setAngularVelocity(double angularVelocity) {
		myAngularVelocity = angularVelocity;
	}
	@Override
	public double getAngularVelocity() {
		return myAngularVelocity;
	}
	
	
	@Override
	public double getAlpha() {
		return myAlpha;
	}
	@Override
	public void setAlpha(double alpha) {
		myAlpha = alpha;
	}
	

	@Override
	public void setZIndex(int zIndex){
		myZIndex = zIndex;
	}
	public int getZIndex() {
		return myZIndex;
	}
}

