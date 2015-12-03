package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.RoomParam;

public class IfRoom extends DataAction {
	
	public IfRoom(){
		init(new RoomParam("Current room is"), new CheckboxParam("NOT"));
	}

	@Override
	public String getTitle() {
		return "If Room";
	}

	@Override
	public String getDescription() {
		String bool = (boolean) get("NOT").getValue() ? "NOT" : "";
		return String.format("If %s in Room %s", bool, ((RoomParam)get("Current room is")).getName());
	}

	@Override
	protected String getSyntax() {
		return "“if (library.get_room_id() %s %f)";
	}

}
