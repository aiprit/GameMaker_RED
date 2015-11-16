package structures.run;

import java.util.List;

import exceptions.CompileTimeException;
import exceptions.GameRuntimeException;
import structures.data.DataRoom;
import utils.Utils;

public class RunRoom {
	
	String myName;
	RunView myView;
	String background;
	List<RunObject> myObjects;
	
	public RunRoom(DataRoom dataRoom, RunObjectConverter converter) throws CompileTimeException {
		myName = dataRoom.getName();
		myView = new RunView(dataRoom.getView());
		
		try {
			myObjects = Utils.transform(dataRoom.getObjectInstances(), e -> converter.instantiate(e));
		} catch (GameRuntimeException e) {
			throw new CompileTimeException(e.getMessage());
		}
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
