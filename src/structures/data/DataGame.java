package structures.data;

import javafx.collections.ObservableMap;
import structures.IObject;
import structures.IRoom;

public class DataGame {
	
	ObservableMap<String, IRoom> myRooms;
	ObservableMap<String, IObject> myObjects;
	ObservableMap<String, DataSprite> mySprites;
	ObservableMap<String, DataSound> mySounds;
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
    
   public void addRoom(DataRoom room){
       myRooms.put(room.getName(), room);
   }
}
