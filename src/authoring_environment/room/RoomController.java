package authoring_environment.room;

import structures.data.DataInstance;
import structures.data.DataRoom;


public class RoomController {
	private DataRoom myRoom;
	
	public RoomController(DataRoom room) {
		myRoom = room;
	}


	public String getName() {
		return myRoom.getName();
	}

	public void addObject(DataInstance object) {
		myRoom.addObjectInstance(object);
	}
}