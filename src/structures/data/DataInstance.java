package structures.data;

import utils.Vector;

public class DataInstance {

    private double myX, myY;

    private long myID;
    private DataObject myParentObject;
    private boolean myVisible;
    private int myZIndex;
    private double myAngle,
            myAngularVelocity,
            myScaleX,
            myScaleY,
            myAlpha;
    private Vector myVelocity;

    public DataInstance(DataObject parentObject, double x, double y) {
        this(parentObject, System.currentTimeMillis(), x, y);
    }

    public DataInstance(DataObject parentObject, long ID, double x, double y) {
        myParentObject = parentObject;
        myAngle = 0;
        myAngularVelocity = 0;
        myX = x;
        myY = y;
        myID = ID;
        myZIndex = parentObject.getZIndex();
        myVisible = true;
        myScaleX = 1.0;
        myScaleY = 1.0;
        myAlpha = 1.0;
        myVelocity = Vector.ZERO;
    }

    public long getID() {
        return myID;
    }

    public void setPosition(double x, double y) {
        myX = x;
        myY = y;
    }

    public DataObject getParentObject() {
        return myParentObject;
    }


    public double[] getPosition() {
        return new double[]{myX, myY};
    }

    public double getX() {
        return myX;
    }

    public double getY() {
        return myY;
    }


    public boolean isVisible() {
        return myVisible;
    }

    public void setVisible(boolean visible) {
        myVisible = visible;
    }

    public Vector getVelocity() {
        return myVelocity;
    }

    public void setVelocity(Vector velocity) {
        myVelocity = velocity;
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


    public double getScaleX() {
        return myScaleX;
    }

    public void setScaleX(double scaleX) {
        myScaleX = scaleX;
    }

    public double getScaleY() {
        return myScaleY;
    }

    public void setScaleY(double scale) {
        myScaleY = scale;
    }


    public double getAlpha() {
        return myAlpha;
    }

    public void setAlpha(double alpha) {
        myAlpha = alpha;
    }
}
