package structures.data;

import structures.IObject;

/*
    Used for instances of DataObjects
 */

public class DataInstance{
    private String myObjectType;
    private double myX, myY;

    private long myID;
    private IObject myParentObject;
    private boolean myVisible;
    private int myZIndex;
    private double myAngle, myAngularVelocity;
    private double myAlpha;

    public DataInstance(IObject parentObject, double x, double y, int zIndex){
        myParentObject = parentObject;
        myAngle = 0;
        myAngularVelocity = 0;
        myX = x;
        myY = y;
        myObjectType = parentObject.getName();
        myID = System.currentTimeMillis();
        myZIndex = zIndex;
        myVisible = true;
    }

    public double getID() {
        return myID;
    }

    public void setPosition(double x, double y) {
        myX = x;
        myY = y;
    }

    public double[] getPosition() {
        return new double[] {myX, myY};
    }

    public void setVisible(boolean visible) {
        myVisible = visible;
    }

    public boolean isVisible() {
        return myVisible;
    }

    public void setAngle(double angle) {
        myAngle = angle;
    }

    public double getAngle() {
        return myAngle;
    }

    public void setZIndex(int zIndex) {
        myZIndex = zIndex;
    }

    public int getZIndex() {
        return myZIndex;
    }

    public void setAngularVelocity(double angularVelocity) {
        myAngularVelocity = angularVelocity;
    }

    public double getAngularVelocity() {
        return myAngularVelocity;
    }

    public double getAlpha() {
        return myAlpha;
    }

    public void setAlpha(double alpha) {
        myAlpha = alpha;
    }
}
