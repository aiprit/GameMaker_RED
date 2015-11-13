package structures.data;

import java.util.List;

import authoring_environment.Sprite;
import javafx.geometry.Point2D;
import structures.IObject;
import structures.data.events.IDataEvent;

public class DataObject implements IObject {
	
    List<IDataEvent> myEvents;

    String myName;
    Sprite mySprite;

    Point2D myPosition;
    double myRotation, myAngularVelocity;
    double myAlpha, myWidth, myHeight;
    
    boolean myVisible;
    int myZIndex;

	public DataObject(String name){
		myName = name;
	}

	@Override
	public void addEvent(IDataEvent e) {
		myEvents.add(e);
	}

	@Override
	public void deleteEvent(IDataEvent e) {
		myEvents.remove(e);
	}

	@Override
	public void addSprite(Sprite s) {
		mySprite = s;
	}

	@Override
	public void setName(String name) {
		myName = name;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	public void setSize(double width, double height) {
		myWidth = width;
		myHeight = height;
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
}
