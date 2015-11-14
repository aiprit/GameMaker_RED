package structures;

import structures.data.DataObject;
import structures.data.DataView;

public interface IRoom {

	void addObject(DataObject o);

	void removeObject(DataObject o);

	void setSize(double width, double height);

	int[] getSize();

	void setBackground(IObject o);

	Object getBackground();

	void setView(DataView view);

	DataView getView();

	String getName();

}
