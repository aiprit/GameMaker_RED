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
	
	private Rectangle myBounds;
	
	public DataView(String roomName, String name) {
		myName = name;
		myRoomName = roomName;
		myBounds = new Rectangle();
	}

    public void setBounds(Rectangle bounds){
    	myBounds = bounds;
    }
    
    public String getName() {
    	return myName;
    }
    
    public String getRoomName() {
    	return myRoomName;
    }
    
    public Rectangle getBounds() {
    	return myBounds;
    }
}
