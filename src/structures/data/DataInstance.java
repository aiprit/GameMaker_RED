package structures.data;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import utils.Vector;

public class DataInstance {

    private double myX, myY;
    private Map<String, Double> myVariables = new HashMap<>();
    private long myID;
    private DataObject myParentObject;
    private boolean myVisible;
    private int myZIndex;
    private double myAngle,
            myAngularVelocity,
            myScaleX,
            myScaleY,
            myAlpha,
            myFriction;
    private Vector myVelocity, myGravity;
    
    public DataInstance(DataObject parentObject, double x, double y){
    	this(parentObject, x, y, 1, 1);
    }
    
    public DataInstance(DataObject parentObject, double x, double y, double scaleX, double scaleY){
    	this(parentObject, x, y, System.currentTimeMillis(), scaleX, scaleY);
    }

    public DataInstance(DataObject parentObject, double x, double y, long ID, double scaleX, double scaleY) {
        myParentObject = parentObject;
        myAngle = 0;
        myAngularVelocity = 0;
        myX = x;
        myY = y;
        myID = ID;
        myZIndex = parentObject.getZIndex();
        myVisible = true;
        myScaleX = scaleX;
        myScaleY = scaleY;
        myAlpha = 1.0;
        myVelocity = Vector.ZERO;
        myGravity = Vector.ZERO;
        myFriction = 0;
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

    public Map<String, Double> getVariableMap(){
        return myVariables;
    }

    public void setVariableMap(Map<String, Double> m){
        myVariables = m;
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
    
    public double getFriction() {
    	return myFriction;
    }
    
    public void setFriction(double friction) {
    	myFriction = friction;
    }
    
    public Vector getGravity() {
    	return myGravity;
    }
    
    public void setGravity(Vector gravity) {
    	myGravity = gravity;
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

    public double getVelocityX(){
        return myVelocity.x;
    }

    public double getVelocityY(){
        return myVelocity.y;
    }

    public double getGravityX(){
        return myGravity.x;
    }

    public double getGravityY(){
        return myGravity.y;
    }

    public double getAlpha() {
        return myAlpha;
    }

    public void setAlpha(double alpha) {
        myAlpha = alpha;
    }
    
    public Image getImage() {
    	return myParentObject.getSprite().getImage();
    }
}
