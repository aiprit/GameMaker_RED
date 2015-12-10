package structures.data.access_restricters;

import java.util.List;

import javafx.collections.ObservableList;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataSound;
import structures.data.DataSprite;

/**
 * @author loganrooper
 *
 */
public interface IObjectInterface {
	ObservableList<DataObject> getObjects();
	void addObject(DataObject... a);
	ObservableList<DataSprite> getSprites();
	String getName();
	ObservableList<DataRoom> getRooms();
	List<DataSound> getSounds();
}