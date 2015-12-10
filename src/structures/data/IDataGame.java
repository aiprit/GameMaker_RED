package structures.data;

import javafx.collections.ObservableList;

public interface IDataGame {
	int getViewWidth();
	
	int getViewHeight();
	
	ObservableList<DataObject> getObjects();
	
	String getName();
	
	int getStartRoomIndex();
	
	void addRoom(DataRoom room);
	
	void setStartRoom(int index);
	
	ObservableList<DataRoom> getRooms();
}
