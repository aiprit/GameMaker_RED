package structures.run;

import java.util.List;

public class RunGame {
	
	private final String myName;
	private List<RunRoom> myRooms;
	
	private int myCurrentRoomNumber;
	
	public RunGame(String name) {
		myName = name;
	}
	
	public String getName() {
		return myName;
	}
	
	public RunRoom getCurrentRoom() {
		return myRooms.get(myCurrentRoomNumber);
	}
	

}
