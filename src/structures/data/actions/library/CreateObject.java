package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class CreateObject extends DataAction {
	
	public CreateObject(){
		init(new StringParam("NewObjectId"), new DoubleParam("NewObjectX"), new DoubleParam("NewObjectY"));
	}

	@Override
	public String getTitle() {
		return "CreateObject";
	}

	@Override
	public String getDescription() {
		return String.format("create an object %s at (%.2f, %.2f)", get("NewObjectId"), get("NewObjectX"), get("NewObjectY"));
	}

	@Override
	protected String getSyntax() {
		return "library.create_object(%s, %f, %f);";
	}

}
