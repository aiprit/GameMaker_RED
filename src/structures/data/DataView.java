package structures.data;

/**
 * The View that determines what the camera paints on the screen
 * within a Room. Views are always bound to a specific Room, and
 * they must have a unique name within a Room.
 *
 */
public class DataView {

	private final String myName;

	//width and height of the view
	//myX and myY is the location on the screen where the top
	//left corner of the view is drawn

	private int myWidth, myHeight, myX, myY;

	public DataView(String name, int width, int height, int x, int y) {
		myName = name;
		myWidth = width;
		myHeight = height;
		myX = x;
		myY = y;
	}

    public String getName() {
    	return myName;
    }


    public int getWidth() {
	return myWidth;
    }
    
    public int getHeight() {
        return myHeight;
    }
    
    public int getX() {
        return myX;
    }

    public int getY() {
        return myY;
    }
    
}
