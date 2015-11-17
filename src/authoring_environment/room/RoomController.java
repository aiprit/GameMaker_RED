package authoring_environment.room;


import structures.IObject;
import structures.IRoom;

public class RoomController {
	private IRoom myRoom;

	public RoomController(IRoom room) {
		myRoom = room;
	}


	public String getName() {
		return myRoom.getName();
	}

	public void addObject(IObject object) {
		myRoom.addObject(object);
	}
}
