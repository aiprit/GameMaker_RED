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
    private String myStartRoomName, myCurrentRoomName;
    private double myScreenWidth;
    private double myScreenHeight;
    private String myGameDirectory;
    
    public static final String SPRITE_REL_DIRECTORY = "images/";
    public static final String SOUND_REL_DIRECTORY = "sounds/";

    public DataGame(String name, String gameDirectory) {
    	myName = name;
    	myGameDirectory = gameDirectory;
    }

    public String getName() {
    	return myName;
    }

    public void setStartRoom(String roomName){
        myStartRoomName = roomName;
    }
    
    public String getSpriteDirectory() {
    	return myGameDirectory + SPRITE_REL_DIRECTORY;
    }
    public String getSoundDirectory() {
    	return myGameDirectory + SOUND_REL_DIRECTORY;
    }

    public void addObject(IObject o){
        myObjects.put(o.getName(), o);
    }

    public void addSprite(DataSprite s){
        mySprites.put(s.getName(), s);
    }

    public void addSound(DataSound s){
        mySounds.put(s.getName(), s);
    }

   public void addRoom(DataRoom room){
       myRooms.put(room.getName(), room);
   }

    public double getWidth() {
        return myScreenWidth;
    }

    public double getHeight() {
        return myScreenHeight;
    }
    public ObservableMap<String, DataSprite> getSprites(){
    	return mySprites;
    }
}
