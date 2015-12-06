package structures.data.actions.object;

import structures.data.DataAction;
import structures.data.actions.params.ObjectParam;

public class CreateInstanceAtObject extends DataAction {
	
	public CreateInstanceAtObject(){
		init(new ObjectParam("ObjectInstance"));
	}

	@Override
	public String getTitle() {
		return "Create Instance At Object";
	}

	@Override
	public String getDescription() {
		return String.format("makes an instance of %s at this object", get("ObjectInstance").getValue());
	}

	@Override
	protected String getSyntax() {
		return "library.create_instance('%s', engine.current().getX(), engine.current().getY());";
	}

}
