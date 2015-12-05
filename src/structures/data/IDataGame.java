package structures.data;

import javafx.collections.ObservableList;

public interface IDataGame {
	//TODO decide to combine interfaces for getObjects or not
	public int getViewWidth();
	
	public int getViewHeight();
	
	public ObservableList<DataObject> getObjects();
	
	public String getName();
}
