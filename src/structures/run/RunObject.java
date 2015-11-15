package structures.run;

import java.util.Map;
import java.util.Vector;
import javafx.event.EventType;
import structures.IObject;
import structures.data.DataObject;

public class RunObject implements IRunDrawable {
	
	private String myName;
	private String sprite;
	int myWidth;
	int myHeight;
	double rotation;
	double velocity;
	Map<EventType, RunAction> myEvents;
	double x;
	double y;
	
	public RunObject(DataObject dataObject) {
	    myName = dataObject.getName();
	    myWidth = dataObject.getSize()[0];
	    myHeight = dataObject.getSize()[1];
	    rotation = dataObject.getRotation();
	    velocity = dataObject.getVelocity();
	    x = dataObject.getPosition().getX();
	    y = dataObject.getPosition().getY();
	}
	
	public DataObject toData() {
	    return new DataObject(myName, myWidth, myHeight);
	}
	
	public void movementAngle(double angle, double velocity, boolean relative){
		
	}
	
	public void movementTowards(double x, double y, double velocity, boolean relative){
		
	}

	public void moveTo(double x, double y){
		
	}
	
	public void moveToRandom(){
		
	}
	
	public void setAcceleration(double acceleration){
		
	}
	
	public void setFriction(double friction){
		
	}
	
	public void wrapAroundRoom(boolean value){
		
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getWidth() {
		return myWidth;
	}

	@Override
	public double getHeight() {
		return myHeight;
	}

	@Override
	public double getRotation() {
		return rotation;
	}

	@Override
	public Vector getVelocity() {
		return velocity;
	}
	
	@Override
	public String getSprite() {
		return sprite;
	}

}
