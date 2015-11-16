package structures.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import structures.IObject;
import structures.IRoom;

import java.util.HashMap;

public class DataGame {

    ObservableMap<String, IRoom> myRooms;
    ObservableMap<String, IObject> myObjects;
    ObservableMap<String, DataSprite> mySprites;
    ObservableMap<String, DataSound> mySounds;
    private String myName;
    private String myStartRoomName, myCurrentRoomName;
    private double myScreenWidth;
    private double myScreenHeight;

    public DataGame(String name) {
        myName = name;
        myRooms = FXCollections.observableMap(new HashMap<>());
        myRooms = FXCollections.observableMap(new HashMap<>());
        myObjects = FXCollections.observableMap(new HashMap<>());
        mySprites = FXCollections.observableMap(new HashMap<>());
        mySounds = FXCollections.observableMap(new HashMap<>());
    }

    public String getName() {
        return myName;
    }

    public void setStartRoom(String roomName) {
        myStartRoomName = roomName;
    }

    public void addObject(IObject o) {
        myObjects.put(o.getName(), o);
    }

    public void addSprite(DataSprite s) {
        mySprites.put(s.getName(), s);
    }

    public void addSound(DataSound s) {
        mySounds.put(s.getName(), s);
    }

    public void addRoom(DataRoom room) {
        myRooms.put(room.getName(), room);
    }

    public double getWidth() {
        return myScreenWidth;
    }

    public double getHeight() {
        return myScreenHeight;
    }

    public ObservableMap<String, DataSprite> getSprites() {
        return mySprites;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        r.append("Printing " + myName + "\n");


        return r.toString();
    }
}
