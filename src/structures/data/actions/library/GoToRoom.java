package structures.data.actions.library;

import structures.data.DataAction;
import structures.data.DataRoom;
import structures.data.actions.params.RoomParam;
import structures.data.DataAction;
import structures.data.actions.params.IntegerParam;

public class GoToRoom extends DataAction{

	public GoToRoom(){
		init(new RoomParam("Room"));
	}

	@Override
	public String getTitle() {
		return "Go To Room";
	}

	@Override
	public String getDescription() {
		return String.format("Go to room %s", ((DataRoom) get("Room").getValue()).getName());
	}

	@Override
	protected String getSyntax() {
		return "library.go_to_room(%s);";
	}

}
