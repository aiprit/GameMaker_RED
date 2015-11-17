package structures.data;

import javafx.collections.ObservableList;
import structures.IObject;
import structures.IRoom;


public class DataGame {

    ObservableList<IRoom> myRooms;
    ObservableList<IObject> myObjects;
    ObservableList<DataSprite> mySprites;
    ObservableList<DataSound> mySounds; 

    private String myName;
    private IRoom myStartRoom, myCurrentRoom;
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

    public void setStartRoom(IRoom room){
        myStartRoom = room;
    }
    
    public String getSpriteDirectory() {
    	return myGameDirectory + SPRITE_REL_DIRECTORY;
    }
    public String getSoundDirectory() {
    	return myGameDirectory + SOUND_REL_DIRECTORY;
    }

    public void addObject(IObject o){
        myObjects.add(o);
    }

    public void addSprite(DataSprite s){
        mySprites.add(s);
    }

    public void addSound(DataSound s){
        mySounds.add(s);
    }

   public void addRoom(DataRoom room){
       myRooms.add(room);
   }

    public double getWidth() {
        return myScreenWidth;
    }

    public double getHeight() {
        return myScreenHeight;
    }
    public ObservableList<DataSprite> getSprites(){
    	return mySprites;
    }
    public ObservableList<IObject> getObjects() {
    	return myObjects;
    }

    public ObservableList<DataSound> getSounds() {
    	return mySounds;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        r.append(myName + "\n");

        for(IObject o : myObjects){
            r.append(o.getName() + "\n");
        }

        for(DataSprite s : mySprites){
            r.append(s.getName() + "\n");
        }

        for(DataSound s : mySounds){
            r.append(s.getName() + "\n");
        }


        for(IRoom room : myRooms){
            r.append(room.getName() + "\n");
        }

        return r.toString();
    }
}
