package structures.run;

import java.util.List;
import structures.data.DataRoom;

public class RunRoom {
	
	String myName;
	RunView myView;
	String background;
	List<RunObject> myObjects;
	
	public RunRoom(DataRoom dataRoom) {
		myName = dataRoom.toString();
		myView = new RunView(dataRoom.getView());
	}
	
	public String getBackground(){
		return background;
	}
	
	public List<RunObject> getObjects() {
		return myObjects;
	}
	
	public DataRoom toData() {
	    return null;
	}

}
