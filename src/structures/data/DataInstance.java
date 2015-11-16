package structures.data;

import structures.IObject;

/*
    Used for instances of DataObjects
 */

public class DataInstance {
    private String myObjectType;
    private double myX, myY;
    private long myID;
    private IObject myParentObject;

    public DataInstance(IObject parentObject, double x, double y){
        myParentObject = parentObject;
        myX = x;
        myY = y;
        myObjectType = parentObject.getName();
        myID = System.currentTimeMillis();
    }
}
