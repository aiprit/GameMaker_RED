package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.DoubleParam;

public class GoToRoom extends DataAction{
	
	public GoToRoom(){
		init(new DoubleParam("NewRoomNumber"));
	}

	@Override
	public String getTitle() {
		return "GoToRoom";
	}

	@Override
	public String getDescription() {
		return String.format("go to room %.2f", get("NewRoomNumber"));
	}

	@Override
	protected String getSyntax() {
		return "library.go_to_room(%f);";
	}

}
