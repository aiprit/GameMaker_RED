package structures.data.actions.object;

import structures.data.DataObject;
import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.ObjectParam;

public class CreateInstance extends DataAction {
	
	public CreateInstance(){
		init(new ObjectParam("Object"), new DoubleParam("X"), new DoubleParam("Y"));
	}

	@Override
	public String getTitle() {
		return "Create Instance";
	}

	@Override
	public String getDescription() {
		DataObject obj = (DataObject)get("Object").getValue();
		return String.format("Create an instance of %s at (%.2f, %.2f)", obj.getName(), get("X").getValue(), get("Y").getValue());
	}

	@Override
	protected String getSyntax() {
		return "library.create_instance('%s', %f, %f);";
	}

}
