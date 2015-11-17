package authoring_environment.room;

import structures.IRoom;
import structures.data.DataInstance;

public class RoomController {
	private IRoom myRoom;

	public RoomController(IRoom room) {
		myRoom = room;
	}


	public String getName() {
		return myRoom.getName();
	}

	public void addObject(DataInstance object) {
		myRoom.addObjectInstance(object);
	}
}
