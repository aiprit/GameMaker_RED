package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class CreateObjectRandom extends DataAction {

	public CreateObjectRandom(){
		init(new StringParam("NewRandomObjectId"), new DoubleParam("NewRandomObjectX"), new DoubleParam("NewRandomObjectY"));
	}
	
	@Override
	public String getTitle() {
		return "CreateObjectRandom";
	}

	@Override
	public String getDescription() {
		return String.format("create an object %s between (0, 0) and (%.2f, %.2f)", get("NewRandomObjectId"), get("NewRandomObjectX"), get("NewRandomObjectY"));
	}

	@Override
	protected String getSyntax() {
		return "library.create_object('%s', library.random_number(%f), library.random_number(%f));";
	}

}
