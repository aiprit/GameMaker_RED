package structures.data;

import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DataGame implements IGetObjects {

    ObservableList<DataRoom> myRooms;
    ObservableList<DataObject> myObjects;
    ObservableList<DataSprite> mySprites;
    ObservableList<DataSound> mySounds; 

    private String myName;
    private DataRoom myStartRoom, myCurrentRoom;
    private double myScreenWidth;
    private double myScreenHeight;
    private String myGameDirectory;
    private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
    

    public DataGame(String name, String gameDirectory) {
    	myName = name;
    	myGameDirectory = gameDirectory;
    	myRooms = FXCollections.observableArrayList();
    	myObjects = FXCollections.observableArrayList();
    	mySprites = FXCollections.observableArrayList(); 
    	mySounds = FXCollections.observableArrayList();
    }

    public String getName() {
        return myName;
    }
  
    public DataRoom getCurrentRoom() {
    	return myCurrentRoom;
    }
    
    public DataRoom getStartRoom() {
    	return myStartRoom;
    }
    
    public ObservableList<DataRoom> getRooms() {
        return myRooms;
    }

    public void setStartRoom(DataRoom room){
        myStartRoom = room;
    }
    
    public String getSpriteDirectory() {
    	return myGameDirectory + r.getString("spriteRelativeDirectory");
    }
    public String getSoundDirectory() {
    	return myGameDirectory + r.getString("soundsRelativeDirectory");
    }

    public void addObject(DataObject o){
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
    public ObservableList<DataObject> getObjects() {
    	return myObjects;
    }

    public ObservableList<DataSound> getSounds() {
    	return mySounds;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        r.append(myName + "\n");

        for(DataObject o : myObjects){
            r.append(o.getName() + "\n");
        }

        for(DataSprite s : mySprites){
            r.append(s.getName() + "\n");
        }

        for(DataSound s : mySounds){
            r.append(s.getName() + "\n");
        }


        for(DataRoom room : myRooms){
            r.append(room.getName() + "\n");
        }

        return r.toString();
    }

	@Override
	public Collection<DataObject> getUnmodifiableObjects() {
		return Collections.unmodifiableCollection(myObjects);
	}
}
