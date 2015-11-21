package structures.run;

import java.util.List;

import exceptions.CompileTimeException;
import exceptions.GameRuntimeException;
import structures.data.DataRoom;
import utils.Utils;

public class RunRoom {
	
	private String myName;
	private RunView myView;
	private String background;
	private List<RunObject> myObjects;
	private RunObjectConverter myConverter;
	private DataRoom myDataRoom;
	
	public RunRoom(DataRoom dataRoom, RunObjectConverter converter) throws CompileTimeException {
		myName = dataRoom.getName();
		myDataRoom = dataRoom;
		myConverter = converter;
		myView = new RunView(dataRoom.getDataView());
		
		try {
			myObjects = Utils.transform(dataRoom.getObjectInstances(), e -> converter.instantiate(e));
		} catch (GameRuntimeException e) {
			throw new CompileTimeException(e.getMessage());
		}
	}
	
	public RunObject instantiate(String name, double x, double y) throws GameRuntimeException {
		RunObject obj = myConverter.instantiate(name, x, y);
		myObjects.add(obj);
		return obj;
	}
	
	public String getName() {
		return myName;
	}
	
	public String toString() {
	    return myName;
	}
	
	public String getBackground(){
		return background;
	}
	
	public List<RunObject> getObjects() {
		return myObjects;
	}
	
	public RunView getView() {
		return myView;
	}
	
	public DataRoom toData() throws CompileTimeException {
	    try {
                myDataRoom.setRoomObjects(Utils.transform(myObjects, e -> myConverter.toData(e)));
        } catch (GameRuntimeException e) {
                throw new CompileTimeException(e.getMessage());
        }
	    return myDataRoom;
	}

}
