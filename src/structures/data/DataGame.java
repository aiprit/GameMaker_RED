package structures.data;

import java.util.Map;

import structures.IObject;
import structures.IRoom;

public abstract class DataGame {
	
	Map<String, IRoom> myRooms;
	Map<String, IObject> myObjects;
	Map<String, DataSprite> mySprites;
	Map<String, DataSound> mySounds;
    private final String myName;
    String myStartRoomName, myCurrentRoomName;
    double myScreenWidth;
    double myScreenHeight;
    
    public DataGame(String name) {
    	myName = name;
    }
    
    public String getName() {
    	return myName;
    }
    
   
}
