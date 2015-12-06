package structures.data.actions.object;

import structures.data.DataObject;
import structures.data.DataAction;
import structures.data.actions.params.ObjectParam;

public class CreateInstanceAtCursor extends DataAction {
	
	public CreateInstanceAtCursor(){
		init(new ObjectParam("Object"));
	}

	@Override
	public String getTitle() {
		return "Create Instance at Mouse Pointer";
	}

	@Override
	public String getDescription() {
		DataObject obj = (DataObject)get("Object").getValue();
		return String.format("Create an instance of %s at the mouse pointer", obj.getName());
	}

	@Override
	protected String getSyntax() {
		return "library.create_instance('%s', library.mouse_x(), library.mouse_y());";
	}

}
