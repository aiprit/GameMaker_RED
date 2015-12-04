/**
 *
 */
package structures.data.access_restricters;

import javafx.collections.ObservableList;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataSprite;

/**
 * @author loganrooper
 *
 */
public interface IObjectInterface {
	public ObservableList<DataObject> getObjects();
	public void addObject(DataObject... a);
	public ObservableList<DataSprite> getSprites();
	public ObservableList<DataRoom> getRooms();
}