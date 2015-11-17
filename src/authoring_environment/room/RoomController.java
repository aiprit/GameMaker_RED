package authoring_environment.room;

<<<<<<< HEAD
import structures.IRoom;
import structures.data.DataInstance;

public class RoomController {
	private IRoom myRoom;

	public RoomController(IRoom room) {
=======

import structures.data.DataInstance;
import structures.data.DataObject;
import structures.data.DataRoom;

public class RoomController {
	private DataRoom myRoom;
	
	public RoomController(DataRoom room) {
>>>>>>> 263c6f5fa4cb5314c3c7ff0ea2adf81d11b4aff5
		myRoom = room;
	}


	public String getName() {
		return myRoom.getName();
	}
<<<<<<< HEAD

	public void addObject(DataInstance object) {
		myRoom.addObjectInstance(object);
=======
	
	public void addObject(DataObject object) {
		myRoom.addObjectInstance(new DataInstance(object, 0, 0));
>>>>>>> 263c6f5fa4cb5314c3c7ff0ea2adf81d11b4aff5
	}
}
