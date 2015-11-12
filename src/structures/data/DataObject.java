package structures.data;

import java.awt.geom.Point2D;
import java.util.Map;


import authoring_environment.IEvent;
import authoring_environment.IObject;
import authoring_environment.Sprite;

public class DataObject implements IObject {
	
    Map<DataEvent, DataObject> actions;
    String name;
    String sprite;

    Point2D position;
    double direction, angularVelocity;
    double scaleX, scaleY, alpha;
    
    boolean visible;
    int zIndex;

	@Override
	public void addEvent(IEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEvent(IEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSprite(Sprite s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSize(double width, double height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double[] getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(javafx.geometry.Point2D pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public javafx.geometry.Point2D getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVisibility(boolean visible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRotation(double angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getRotation() {
		// TODO Auto-generated method stub
		return 0;
	}
}
