package structures.data;

import javafx.collections.ObservableList;

public interface IDataGame {
	public int getViewWidth();
	
	public int getViewHeight();
	
	public ObservableList<DataObject> getObjects();
}
