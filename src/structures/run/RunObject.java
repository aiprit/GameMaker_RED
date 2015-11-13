package structures.run;

import java.util.Map;
import java.util.Vector;
import javafx.event.EventType;
import javafx.scene.image.ImageView;
import structures.IObject;

public class RunObject implements IRunDrawable {
	
	private String name;
	private String sprite;
	double width;
	double height;
	double rotation;
	Vector velocity;
	Map<EventType, RunAction> myEvents;
	double x;
	double y;
	
	public IObject toData() {
	    return null;
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
		return width;
	}

	@Override
	public double getHeight() {
		return height;
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
