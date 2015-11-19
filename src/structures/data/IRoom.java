package structures.data;

import javafx.collections.ObservableList;

public interface IRoom {
	public ObservableList<DataRoom> getRooms();
	
	public void addRoom(DataRoom room);
}
