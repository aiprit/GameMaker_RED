package authoring_environment.room;

import structures.data.DataInstance;
import structures.data.DataRoom;
import structures.data.DataView;


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
	
	public double[] getSize() {
		return myRoom.getSize();
	}
	
	public void setSize(double width, double height) {
		myRoom.setSize(width, height);
	}
	
	public void setView(DataView view) {
		myRoom.setView(view);
	}
	
	public DataView getView() {
		return myRoom.getView();
	}
}