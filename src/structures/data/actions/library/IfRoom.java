package structures.data.actions.library;

import structures.data.DataAction;
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
		return String.format("If %s in Room %s", bool, get("Current room is"));
	}

	@Override
	protected String getSyntax() {
		return "“if (library.get_room_name().equals(%s) != %b)";
	}

}
