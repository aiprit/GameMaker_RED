package structures.data;

import java.awt.Rectangle;

public class DataView {
	
	private final String myName;
	private Rectangle myBounds;
	
	public DataView(String name) {
		myName = name;
		myBounds = new Rectangle();
	}

    public void setBounds(Rectangle bounds){
    	myBounds = bounds;
    }
    
    public String getName() {
    	return myName;
    }
    
    public Rectangle getBounds() {
    	return myBounds;
    }
}
