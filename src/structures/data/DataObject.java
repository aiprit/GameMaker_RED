package structures.data;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import structures.IObject;
import structures.data.events.IDataEvent;

public class DataObject implements IObject {

	private ObservableList<IDataEvent> myEvents;

	private String myName;
	private DataSprite mySprite;

	private Point2D myPosition;
	private double myRotation, myAngularVelocity;
	private double myAlpha, myWidth, myHeight;

	private boolean myVisible;
	private int myZIndex;

	public DataObject(String name, double width, double height){
		myName = name;
		myWidth = width;
		myHeight = height;
		myZIndex = 0;
		myEvents = FXCollections.observableList(new ArrayList<>());
	}

	@Override
	public void addEvent(IDataEvent e) {
		myEvents.add(e);
	}

	public void deleteEvent(IDataEvent e) {
		myEvents.remove(e);
	}

	@Override
	public void addSprite(DataSprite s) {
		mySprite = s;
	}

	@Override
	public String getName() {
		return myName;
	}


	@Override
	public double[] getSize() {
		return new double[] {myWidth, myHeight};
	}

	@Override
	public void setPosition(Point2D pos) {
		myPosition = pos;
	}

	@Override
	public javafx.geometry.Point2D getPosition() {
		return myPosition;
	}

	@Override
	public void setVisibility(boolean visible) {
		myVisible = visible;
	}

	@Override
	public boolean isVisible() {
		return myVisible;
	}

	@Override
	public void setRotation(double angle) {
		myRotation = angle;
	}

	@Override
	public double getRotation() {
		return myRotation;
	}
	
	public double getVelocity() {
	    return myAngularVelocity;
	}

	public void setZIndex(int zIndex){
		myZIndex = zIndex;
	}

	@Override

	public ObservableList<IDataEvent> getEvents(){
		return myEvents;
	}

	public void setSize(double width, double height) {
		myWidth = width;
		myHeight = height;
	}

	@Override
	public void setName(String name) {
		myName = name;
	}

	@Override
	public DataSprite getSprite() {
		return mySprite;
	}
}

