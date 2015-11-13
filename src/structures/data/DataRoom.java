package structures.data;

import java.util.List;
import structures.IObject;
import structures.IRoom;

public class DataRoom implements IRoom {
    List<IObject> roomObjects;
    DataView myView;
    IObject myBackground;
    int myWidth, myHeight;
    String myName;
    
        public DataRoom(String name) {
            myName = name;
            myView = new DataView("", "DefaultView", 400, 400, 0, 0);
        }
        
        public String toString() {
            return myName;
        }

	@Override
	public void addObject(IObject o) {
		roomObjects.add(o);
	}
	@Override
	public void removeObject(IObject o) {
		roomObjects.remove(o);
	}
	@Override
	public void setSize(int width, int height) {
		myWidth = width;
        myHeight = height;
	}
	@Override
	public double[] getSize() {
		return new double[] {myWidth, myHeight};
	}
	@Override
	public void setBackground(IObject o) {
		myBackground = o;
	}
	@Override
	public Object getBackground() {
		return myBackground;
	}
	@Override
	public void setView(DataView view) {
		myView = view;
	}
	@Override
	public DataView getView() {
		return myView;
	}
}
