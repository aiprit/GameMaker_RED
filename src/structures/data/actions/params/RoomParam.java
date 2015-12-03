package structures.data.actions.params;

import java.util.List;

import exceptions.ParameterParseException;
import structures.data.DataRoom;
import utils.Utils;

public class RoomParam implements IParameter, ISelectable {

	private List<DataRoom> myPossibleRooms;
	private DataRoom mySelected;
	private String myOriginal;
	private String myTitle;

	public RoomParam(String title) {
		myTitle = title;
	}

	public void setRoomList(List<DataRoom> possibleRooms) {
		myPossibleRooms = possibleRooms;
	}

	@Override
	public List<String> getOptions() {
		return Utils.transform(myPossibleRooms, e -> e.getName());
	}

	@Override
	public void parse(String string) throws ParameterParseException {
		myOriginal = string;
		DataRoom found = Utils.first(myPossibleRooms, e -> (e.getName().equals(string)), null);
		if (found == null) {
			throw new ParameterParseException("No valid room selected!");
		}
		mySelected = found;
	}

	@Override
	public String getOriginal() {
		return myOriginal;
	}

	@Override
	public String getTitle() {
		return myTitle;
	}

	@Override
	public DataRoom getValue() {
		return mySelected;
	}

	@Override
	public type getType() {
		return type.ROOM_SELECT;
	}

	@Override
	public String getName() {
		return mySelected.getName();
	}

}
