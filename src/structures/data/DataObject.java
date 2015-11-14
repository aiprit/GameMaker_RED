package structures.data;

import java.util.List;
import authoring_environment.Sprite;
import javafx.geometry.Point2D;
import structures.IObject;
import structures.data.events.IDataEvent;

public class DataObject implements IObject {

	private List<IDataEvent> myEvents;

	private String myName;
	private DataSprite mySprite;

	private Point2D myPosition;
	private double myRotation, myAngularVelocity;
	private int myAlpha, myWidth, myHeight;

	private boolean myVisible;
	private int myZIndex;

	public DataObject(String name, int width, int height){
		myName = name;
		myWidth = width;
		myHeight = height;
		myZIndex = 0;
	}
	
	public List<IDataEvent> getEvents() {
	    return myEvents;
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
	public void addSprite(DataSprite s) {
		mySprite = s;
	}

	@Override
	public String getName() {
		return myName;
	}


	@Override
	public int[] getSize() {
		return new int[] {myWidth, myHeight};
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
    public Sprite getSprite () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setName (String name) {
        // TODO Auto-generated method stub
        
    }
}
