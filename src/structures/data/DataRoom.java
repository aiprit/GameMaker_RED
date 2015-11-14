package structures.data;

import java.util.List;
import structures.IObject;
import structures.IRoom;

public class DataRoom implements IRoom {
    private List<DataObject> roomObjects;
	private DataView myView;
	private IObject myBackground;
	private int myWidth, myHeight;
	private String myName;

	public DataRoom(String name, int width, int height){
		myName = name;
		myWidth = width;
		myHeight = height;

		//by default the view is set to be the entire room
		myView = new DataView("MainView", width, height, 0, 0);
	}
	
	public List<DataObject> getObjects() {
	    return roomObjects;
	}

	@Override
	public void addObject(DataObject o) {
		roomObjects.add(o);
	}

	@Override
	public void removeObject(DataObject o) {
		roomObjects.remove(o);
	}

	@Override
	public void setSize(double width, double height) {

	}

	@Override
	public int[] getSize() {
		return new int[] {myWidth, myHeight};
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

	@Override
	public String getName() {
		return myName;
	}
}
