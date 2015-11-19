package structures.data;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

=======
>>>>>>> refs/remotes/origin/authoring_environment
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

import java.util.HashMap;
import java.util.List;


public class DataObject {

//<<<<<<< HEAD
//    private ObservableMap<IDataEvent, List<IAction>> myEvents;
//=======
	private ObservableMap<IDataEvent, ObservableList<IAction>> myEvents;
//>>>>>>> abadaa4d66bf628cc800d4773a97910a4f881c00

<<<<<<< HEAD
	private String myName;
	private DataSprite mySprite;
	private ArrayList<DataSprite> mySprites = new ArrayList<DataSprite>();
	private int myZIndex;
=======
    private String myName;
    private DataSprite mySprite;

    private int myZIndex;
>>>>>>> refs/remotes/origin/authoring_environment

    public DataObject(String name) {
        myName = name;
        myEvents = FXCollections.observableMap(new HashMap<>());
        myZIndex = 0;
    }

    public String getName() {
        return myName;
    }

    public void setName(String name) {
        myName = name;
    }

//<<<<<<< HEAD
//    public void bindEvent(IDataEvent event, List<IAction> actions) {
//        myEvents.put(event, actions);
//    }
//=======

	public void bindEvent(IDataEvent event, ObservableList<IAction> actions) {
		myEvents.put(event, actions);
	}
//>>>>>>> abadaa4d66bf628cc800d4773a97910a4f881c00

    public void removeEvent(IDataEvent e) {
        myEvents.remove(e);
    }

//<<<<<<< HEAD
//    public ObservableMap<IDataEvent, List<IAction>> getEvents() {
//        return myEvents;
//    }
//=======
	public ObservableMap<IDataEvent, ObservableList<IAction>> getEvents(){

		return myEvents;
	}
//>>>>>>> abadaa4d66bf628cc800d4773a97910a4f881c00

    public DataSprite getSprite() {
        return mySprite;
    }

    public void setSprite(DataSprite sprite) {
        mySprite = sprite;
    }

    public int getZIndex() {
        return myZIndex;
    }

    public void setZIndex(int zIndex) {
        myZIndex = zIndex;
    }

<<<<<<< HEAD
	public void addSprite(DataSprite s) {

		mySprite = s;
		mySprites.add(s);

	}
=======
    public void addSprite(DataSprite s) {
        mySprite = s;
    }
>>>>>>> refs/remotes/origin/authoring_environment
}

