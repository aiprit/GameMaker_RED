package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class GetRoomId extends DataAction {
	
	public GetRoomId(){
		init(new StringParam("IdOperation"),
				new DoubleParam("IdCheck"));
	}

	@Override
	public String getTitle() {
		return "GetRoomId";
	}

	@Override
	public String getDescription() {
		return String.format("checks if the room id is %s %.2f", get("IdOperation"), get("IdCheck"));
	}

	@Override
	protected String getSyntax() {
		return "“if (library.get_room_id() %s %f)";
	}

}
