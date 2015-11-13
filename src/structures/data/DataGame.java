package structures.data;

import java.util.Map;

public abstract class DataGame {

	Map<String, DataRoom> myRooms;
	Map<String, DataObject> myObjects;
	Map<String, DataSprite> mySprites;
	Map<String, DataSound> mySounds;
	private final String myName;
	String myStartRoomName, myCurrentRoomName;

	public DataGame(String name) {
		myName = name;
	}

	public String getName() {
		return myName;
	}

}
