/**
 *
 */
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
	public ObservableList<DataObject> getObjects();
	public void addObject(DataObject... a);
	public ObservableList<DataSprite> getSprites();
	public String getName();
	public ObservableList<DataRoom> getRooms();
	public List<DataSound> getSounds();
}