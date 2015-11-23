package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.StringParam;

public class CreateObjectOnClick extends DataAction {
	
	public CreateObjectOnClick(){
		init(new StringParam("ObjectOnClick"));
	}

	@Override
	public String getTitle() {
		return "CreateObjectOnClick";
	}

	@Override
	public String getDescription() {
		return String.format("create an object %s at the mouse click", get("ObjectOnClick"));
	}

	@Override
	protected String getSyntax() {
		return "library.create_object('%s', event.get_x(), event.get_y());";
	}

}
