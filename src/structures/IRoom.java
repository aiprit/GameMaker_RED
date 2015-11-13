package structures;

import structures.data.DataView;

public interface IRoom {

	void addObject(IObject o);

	void removeObject(IObject o);

	void setSize(int width, int height);

	double[] getSize();

	void setBackground(IObject o);

	Object getBackground();

	void setView(DataView view);

	DataView getView();

	String getName();

}
