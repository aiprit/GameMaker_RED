package structures.data;

import java.util.ArrayList;
import java.util.List;

public class DataRoom implements IDataRoom {
    private List<DataInstance> roomObjects;
    private DataView myView;
    private String myBackgroundColor;
    private double myWidth, myHeight;
    private String myName;

    public DataRoom(String name, double width, double height) {
        myName = name;
        myWidth = width;
        myHeight = height;
        roomObjects = new ArrayList<>();

        //by default the view is set to be the entire room
        myView = new DataView("MainView", 0, 0, width, height);
    }

    public void addObjectInstance(DataInstance o) {
        roomObjects.add(o);
    }

    public void setRoomObjects(List<DataInstance> roomObjects) {
        this.roomObjects = roomObjects;
    }

    public void removeObjectInstance(DataInstance o) {
        roomObjects.remove(o);
    }

    public List<DataInstance> getObjectInstances() {
        return roomObjects;
    }

    public void setSize(double width, double height) {
        myWidth = width;
        myHeight = height;
    }

    public String getBackgroundColor() {
        return myBackgroundColor;
    }

    public void setBackgroundColor(String color) {
        myBackgroundColor = color;
    }

    public double[] getSize() {
        return new double[]{myWidth, myHeight};
    }


    public DataView getView() {
        return myView;
    }

    public void setView(DataView view) {
        myView = view;
    }

    public String getName() {
        return myName;
    }
    
    public void setName(String name) {
    	myName = name;
    }
    
    @Override
    public String toString() {
    	return getName();
    }
}
