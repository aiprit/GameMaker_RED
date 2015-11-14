package structures.run;

import java.util.ArrayList;
import java.util.List;
import structures.data.DataObject;
import structures.data.DataRoom;

public class RunRoom {
	
	private String myName;
	private RunView myView;
	private Object myBackground;
	private List<RunObject> myObjects;
	private int myWidth;
	private int myHeight;
	
	public RunRoom(DataRoom dataRoom) {
		myName = dataRoom.toString();
		myView = new RunView(dataRoom.getView());
		myObjects = new ArrayList<RunObject>();
		myBackground = dataRoom.getBackground();
		myWidth = dataRoom.getSize()[0];
		myHeight = dataRoom.getSize()[1];
		for (DataObject dObject : dataRoom.getObjects()) {
		    myObjects.add(new RunObject(dObject));
		}
	}
	
	public List<RunObject> getObjects() {
		return myObjects;
	}
	
	public DataRoom toData() {
	    return new DataRoom(myName, myWidth, myHeight);
	}

}
