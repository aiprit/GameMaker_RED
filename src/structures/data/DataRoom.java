package structures.data;

import java.util.List;
import structures.IObject;
import structures.IRoom;

public class DataRoom implements IRoom {
    private List<IObject> roomObjects;
	private DataView myView;
	private IObject myBackground;
	private double myWidth, myHeight;
	private String myName;

	public DataRoom(String name, double width, double height){
		myName = name;
		myWidth = width;
		myHeight = height;

		//by default the view is set to be the entire room
		myView = new DataView("MainView", width, height, 0, 0);
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
	public void setSize(double width, double height) {

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

	@Override
	public String getName() {
		return myName;
	}
}
