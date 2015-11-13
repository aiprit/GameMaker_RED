package structures.data;

import java.awt.Rectangle;

/**
 * The View that determines what the camera paints on the screen
 * within a Room. Views are always bound to a specific Room, and
 * they must have a unique name within a Room. 
 *
 */
public class DataView {
	
	private final String myName;
	private final String myRoomName;
	private Rectangle myRectangle;
	
	public DataView(String roomName, String name, int width, int height, int x, int y) {
		myName = name;
		myRoomName = roomName;
		myRectangle = new Rectangle(x, y, width, height);
	}
    
    public String getName() {
    	return myName;
    }
    
    public String getRoomName() {
    	return myRoomName;
    }
    
    public Rectangle getView() {
        return myRectangle;
    }
}
