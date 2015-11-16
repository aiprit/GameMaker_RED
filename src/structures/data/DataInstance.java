package structures.data;

import structures.IObject;

public class DataInstance {
    private String myObjectType;
    private double myX, myY;

    private long myID;
    private IObject myParentObject;
    private boolean myVisible;
    private int myZIndex;
    private double myAngle, myAngularVelocity;
    private double myAlpha;

    public DataInstance(IObject parentObject, double x, double y, int zIndex) {
        this(parentObject, System.currentTimeMillis(), x, y, zIndex);
    }

    public DataInstance(IObject parentObject, long ID, double x, double y, int zIndex) {
        myParentObject = parentObject;
        myAngle = 0;
        myAngularVelocity = 0;
        myX = x;
        myY = y;
        myObjectType = parentObject.getName();
        myID = ID;
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

    public IObject getParentObject() {
        return myParentObject;
    }

    public double[] getPosition() {
        return new double[]{myX, myY};
    }

    public boolean isVisible() {
        return myVisible;
    }

    public void setVisible(boolean visible) {
        myVisible = visible;
    }

    public double getAngle() {
        return myAngle;
    }

    public void setAngle(double angle) {
        myAngle = angle;
    }

    public int getZIndex() {
        return myZIndex;
    }

    public void setZIndex(int zIndex) {
        myZIndex = zIndex;
    }

    public double getAngularVelocity() {
        return myAngularVelocity;
    }

    public void setAngularVelocity(double angularVelocity) {
        myAngularVelocity = angularVelocity;
    }

    public double getAlpha() {
        return myAlpha;
    }

    public void setAlpha(double alpha) {
        myAlpha = alpha;
    }
}
