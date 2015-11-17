package structures;

import structures.data.DataInstance;
import structures.data.DataView;

public interface IRoom {

	void addObjectInstance(DataInstance o);

	void removeObjectInstance(DataInstance o);

	void setSize(double width, double height);

	double[] getSize();

	void setBackground(IObject o);

	Object getBackground();

	void setView(DataView view);

	DataView getView();

	String getName();

}
