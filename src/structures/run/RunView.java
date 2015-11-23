package structures.run;

import structures.data.DataView;
import utils.Rectangle;

public class RunView {

	private final String myName;
	private Rectangle myView;
	private DataView myDataView;

	public RunView(DataView dataView) {
		myDataView = dataView;
		myName = dataView.getName();
		myView = dataView.getView().clone();
	}

	public String getName() {
		return myName;
	}

	public void updateLocation(double x, double y) {
		myView.move(x, y);
	}
	
	public Rectangle getView() {
		return myView;
	}

}
