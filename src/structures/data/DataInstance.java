package structures.data;

import java.util.Random;

import utils.Point;

public class DataInstance {
	
	private DataObject myObject;
	private Point myPosition;
	private final long myInstanceID;
	
	public DataInstance(DataObject object) {
		this(object, (new Random()).nextLong());
	}
	public DataInstance(DataObject object, long instanceID) {
		myInstanceID = instanceID;
		myObject = object;
	}
	
	public long getInstanceId() {
		return myInstanceID;
	}
	
	public DataObject getDataObject() {
		return myObject;
	}
	
	
	public Point getPosition() {
		return myPosition;
	}
	public void setPosition(Point position) {
		myPosition = position;
	}
}
