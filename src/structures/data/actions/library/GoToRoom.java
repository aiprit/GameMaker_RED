package structures.data.actions.library;

<<<<<<< HEAD
import structures.data.actions.DataAction;
import structures.data.actions.params.RoomParam;
=======
import structures.data.DataAction;
import structures.data.actions.params.IntegerParam;
>>>>>>> master

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
		return String.format("Go to room %s", get("NewRoomNumber"));
	}

	@Override
	protected String getSyntax() {
		return "library.go_to_room(%s);";
	}

}
