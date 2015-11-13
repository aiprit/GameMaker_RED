package authoring_environment.room;

import structures.IRoom;

public class RoomController {
	private IRoom myRoom;
	
	public RoomController(IRoom room) {
		myRoom = room;
	}

	public String getName() {
		return myRoom.getName();
	}
	
	
}
