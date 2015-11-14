package structures.run;

import java.awt.Rectangle;
import structures.data.DataView;

public class RunView {
	
        private String myName;
        private Rectangle myView;
	
	public RunView(DataView dataView) {
	    myName = dataView.getName();
	    int x = dataView.getX();
	    int y = dataView.getY();
	    int width = dataView.getWidth();
	    int height = dataView.getHeight();
	    myView = new Rectangle(x, y, width, height);
	}
	
	public DataView toData() {
	    int width = myView.width;
	    int height = myView.height;
	    int x = myView.x;
	    int y = myView.y;
	    return new DataView(myName, width, height, x, y);
	}
	
	public void updateLocation(int x, int y) {
	    myView.setLocation(x, y);
	}
	
	public String toString() {
	    return myName;
	}

}
