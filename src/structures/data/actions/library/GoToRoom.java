package structures.data.actions.library;

import structures.data.DataAction;
import structures.data.actions.params.IntegerParam;

public class GoToRoom extends DataAction{
	
	public GoToRoom(){
		init(new IntegerParam("NewRoomNumber"));
	}

	@Override
	public String getTitle() {
		return "GoToRoom";
	}

	@Override
	public String getDescription() {
		return String.format("go to room %d", get("NewRoomNumber").getValue());
	}

	@Override
	protected String getSyntax() {
		return "library.go_to_room(%d);";
	}

}
