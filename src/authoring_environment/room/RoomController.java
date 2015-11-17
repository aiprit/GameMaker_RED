package authoring_environment.room;


import structures.data.DataInstance;
import structures.data.DataObject;
import structures.data.DataRoom;

public class RoomController {
	private DataRoom myRoom;
	
	public RoomController(DataRoom room) {
		myRoom = room;
	}


	public String getName() {
		return myRoom.getName();
	}
	
	public void addObject(DataObject object) {
		myRoom.addObjectInstance(new DataInstance(object, 0, 0));
	}
}
