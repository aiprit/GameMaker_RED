package structures.run;

import java.awt.Rectangle;
import structures.data.DataView;

public class RunView {
	
        private String myName;
        private String myRoomName;
        private Rectangle myView;
	
	public RunView(DataView dataView) {
	    myName = dataView.getName();
	    myRoomName = dataView.getRoomName();
	    myView = new Rectangle(dataView.getView());
	}
	
	public DataView toData() {
	    return new DataView(myRoomName, myName, myView.width, myView.height, myView.x, myView.y);
	}
	
	public void updateLocation(int x, int y) {
	    myView.setLocation(x, y);
	}

}
