package structures.data;

/**
 * The View that determines what the camera paints on the screen
 * within a Room. Views are always bound to a specific Room, and
 * they must have a unique name within a Room.
 */
public class DataView {

    private final String myName;

    //width and height of the view
    //myX and myY is the location on the screen where the top
    //left corner of the view is drawn

    private double myWidth, myHeight, myX, myY;

    public DataView(String name, double width, double height, double x, double y) {
        myName = name;
        myWidth = width;
        myHeight = height;
        myX = x;
        myY = y;
    }

    public String getName() {
        return myName;
    }


    public double[] getBounds() {
        return new double[]{myWidth, myHeight};
    }

    public double[] getPosition() {
        return new double[]{myX, myY};
    }
    
    public void setWidth(double width) {
    	myWidth = width;
    }
    
    public void setHeight(double height) {
    	myHeight = height;
    }
    
    public void setXPosition(double x) {
    	myX = x; 
    }
    
    public void setYPosition(double y) {
    	myY = y; 
    }
    
    public void setPosition(double x, double y) {
    	myX = x; myY = y;
    }
}
