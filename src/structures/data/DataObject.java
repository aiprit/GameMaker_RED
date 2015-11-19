package structures.data;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

import java.util.HashMap;
import java.util.List;


public class DataObject {


	private ObservableMap<IDataEvent, ObservableList<IAction>> myEvents;


    private String myName;
    private DataSprite mySprite;
    private double myScaleX, myScaleY;

    private int myZIndex;


    public DataObject(String name) {
        myName = name;
        myEvents = FXCollections.observableMap(new HashMap<>());
        myZIndex = 0;
        myScaleX = 1.0;
        myScaleY = 1.0;
    }
    public DataObject(DataObject obj){
    	myName = obj.getName();
    	myEvents = obj.getEvents();
    	myZIndex = obj.getZIndex();
    	mySprite = obj.getSprite();

    }

    public String getName() {
        return myName;
    }

    public void setName(String name) {
        myName = name;
    }



	public void bindEvent(IDataEvent event, ObservableList<IAction> actions) {
		myEvents.put(event, actions);
	}


    public void removeEvent(IDataEvent e) {
        myEvents.remove(e);
    }

	public ObservableMap<IDataEvent, ObservableList<IAction>> getEvents(){

		return myEvents;
	}
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


    public void addSprite(DataSprite s) {
        mySprite = s;
    }

    @Override
    public String toString(){
    	return myName;
    }


    
    public double getScaleX() {
        return myScaleX;
    }

    public void setScaleX(double scale) {
        myScaleX = scale;
    }

    public double getScaleY() {
        return myScaleY;
    }

    public void setScaleY(double scale) {
        myScaleY = scale;
    }

}

